package app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import app.database.Database;
import app.gui.QuizPanel;
import app.model.WordCard;

enum Stage {
	QUESTION, EVALUATION, TRAINING, EXIT
}

public class Quiz {
	
	private static ArrayList<WordCard> cardList = null;
	private static WordCard card = null;
	private static Random random = new Random();
	private static int cIndex = 0;
	private static boolean isForward = true;
	private static Stage stage;
	private static boolean isEvaluated;
	private static StringBuilder typedWord = new StringBuilder();
	
	public static void start() {
		try {
			cardList = Database.getCardList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		next();
	}
	
	public static void next() {
		isEvaluated = false;
		typedWord = new StringBuilder();
		cIndex = random.nextInt(cardList.size());
		card = cardList.get(cIndex);
		
		isForward = random.nextBoolean();
		//isForward = true;
		/*if(isForward) {
			//QuizPanel.startForward(card);
			//QuizPanel.start(card, isForward);
		} else {
			QuizPanel.startBackward(card);
			typedWord = new StringBuilder();
		}*/
		QuizPanel.start(card, isForward);
		
		stage = Stage.QUESTION;
		
		System.out.println(cIndex);
		System.out.println(card);
	}
	
	public static void react(char command) {
		System.out.print(command);
		if(command == '') {
			play();
		} else if(stage == Stage.QUESTION) {
			if(command == '\n') {
				QuizPanel.showAnswer();
				play();
				stage = Stage.EVALUATION;
				
				if(!isForward) {
					if(typedWord.toString().equals(card.getWord())) {
						isEvaluated = true;
						QuizPanel.mark(1);
					} else {
						isEvaluated = true;
						QuizPanel.mark(-1);
					}
				}
			} else if(!isForward) {
				getWordOfQuestion(command);
			}
		} else if(stage == Stage.EVALUATION) {
			System.out.println("evaluate!");
			if(command == 'g') {
				isEvaluated = true;
				QuizPanel.mark(1);
			} else if(command == 'b') {
				isEvaluated = true;
				QuizPanel.mark(-1);
			} else if(command == 'n') {
				isEvaluated = true;
				QuizPanel.mark(0);
			} else if(isEvaluated && command == '\n') {
				if(typedWord.toString().equals(card.getWord())) {
					//stage = Stage.EXIT;
					next();
				} else {
					stage = Stage.TRAINING;
					typedWord = new StringBuilder();
					getTrainWord('_');
				}
			}
		} else if(stage == Stage.TRAINING) {
			//System.out.println("train!");
			getTrainWord(command);
		} else { // EXIT
			System.out.println("exit!");
			if(command == '\n') {
				next();
			}
		}
	}
	
	private static void getTrainWord(char c) {
		//System.out.println("training!");
		if(c == '\b') {
			typedWord.deleteCharAt(typedWord.length() - 1);
		} else if(c != '_') {
			typedWord.append(c);
		}
		var printOut = new StringBuilder(typedWord);
		if(typedWord.toString().equals(card.getWord())) {
			stage = Stage.EXIT;
		} else {
			printOut.append("_");
		}
		QuizPanel.typeIn(printOut.toString());
	}
	
	private static void getWordOfQuestion(char c) {
		if(c == '\b') {
			typedWord.deleteCharAt(typedWord.length() - 1);
		} else {
			typedWord.append(c);
		}
		var printOut = new StringBuilder(typedWord);
		printOut.append("_");
		QuizPanel.typeIn(printOut.toString());
	}
	
	private static void play() {
		var i = random.nextInt(card.getMp3urls().size());
		PlayMP3.play(card.getMp3urls().get(i));
	}
}
