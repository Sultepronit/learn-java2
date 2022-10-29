package app.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.App;

public class AddCardPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public AddCardPanel() {
		var wordField = new JTextField(30);
		var transcField = new JTextField(30);
		var translField = new JTextField(80);
		//var translField = new JTextField();
		//var soundUrdField = new JTextField(80);
		var soundUrdField = new JTextField(App.line);
		
		var playButton = new JButton("Play");
		var addButton = new JButton("Save");
		
		/*add(wordField);
		add(transcField);
		add(translField);*/
		
		setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		gc.gridwidth = 1;
		//gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(wordField, gc);
		
		gc.gridx++;
		add(transcField, gc);
		
		gc.gridx++;
		add(playButton, gc);
		
		gc.gridx++;
		add(addButton, gc);
		
		gc.gridwidth = 4;
		gc.gridx = 0;
		gc.gridy++;
		//gc.anchor = GridBagConstraints.BOTH;
		//gc.anchor = GridBagConstraints.HORIZONTAL;
		add(translField, gc);
		
		gc.gridy++;
		add(soundUrdField, gc);
		
		/*gc.gridx = 3;
		add(playButton, gc);*/
	}
	
	
}
