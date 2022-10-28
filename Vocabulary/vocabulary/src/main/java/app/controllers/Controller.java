package app.controllers;

import javax.swing.JPanel;

import app.gui.MainFrame;



public class Controller {
	
	private MainFrame mainFrame;
	
	public Controller() {
		mainFrame = new MainFrame(new JPanel(), new JPanel());
	}
}
