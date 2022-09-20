package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Database0.ReadFile;
import application.PlayMP3;

class GetKey implements KeyListener {
	public void keyTyped(KeyEvent e) {
		char k = e.getKeyChar();
		System.out.print(k);
		
		switch(k) {
			case 's':
				WordPanel.hintForward();
				break; 
			case 'w':
				WordPanel.showForward();
				break;
			case 'a':
				PlayMP3.repeat();
				break;
			case 'm':
				PlayMP3.stop();
				break;
			
			default: 
				//WordPanel.changeContents0();
				WordPanel.forward();
		}
		
		//WordPanel.changeContents0();
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}

public class WordKanjiPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//public static JLabel basicLabel = new JLabel("Progres...");
	public static JLabel wordLabel = new JLabel("word");
	public static JLabel transcriptionLabel = new JLabel("");
	public static JLabel translationLabel = new JLabel("");
	private static JLabel listLabel = new JLabel("---");
	
	public WordKanjiPanel() {
		//setBackground(Color.white);
		
		var getKey = new GetKey();
		
		var basicLabel = new JLabel("<html>"
				+ "<p style='padding: 5px; background-color:#eeeeee'>"
				+ "Progres...</p></html>");
		basicLabel.setFocusable(true);
		basicLabel.addKeyListener(getKey);
		
		setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.insets = new Insets(0,20,0,20);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(basicLabel, gc);
		
		
		gc.weightx = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		//gc.insets = new Insets(0,20,0,20);
		//gc.anchor = GridBagConstraints.CENTER;
		gc.gridy++;
		var whitePanel = new JPanel();
		whitePanel.setBackground(Color.white);
		whitePanel.setBorder(BorderFactory.createEtchedBorder());
		whitePanel.setLayout(new GridBagLayout());
		add(whitePanel, gc);
		
		var nc = new GridBagConstraints();
		nc.gridx = 0;
		nc.gridy = 0;
		nc.fill = GridBagConstraints.HORIZONTAL;
		var wordPanel = new WordPanel();
		whitePanel.add(wordPanel, nc);
		/*var wordPanel = new WordPanel();
		add(wordPanel, gc);*/
		
		nc.gridy++;
		nc.weightx = 3;
		//nc.fill = GridBagConstraints.HORIZONTAL;
		var kanjiPanel = new KanjiPanel();
		whitePanel.add(kanjiPanel, nc);
		
		gc.gridy++;
		//add(listLabel, gc);		
		//listLabel.setMaximumSize(new Dimension(1000,500));
		listLabel.setForeground(Color.black);
		listLabel.setFont(new Font("Noto Sans JP", Font.PLAIN, 40));
		
		JScrollPane scrollPane = new JScrollPane(listLabel);
		//scrollPane.setViewportView(box);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setLayout(new GridBagLayout());
		//scrollPane.add(listLabel, gc);
		//scrollPane.setMaximumSize(new Dimension(150, 410));
		scrollPane.setPreferredSize(new Dimension(150, 410));
		
		add(scrollPane, gc);
		
		//whitePanel.add(new JLabel("sdfsdfsdfsdfdf"));
		
		
		gc.gridy++;
		//gc.gridx++;
		//gc.gridwidth = 2;
		gc.weighty = 2;
		//gc.weightx = 2;
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		add(new JLabel("#####"), gc);
		
	}
	
	
	public static void kanjiToWords(char kanji) {
		System.out.println(kanji);
		var block = new StringBuilder("<html>");
		var tag = "<p style='font-family:\"FreeSerif\"; background-color:white; padding:2; margin:3' >";
		for(var word: ReadFile.words1) {
			if(word.getAllKanji().contains(kanji)) {
				//System.out.println(word);
				System.out.println(word.toTextLine());
				block.append(tag + word.toTextLine() + "</p>");
			}
		}
		block.append("</html>");
		listLabel.setText(block.toString());
	}

}
