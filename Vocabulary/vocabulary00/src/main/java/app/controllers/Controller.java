package app.controllers;

import javax.swing.JPanel;

import app.gui.AddCardPanel;
import app.gui.MainFrame;

public class Controller {
	
	private MainFrame mainFrame;
	//public AddCardPanel addCard; 
	
	public Controller() {
		var addCard = new AddCardPanel();
		mainFrame = new MainFrame(addCard, new JPanel());
	}
}
