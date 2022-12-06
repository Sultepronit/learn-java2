package app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.xml.crypto.Data;

import app.database.Database;
import app.gui.MainFrame;
import app.gui.QuizPanel;
import app.model.WordCard;

enum Stage {
	QUESTION, EVALUATION, TRAINING, EXIT
}

enum CardLearnStage {
	STUDY, CONTROL, REPEAT
}

public class Quiz {
	
	private static List<WordCard> cardList; 
	private static ArrayList<WordCard> studyList;
	private static ArrayList<WordCard> controlList;
	private static ArrayList<WordCard> repeatList;
	private static ArrayList<CardLearnStage> sessionCardOrder;
	private static WordCard card;
	//private static WordCard cardBefore;
	private static Random random = new Random();
	private static boolean isForward = true;
	private static Stage stage;
	private static boolean isEvaluated;
	private static StringBuilder typedWord = new StringBuilder();
	private static boolean isCorrect = false;
	private static int mark;
	private static int maxRepeatable;
	
	public Quiz(List<WordCard> cardList) {
		this.cardList = cardList;
		maxRepeatable = 10;
	}

	public static void start() {
		studyList = new ArrayList<>();
		controlList = new ArrayList<>();
		repeatList = new ArrayList<>();
		sessionCardOrder = new ArrayList<>();
		//studyList = (ArrayList<WordCard>) App.cardList;
		for(var card: cardList) {
			if(card.getStatus() == 0) {
				studyList.add(card);
			} else if(card.getStatus() == 1) {
				controlList.add(card);
			} else if(card.getStatus() <= maxRepeatable) {
				repeatList.add(card);
			}
		}
		System.out.println("Study list: " + studyList.size());
		System.out.println("Repeat0 list: " + controlList.size());
		System.out.println("Repeat list: " + repeatList.size());
		//lastToRepeat = controlList.size() / 10;
		//System.out.println("To repeat: " + lastToRepeat);
		
		for(int i = 0; i < studyList.size() - 2; i++) {
			sessionCardOrder.add(CardLearnStage.STUDY);
		}
		for(int i = 0; i < controlList.size() / 10; i++) {
			sessionCardOrder.add(CardLearnStage.CONTROL);
		}
		//sessionCardList.
		Collections.shuffle(sessionCardOrder);
		System.out.println(sessionCardOrder);
		
		chooseCard();
	}
	
	private static void next() {
		evaluate();
		if(sessionCardOrder.size() == 0) {
			end();
		} else {
			chooseCard();
		}
	}
	
	private static void end() {
		QuizPanel.end();
	}
	
	private static void chooseCard() {
		isEvaluated = false;
		isCorrect = false;
		typedWord = new StringBuilder();
		var cardBefore = card;
		while(cardBefore == card) {
			var ci = 0;
			switch(sessionCardOrder.get(0)) {
			case STUDY:
				ci = random.nextInt(studyList.size());
				card = studyList.get(ci);
				studyList.remove(ci);
				break;
			case CONTROL:
				ci = random.nextInt(controlList.size());
				card = controlList.get(ci);
				controlList.remove(ci);
				break;
			case REPEAT:
				break;
			}
			sessionCardOrder.remove(0);
		}

		if(card.getForward() > card.getBackward()) isForward = false;
		else isForward = true;
		
		QuizPanel.start(card, isForward);		
		stage = Stage.QUESTION;
		
		System.out.println(sessionCardOrder);
		System.out.println(card);
	}
	
	public static void react(char command) {
		System.out.print(command);
		if(command == '') {
			play();
		} else if (command == '') {
			start();
		} else if(stage == Stage.QUESTION) {
			if(command == '\n') {
				QuizPanel.showAnswer();
				play();
				stage = Stage.EVALUATION;
				
				if(!isForward) {
					isEvaluated = true;
					if(typedWord.toString().equals(card.getWord())) {
						isCorrect = true;
						mark = 1;
						typedWord.insert(0, "<html><p style='color:blue'>");
					} else {
						if(typedWord.toString().equals("")) {
							typedWord.append("_");
						}
						mark = -1;
						typedWord.insert(0, "<html><p style='color:red'>");
					}
					QuizPanel.mark(mark);
					typedWord.append("</p></html>");
					QuizPanel.typeIn(typedWord.toString());
				}
			} else if(!isForward) {
				getWordOfQuestion(command);
			}
		} else if(stage == Stage.EVALUATION) {
			//System.out.println("evaluate!");
			if(command == 'g') {
				isEvaluated = true;
				mark = 1;
				QuizPanel.mark(mark);
			} else if(command == 'b') {
				isEvaluated = true;
				mark = -1;
				QuizPanel.mark(mark);
			} else if(command == 'n') {
				isEvaluated = true;
				mark = 0;
				QuizPanel.mark(mark);
			} else if(isEvaluated && command == '\n') {
				if(isCorrect) {
					next();
				} else {
					stage = Stage.TRAINING;
					typedWord = new StringBuilder();
					QuizPanel.typeIn("_");
				}
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
		if(c == '\b') {
			typedWord.deleteCharAt(typedWord.length() - 1);
		} else {
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
	
	private static void evaluate() {
		if(card.getStatus() == 0) {
			if(mark == 0) mark = -1;
			if(isForward) {
				var res = card.getForward() + mark;
				if(res < -1) res = -1;
				card.setForward(res);
			} else { // backward
				var res = card.getBackward() + mark;
				if(res < -1) {
					res = 0;
					card.setForward(0);
				} 
				card.setBackward(res);
			}
			if(card.getBackward() == 2) {
				card.setStatus(1);
				card.setForward(0);
				card.setBackward(0);
			}
			/*if(mark > 0) {
				studyList.remove(cIndex);
				//System.out.println("remove!");
			}*/
			if(mark < 0) {
				studyList.add(card);
				sessionCardOrder.add(CardLearnStage.STUDY);
			}
		} else {
			int res = 0; // status > 0
			if(isForward) {
				res = card.getForward() + mark;
				card.setForward(res);
			} else { // backward
				res = card.getBackward() + mark;
				card.setBackward(res);
			}
			if(res < -1) {
				card.setStatus(0);
				card.setForward(0);
				card.setBackward(0);
			} 
			if(card.getBackward() == 1) {
				card.setStatus(2);
				card.setForward(0);
				card.setBackward(0);
			}
		}
		try {
			Database.updateStats(card);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(card);
	}
	
	private static void play() {
		var i = random.nextInt(card.getMp3urls().size());
		PlayMP3.play(card.getMp3urls().get(i));
	}
}
