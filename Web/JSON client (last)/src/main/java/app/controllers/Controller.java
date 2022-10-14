package app.controllers;

import java.io.IOException;

import app.gui.CreateBookPanel;
import app.gui.MainFrame;
import app.gui.ViewBooksPanel;
import app.model.Book;
import app.services.BookService;

public class Controller {
	
	private MainFrame mainFrame;
	private CreateBookPanel createPanel;
	private ViewBooksPanel viewPanel;
	private BookService bookService;
	
	public Controller() {
		bookService = new BookService();
		try {
			var bookList = bookService.getAll();
			System.out.println(bookList);
			viewPanel = new ViewBooksPanel(bookList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		createPanel = new CreateBookPanel();
		createPanel.setFormListener((author, title) -> {
			System.out.println(author + ": " + title);
			try {
				bookService.save(new Book(author, title));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		//viewPanel = new ViewBooksPanel();
		
		mainFrame = new MainFrame(createPanel, viewPanel);
	}
}
