package app.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;

import app.database.Database;
import app.gui.AddCardPanel;
import app.gui.MainFrame;
import app.gui.QuizPanel;
import app.model.WordCard;

public class Controller {
	
	private MainFrame mainFrame;
	
	private static List<WordCard> cardList; 
	
	public Controller() {
		refresh();
		var addCard = new AddCardPanel();
		var quizPanel = new QuizPanel();
		mainFrame = new MainFrame(addCard, quizPanel);
	}
	
	private void refresh() {
		cardList.clear();
		try {
			cardList = Database.getCardList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
