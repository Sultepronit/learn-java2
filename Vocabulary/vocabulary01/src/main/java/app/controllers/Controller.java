package app.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.Quiz;
import app.database.Database;
import app.gui.AddCardPanel;
import app.gui.MainFrame;
import app.gui.QuizPanel;
import app.model.WordCard;

public class Controller {
	
	private MainFrame mainFrame;
	
	private static final List<WordCard> cardList = new ArrayList<>(); 
	
	public Controller() {
		new Quiz(cardList);
		//refresh();
		var addCard = new AddCardPanel(cardList);
		var quizPanel = new QuizPanel();
		mainFrame = new MainFrame(addCard, quizPanel);
		//new Quiz(cardList);
		//Quiz.start();
		refresh();
	}
	
	public static void refresh() {
		
		try {
			cardList.clear();
			//cardList = Database.getCardList();
			cardList.addAll(Database.getCardList());
			Quiz.start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
