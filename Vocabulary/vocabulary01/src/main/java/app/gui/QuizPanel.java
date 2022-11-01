package app.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app.model.WordCard;

public class QuizPanel extends JPanel {
	
	public static JLabel wordLabel = new JLabel("word", SwingConstants.CENTER);
	public static JLabel transcLabel = new JLabel("transcription", SwingConstants.CENTER);
	
	public static void startForward(WordCard card) {
		System.out.println("Do!");
		wordLabel.setText(card.getWord());
		transcLabel.setText(" ");
	}
	
	public QuizPanel() {
		/*var typeField = new JTextField(20);
		typeField.setFont(new Font("Ubuntu", Font.PLAIN, 30));*/
		
		//var wordLabel = new JLabel("word", SwingConstants.CENTER);
		wordLabel.setOpaque(true);
		wordLabel.setBackground(Color.white);
		wordLabel.setFont(new Font("Ubuntu", Font.PLAIN, 30));
		//wordLabel.setVisible(false);
		
		//var transcLabel = new JLabel("transcription", SwingConstants.CENTER);
		transcLabel.setOpaque(true);
		transcLabel.setBackground(Color.white);
		transcLabel.setFont(new Font("Ubuntu", Font.PLAIN, 30));
		
		var trsl = "<html>translation, translation, translation, translation</html>";
		//var translLabel = new JLabel("translation", SwingConstants.CENTER);
		//var translLabel = new JLabel(trsl, SwingConstants.CENTER);
		var translLabel = new JLabel(trsl);
		//var translLabel = new JTextArea(trsl);
		translLabel.setOpaque(true);
		translLabel.setBackground(Color.white);
		translLabel.setFont(new Font("Ubuntu", Font.PLAIN, 30));
		//translLabel.setLineWrap(true);
		
		
		/*var wordPanel = new JPanel();
		wordPanel.setBackground(Color.WHITE);*/
		//wordPanel.setSize(new Dimension(1000,500));
		
		var mainPane = new JPanel();
		mainPane.setBackground(Color.YELLOW);
		//mainPane.setBackground(Color.white);
		setLayout(new BorderLayout());
		add(mainPane, BorderLayout.PAGE_START);
		
		mainPane.setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		gc.gridwidth = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		//gc.fill = GridBagConstraints.HORIZONTAL;
		
		//mainPane.add(typeField, gc);
		
		gc.gridy++;
		mainPane.add(wordLabel, gc);
		
		gc.gridy++;
		mainPane.add(transcLabel, gc);
		
		gc.gridy++;
		gc.fill = GridBagConstraints.HORIZONTAL;
		//gc.anchor = GridBagConstraints.LAST_LINE_END;*/
		mainPane.add(translLabel, gc);
		
		
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
}
