package app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import app.database.Database;
import app.gui.QuizPanel;
import app.model.WordCard;

enum Stage {
	QUESTION, EVALUATION, TRAINING
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
	//question
	//evaluation
	//training
	
	//question
	//evaluation
	//training
	
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
			
		}
	}
	
	/*public static void react(char command) {
		System.out.print(command);
		if(command == '') {
			play();
		} else if(isForward) {
			if(stage == "question") { //word
				if(command == '\n') {
					QuizPanel.showForward();
					play();
					stage = "evaluation";
				}
			}
			else {
				
			}
		} else { // backward
			
		}
	}*/
	
	private static void play() {
		var i = random.nextInt(card.getMp3urls().size());
		PlayMP3.play(card.getMp3urls().get(i));
	}
}
