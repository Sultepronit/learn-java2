package app.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.GetKey;
import app.model.WordCard;

public class QuizPanel extends JPanel {
	
	private static WordCard card = null;
	
	//public static JLabel statusMark = new JLabel(2);
	public static JPanel statusMark = new JPanel();
	public static JLabel wordLabel = new JLabel("word", SwingConstants.CENTER);
	public static JLabel transcLabel = new JLabel("transcription", SwingConstants.CENTER);
	public static JLabel translLabel = new JLabel("transl", SwingConstants.CENTER);
	public static JLabel exampleLabel = new JLabel("example", SwingConstants.CENTER);
	public static JLabel typeIn = new JLabel("_");
	
	public static GetKey getKey = new GetKey();
	
	public static void startForward(WordCard c) {
		card = c;
		System.out.println("Do!");
		typeIn.setVisible(false);
		wordLabel.setText(card.getWord());
		transcLabel.setText(" ");
		translLabel.setText(" ");
		exampleLabel.setText(" ");
	}
	
	public static void showForward() {
		//typeIn.setVisible(true);
		transcLabel.setText(card.getTransc());
		translLabel.setText(card.getTransl());
		exampleLabel.setText(card.getExample());
	}
	
	public static void mark(int isGood) {
		if(isGood == 1) {
			statusMark.setBackground(Color.green);
		} else if(isGood == -1) {
			statusMark.setBackground(Color.red);
		} else { // 0
			statusMark.setBackground(Color.yellow);
		}
	}
	
	public static void train(String typedIn) {
		typeIn.setVisible(true);
		typeIn.setText(typedIn);
	}
	
	public QuizPanel() {
		//statusMark.setOpaque(true);
		statusMark.setBackground(Color.white);
		statusMark.setPreferredSize(new Dimension(30, 30));
		//statusMark.setFont(new Font("Nimbus Roman", Font.ITALIC, 40));
		
		typeIn.setText(typeIn.getText() + "b");
		typeIn.setOpaque(true);
		typeIn.setBackground(Color.white);
		typeIn.setFont(new Font("Nimbus Roman", Font.ITALIC, 40));
		//typeIn.setVisible(false);
		/*var typeField = new JTextField(20);
		typeField.setFont(new Font("Ubuntu", Font.PLAIN, 30));*/
		
		//var wordLabel = new JLabel("word", SwingConstants.CENTER);
		wordLabel.setOpaque(true);
		wordLabel.setBackground(Color.white);
		wordLabel.setFont(new Font("Nimbus Roman", Font.PLAIN, 40));
		//wordLabel.setVisible(false);
		
		//var transcLabel = new JLabel("transcription", SwingConstants.CENTER);
		transcLabel.setOpaque(true);
		transcLabel.setBackground(Color.white);
		transcLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		
		var trsl = "<html>translation, translation, translation, translation</html>";
		//var translLabel = new JLabel("translation", SwingConstants.CENTER);
		//var translLabel = new JLabel(trsl, SwingConstants.CENTER);
		//var translLabel = new JLabel(trsl);
		//var translLabel = new JTextArea(trsl);
		translLabel.setOpaque(true);
		translLabel.setBackground(Color.white);
		translLabel.setFont(new Font("Ubuntu", Font.PLAIN, 30));
		//translLabel.setLineWrap(true);
		
		
		/*var wordPanel = new JPanel();
		wordPanel.setBackground(Color.WHITE);*/
		//wordPanel.setSize(new Dimension(1000,500));
		
		var mainPane = new JPanel();
		mainPane.setFocusable(true);
		mainPane.addKeyListener(getKey);
		//mainPane.setBackground(Color.YELLOW);
		//mainPane.setBackground(Color.white);
		setLayout(new BorderLayout());
		add(mainPane, BorderLayout.PAGE_START);
		
		mainPane.setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		gc.gridwidth = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		mainPane.add(statusMark, gc);
		//gc.gridx++;
		gc.gridwidth = 2;
		gc.weightx = 20;
		gc.anchor = GridBagConstraints.CENTER;
		
		gc.gridy++;
		mainPane.add(typeIn, gc);
		
		gc.gridy++;
		mainPane.add(wordLabel, gc);
		
		gc.gridy++;
		mainPane.add(transcLabel, gc);
		
		gc.gridy++;
		gc.fill = GridBagConstraints.HORIZONTAL;
		//gc.anchor = GridBagConstraints.LAST_LINE_END;*/
		mainPane.add(translLabel, gc);
		
		gc.gridy++;
		mainPane.add(exampleLabel, gc);
		
		
		/*setLayout(new BorderLayout());
		
		add(wordLabel, BorderLayout.PAGE_START);
		//add(wordLabel, BorderLayout.NORTH);
		
		add(transcLabel, BorderLayout.CENTER);
		
		add(translLabel, BorderLayout.LINE_START);
		
		var button = new JButton("+");
		button.addActionListener(arg -> {
			System.out.println("pressss");
			//wordLabel.setVisible(true);
		});
		add(button, BorderLayout.PAGE_END);*/
		
		/*setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		gc.gridwidth = 1;
		//gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(wordLabel, gc);
		
		gc.gridy++;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(wordPanel, gc);
		
		gc.gridy++;
		add(new JLabel("#####"), gc);
		
		var button = new JButton("+");
		button.addActionListener(arg -> {
			System.out.println("pressss");
			wordLabel.setVisible(true);
		});
		
		gc.gridy++;
		add(button, gc);*/
		
		//add(wordLabel);
	}
	
	/*public static void setCard(WordCard sCard) {
		card = sCard;
	}*/
}
