package app;

import java.sql.SQLException;
import java.util.ArrayList;
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

public class Quiz {
	
	private static List<WordCard> cardList; 
	private static ArrayList<WordCard> studyList;
	private static ArrayList<WordCard> repeatList;
	//private static int toRepeat;
	private static int lastToRepeat;
	private static WordCard card;
	private static WordCard cardBefore;
	private static Random random = new Random();
	private static int cIndex = 0;
	private static boolean isForward = true;
	private static Stage stage;
	private static boolean isEvaluated;
	private static StringBuilder typedWord = new StringBuilder();
	private static boolean isCorrect = false;
	private static int mark = 88;
	
	public Quiz(List<WordCard> cardList) {
		this.cardList = cardList;
	}

	public static void start() {
		/*try {
			//studyList = Database.getCardList();
			var list = Database.getCardList();
			studyList = list;
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		studyList = new ArrayList<>();
		repeatList = new ArrayList<>();
		//studyList = (ArrayList<WordCard>) App.cardList;
		for(var card: cardList) {
			if(card.getStatus() == 0) {
				studyList.add(card);
				System.out.println(card);
			} else if(card.getStatus() == 1) {
				repeatList.add(card);
				//System.out.println(card);
			}
		}
		//toRepeat = lastToRepeat = 1;
		lastToRepeat = 1;
		next();
	}
	
	private static void end() {
		//System.out.println("finnish!");
		QuizPanel.end();
	}
	
	private static void next() {
		if(mark < 88) {
			evaluate();
		}
		
		if(studyList.size() + lastToRepeat < 3) {
			end();
		} else {
			isEvaluated = false;
			isCorrect = false;
			typedWord = new StringBuilder();
			cardBefore = card;
			while(cardBefore == card) {
				int order = random.nextInt(lastToRepeat + studyList.size() + 1 - 2);
				if(order > lastToRepeat) {
					cIndex = random.nextInt(studyList.size());
					card = studyList.get(cIndex);
				} else {
					cIndex = random.nextInt(repeatList.size());
					card = repeatList.get(cIndex);
					lastToRepeat--;
					repeatList.remove(cIndex);
				}
				
			}
			//isForward = random.nextBoolean();
			if(card.getForward() > card.getBackward()) isForward = false;
			else isForward = true;
			
	
			QuizPanel.start(card, isForward);		
			stage = Stage.QUESTION;
			
			System.out.println(studyList.size());
			System.out.println(cIndex);
			System.out.println(card);
		}
	}
	
	public static void react(char command) {
		System.out.print(command);
		if(command == '') {
			play();
			//MainFrame.returnSplitPane();
		} else if(stage == Stage.QUESTION) {
			if(command == '\n') {
				QuizPanel.showAnswer();
				play();
				stage = Stage.EVALUATION;
				
				if(!isForward) {
					isEvaluated = true;
					if(typedWord.toString().equals(card.getWord())) {
						isCorrect = true;
						//QuizPanel.mark(1);
						mark = 1;
						typedWord.insert(0, "<html><p style='color:blue'>");
					} else {
						//QuizPanel.mark(-1);
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
			//System.out.println("train!");
			getTrainWord(command);
		} else { // EXIT
			//System.out.println("exit!");
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
			if(/*card.getForward() ==*/ card.getBackward() == 2) {
				card.setStatus(1);
				card.setForward(0);
				card.setBackward(0);
			}
			if(mark > 0) {
				studyList.remove(cIndex);
				System.out.println("remove!");
			}
		} else {
			int res = 0; // status > 0
			if(isForward) {
				res = card.getForward() + mark;
				if(res < -1) res = -1;
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
			if(/*card.getForward() ==*/ card.getBackward() == 1) {
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
