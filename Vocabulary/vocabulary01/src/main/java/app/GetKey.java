package app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import app.gui.QuizPanel;

public class GetKey implements KeyListener {

	public void keyTyped(KeyEvent e) {
		Quiz.react(e.getKeyChar());
		/*char k = e.getKeyChar();
		System.out.print(k);
		
		switch(k) {
			case 's':
				QuizPanel.showForward();
				break;
			case '\n':
				Quiz.next();
				break;
			case '\b':
				System.out.println("BaaaaK!");
				break;
		}*/
	}

	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}
