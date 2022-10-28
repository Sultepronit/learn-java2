package app;

import javax.swing.SwingUtilities;

import app.controllers.Controller;

public class App {

	public static void main(String[] args) {
		System.out.println("Hello there");
		
		SwingUtilities.invokeLater(Controller::new);
	}

}
