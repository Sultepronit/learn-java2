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
	//private static short stage = 0;
	//private static String stage = null;
	private static Stage stage;
	private static boolean isEvaluated;
	private static StringBuilder typedWord;
	
	public static void start() {
		//ArrayList<WordCard> cardList = new ArrayList<WordCard>();
		//ArrayList<WordCard> cardList = null;
		try {
			cardList = Database.getCardList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		next();
	}
	
	public static void next() {
		cIndex = random.nextInt(cardList.size());
		card = cardList.get(cIndex);
		QuizPanel.startForward(card);
		isForward = true;
		//stage = 0;
		//stage = "question";
		stage = Stage.QUESTION;
		
		System.out.println(cIndex);
		System.out.println(card);
	}
	
	public static void react(char command) {
		System.out.print(command);
		if(command == '') {
			play();
		} else if(stage == Stage.QUESTION) {
			if(isForward) { 
				if(command == '\n') {
					QuizPanel.showForward();
					play();
					stage = Stage.EVALUATION;
				}
			}
			else { //backward
				
			}
		} else if(stage == Stage.EVALUATION) {
			//System.out.println("evaluate!");
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
				stage = Stage.TRAINING;
				typedWord = new StringBuilder();
				getTrainWord('_');
			}
		} else if(stage == Stage.TRAINING) {
			getTrainWord(command);
		} else { // EXIT
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
		QuizPanel.train(printOut.toString());
	}
	
	
	private static void play() {
		var i = random.nextInt(card.getMp3urls().size());
		PlayMP3.play(card.getMp3urls().get(i));
	}
}
