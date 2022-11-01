package app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import app.database.Database;
import app.gui.QuizPanel;
import app.model.WordCard;

public class Quiz {
	
	public static ArrayList<WordCard> cardList = null;
	public static WordCard card = null;
	public static Random random = new Random();
	public static int cIndex = 0;
	
	public static void start() {
		//ArrayList<WordCard> cardList = new ArrayList<WordCard>();
		//ArrayList<WordCard> cardList = null;
		try {
			cardList = Database.getCardList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Random random = new Random();
		cIndex = random.nextInt(cardList.size());
		//cIndex = random.nextInt(2);
		System.out.println(cIndex);
		card = cardList.get(cIndex);
		System.out.println(card);
		//QuizPanel(cardList.get(cIndex));
		QuizPanel.startForward(card);
	}
	
	public static void next() {
		cIndex = random.nextInt(cardList.size());
		System.out.println(cIndex);
		card = cardList.get(cIndex);
		System.out.println(card);
		QuizPanel.startForward(card);
	}
}
