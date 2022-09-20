package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private UserFormListener formListener;
	
	public MainPanel() {
		//setBackground(Color.YELLOW);
		
		var formLabel = new JLabel("Add User");
		formLabel.setFont(new Font("URW Bookman", Font.PLAIN, 30));
		
		/*var nameLabel = new JLabel("Name:");
		var passLabel = new JLabel("Password:");
		
		var nameField = new JTextField(15);//15 characters long
		var passField = new JTextField(15);
		
		var addButton = new JButton("Save");*/
		
		/*setLayout(new BorderLayout());
		add(formLabel, BorderLayout.NORTH);
		add(nameLabel, BorderLayout.CENTER);*/
		
		setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		
		/*var rightPad = new Insets(0,0,0,10);
		var zeroPad = new Insets(0,0,0,0);*/
		
		gc.gridx = 0;
		gc.gridy = 0;
		//gc.weightx = 1; 
		gc.weighty = 1;
		
		//gc.weighty = 30;
		//gc.gridwidth = 2;
		
		add(formLabel, gc);
		
		gc.gridy++;
		gc.weighty = 1.5;
		gc.anchor = GridBagConstraints.NORTH;		
		add(createFormPanel(), gc);
		
		
		/*gc.gridwidth = 1;
		gc.weighty = 0.1;
		
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = rightPad;
		add(nameLabel, gc);
		gc.insets = zeroPad;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc); 
		
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = rightPad;
		add(passLabel, gc);
		gc.insets = zeroPad;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(passField, gc);
		
		gc.weighty = 30;
		
		gc.gridy++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(addButton, gc);*/
		
	}
	
	public void setFormListener(UserFormListener formListener) {
		this.formListener = formListener;
	}
	
	private JPanel createFormPanel() {
		JPanel panel = new JPanel();
		
		var padding = 20;
		var etchedBorder = BorderFactory.createEtchedBorder();
		var EmptyBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
		panel.setBorder(BorderFactory.createCompoundBorder(etchedBorder, EmptyBorder));
		
		var nameLabel = new JLabel("Name:");
		var passLabel = new JLabel("Password:");
		
		var nameField = new JTextField(15);//15 characters long
		var passField = new JTextField(15);
		
		var addButton = new JButton("Save");
		addButton.addActionListener(e -> {
			String username = nameField.getText();
			String password = passField.getText();
			//System.out.println(name + ": " + password);
			if(formListener != null) {
				formListener.formSubmitted(username, password);
			}
		});
		
		panel.setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		
		var rightPad = new Insets(0,0,0,10);
		var zeroPad = new Insets(0,0,0,0);
		
		gc.gridwidth = 1;
		gc.weighty = 0.1;
		
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = rightPad;
		panel.add(nameLabel, gc);
		gc.insets = zeroPad;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		panel.add(nameField, gc); 
		
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = rightPad;
		panel.add(passLabel, gc);
		gc.insets = zeroPad;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		panel.add(passField, gc);
		
		gc.weighty = 30;
		
		gc.gridy++;
		//gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,3);
		panel.add(addButton, gc);
		
		return panel;
	}

}
