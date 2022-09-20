package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class KanjiPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static JLabel kanjiText = new JLabel("");
	public static JLabel kanjiGif = new JLabel("");
	
	public KanjiPanel() {
		setBackground(Color.white);
		
		setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.insets = new Insets(30,0,0,0);
		add(kanjiGif, gc);
		gc.insets = new Insets(0,0,0,0);
		
		gc.gridx++;
		add(kanjiText, gc);
		Font kanji1 = new Font("Noto Sans CJK JP", Font.PLAIN, 115);
		kanjiText.setFont(kanji1);
		kanjiText.setForeground(Color.black);
		/*kanjiText.setText("<html>怪"
				+ "<span style='font-family:\"Noto Serif CJK SC\" '>怪</span>"
				//+ "<img src='http://www.yosida.com/images/kanji/8336.gif'></html>");
				+ "</html>");*/
		
		gc.gridy++;
		gc.weightx = 2;
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		add(new JLabel("#####"), gc);
		
	}
}
