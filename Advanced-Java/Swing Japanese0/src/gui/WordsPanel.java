package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.PlayMP3;

class GetKey implements KeyListener {
	public void keyTyped(KeyEvent e) {
		/*System.out.println("!!!");
		System.out.println();*/
		System.out.print(e.getKeyChar());
		WordsPanel.changeContents0();
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}

public class WordsPanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	
	//public static JLabel basicLabel = new JLabel("Progres...");
	public static JLabel unneeded = new JLabel("++++++++");
	public static JLabel wordLabel = new JLabel("word");
	public static JLabel transcriptionLabel = new JLabel("");
	public static JLabel translationLabel = new JLabel("");
	
	public WordsPanel() {
		setBackground(Color.white);
		
		var getKey = new GetKey();
		
		var basicLabel = new JLabel("<html>"
				+ "<p style='padding: 5px; background-color:#eeeeee'>"
				+ "Progres...</p></html>");
		basicLabel.setFocusable(true);
		basicLabel.addKeyListener(getKey);
		//basicLabel.addKeyListener(this);
		
		/*basicLabel.setBackground(Color.YELLOW);
		basicLabel.setForeground(Color.red);*/
		
		//var wordLabel = new JLabel("word");
		
		setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(basicLabel, gc);
		
		//gc.gridx++;
		gc.weightx = 2;
		//gc.gridwidth = 3;
		gc.anchor = GridBagConstraints.CENTER;
		
		gc.gridy++;
		add(unneeded, gc);
		
		gc.gridy++;
		//gc.weighty = 1;
		add(wordLabel, gc);
		Font word1 = new Font("Arial", Font.PLAIN, 100);
		wordLabel.setFont(word1);
		wordLabel.setBackground(Color.YELLOW);
		wordLabel.setForeground(Color.black);
		
		gc.gridy++;
		//gc.weighty = 1;
		transcriptionLabel.setFont(new Font("Noto Serif CJK SC", Font.PLAIN, 40));
		//transcriptionLabel.setFont(new Font("Noto Sans CJK JP", Font.PLAIN, 30));
		//transcriptionLabel.setFont(new Font("FreeSerif", Font.PLAIN, 40));
		add(transcriptionLabel, gc);
		transcriptionLabel.setForeground(Color.black);
		
		gc.gridy++;
		translationLabel.setFont(new Font("FreeSerif", Font.PLAIN, 40));
		add(translationLabel, gc);
		translationLabel.setForeground(Color.black);
		
		
		gc.gridy++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(new JLabel("kanj0"), gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("kanj01"), gc);
		
		gc.gridy++;
		//gc.gridx++;
		//gc.gridwidth = 2;
		gc.weighty = 2;
		//gc.weightx = 2;
		add(new JLabel("#####"), gc);
		
		var imgLabel = new JLabel();
		imgLabel.setIcon(new ImageIcon("http://www.yosida.com/images/kanji/8336.gif"));
		Dimension size = imgLabel.getPreferredSize();
		imgLabel.setBounds(50, 30, size.width, size.height);
		add(imgLabel);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new URL("http://www.yosida.com/images/kanji/8336.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(img);
		int imgh = icon.getIconHeight();
		int imgw = icon.getIconWidth();
		double newImagew = imgw / (double)imgh * 100.0;
		System.out.println(imgw + "*" + imgh);
		System.out.println(newImagew + "*" + 100);
		/*JLabel lbl = new JLabel();
	    lbl.setIcon(icon);*/
		JLabel lbl = new JLabel(icon);
	    add(lbl);
	}
	
	public static String[][] words = {
			{"怪しい", "あやしい", "сумнівний, підозрілий"},
			{"趣味", "しゅみ", "інтерес, хобі, захоплення"},	
			{"燃える", "もえる", "горіти, палати <i>прям. і перен.</i>"},	
	};
	
	public static void changeContents0() {
		String word = "怪しい";
		String transc = "あやしい";
		String transl = "сумнівний, підозрілий";
		Random random = new Random();
		int i = random.nextInt(3);
		unneeded.setText("");
		//"<img src=\"http://www.yosida.com/images/kanji/984C.gif\">"
		//wordLabel.setText("<html><p style='font-size:4em'>" + word + "</html>");
		char c = words[i][0].charAt(0);
		System.out.println(c);
		System.out.println(Integer.toHexString((int) c));
		String sc = Integer.toHexString((int) c).toUpperCase();
		System.out.println("http://www.yosida.com/images/kanji/" + sc + ".gif");
		wordLabel.setText("<html>"
				+ "<img src='http://www.yosida.com/images/kanji/" + sc + ".gif'>"
				+ "<img width=133 height=100 src='http://www.yosida.com/images/kanji/8336.gif'>"
				+ "<p style='font-family:\"Noto Serif CJK JP\"; text-align:center'>"
				//+ "<p style='font-family:\"Noto Serif CJK JP\"; font-size:7em; text-align:center'>"
				//+ "<p style='font-family:\"VL PGothic\"; font-size:7em; text-align:center'>"
				+words[i][0] + "</p></html>");
		//wordLabel.setBackground(Color.YELLOW);
		//wordLabel.setForeground(Color.RED);VL PGothic
		transcriptionLabel.setText(words[i][1]);
		//translationLabel.setText("<html><p style='font-family:\"FreeSerif\" '>"
		translationLabel.setText("<html><p style='color:blue'>"
		+ words[i][2] + "</p></html>");
		//PlayMP3.play();
		PlayMP3.audio("https://assets.languagepod101.com/dictionary/japanese/audiomp3.php?kana="
				+ words[i][1] + "&kanji=" + words[i][0]);
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
