package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import application.PlayMP3;

class GetKey2 implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		/*System.out.println("!!!");
		System.out.println();*/
		System.out.print(e.getKeyChar());
		WordsPanel2.changeContents0();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

public class WordsPanel2 extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	
	//public static JLabel basicLabel = new JLabel("Progres...");
	public static JLabel unneeded = new JLabel("++++++++");
	public static JLabel wordLabel = new JLabel("word");
	public static JLabel transcriptionLabel = new JLabel("");
	public static JLabel translationLabel = new JLabel("");
	
	public WordsPanel2() {
		var getKey = new GetKey();
		
		//PlayMP3.audio("");
		
		var basicLabel = new JLabel("Progres...");
		basicLabel.setFocusable(true);
		basicLabel.addKeyListener(getKey);
		//basicLabel.addKeyListener(this);
		
		//var wordLabel = new JLabel("word");
		
		setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		
		//gc.anchor = GridBagConstraints.FIRST_LINE_END;
		//gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		//gc.weighty = 1;
		//gc.anchor = GridBagConstraints.NORTHWEST;
		//gc.anchor = GridBagConstraints.NORTH;
		add(basicLabel, gc);
		
		gc.gridy++;
		add(unneeded, gc);
		
		//gc.gridwidth = 2;
		//gc.gridx = 10;
		//gc.anchor = GridBagConstraints.NORTH;
		gc.gridy++;
		//gc.weighty = 1;
		add(wordLabel, gc);
		
		gc.gridy++;
		//gc.weighty = 1;
		add(transcriptionLabel, gc);
		
		gc.gridy++;
		add(translationLabel, gc);
	}
	
	public static void changeContents0() {
		String word = "怪しい";
		String transc = "あやしい";
		String transl = "сумнівний, підозрілий";
		unneeded.setText("");
		//"<img src=\"http://www.yosida.com/images/kanji/984C.gif\">"
		//wordLabel.setText("<html><p style='font-size:4em'>" + word + "</html>");
		wordLabel.setText("<html>"
				+ "<img src=\"http://www.yosida.com/images/kanji/984C.gif\">"
				+ "<img src=\"http://www.yosida.com/images/kanji/8336.gif\">"
				+ "<p style='font-size:6em; text-align:center'>" + word + "</html>");
		transcriptionLabel.setText(transc);
		translationLabel.setText(transl);
		//PlayMP3.play();
		PlayMP3.audio("https://assets.languagepod101.com/dictionary/japanese/audiomp3.php?kana="
				+ transc + "&kanji=" + word);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("!!!");
		System.out.println(e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
