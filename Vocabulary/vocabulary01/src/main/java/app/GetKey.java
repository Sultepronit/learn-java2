package app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import app.gui.QuizPanel;

public class GetKey implements KeyListener {

	public void keyTyped(KeyEvent e) {
		char k = e.getKeyChar();
		//var code = e.getKeyCode();
		System.out.print(k);
		//System.out.print(code);
		
		/*if(code == KeyEvent.VK_ENTER) {
			System.out.println("enter");
		}*/
		
		switch(k) {
			case 's':
				QuizPanel.showForward();
				break;
			case '\n':
				Quiz.next();
				break;
		}
	}

	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}
