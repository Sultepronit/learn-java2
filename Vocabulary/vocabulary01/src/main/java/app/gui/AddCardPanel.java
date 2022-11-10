package app.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.App;
import app.PlayMP3;
import app.database.Database;
import app.model.WordCard;

public class AddCardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static JLabel soundUrlLabel = new JLabel();
	
	private static String currentWord = null; 
	private static ArrayList<String> urlList = null;
	private static int listIndex = 0;

	public AddCardPanel() {
		var wordField = new JTextField(20);
		wordField.setFont(new Font("Arial", Font.PLAIN, 30));
		
		var transcField = new JTextField(20);
		transcField.setFont(new Font("Arial", Font.PLAIN, 30));
		
		//var translField = new JTextField(80);
		var translField = new JTextArea("", 4, 40);
		translField.setFont(new Font("Arial", Font.PLAIN, 30));
		translField.setLineWrap(true);
		
		var exampleField = new JTextField(40);
		exampleField.setFont(new Font("Roman", Font.PLAIN, 30));
		
		var scrollPane = new JScrollPane(soundUrlLabel);
		//scrollPane.setSize(100, 100);
		//scrollPane.setSize(new Dimension(150, 410));
		scrollPane.setPreferredSize(new Dimension(950, 110));
		
		var playButton = new JButton("Play");
		playButton.addActionListener(arg -> {
			String word = wordField.getText();
			if(word.equals(currentWord)) {
				listIndex++;
				if(listIndex == urlList.size()) {
					listIndex = 0;
				}
				playList();
			} else {
				currentWord = word;
				System.out.println(word);
				String urls = null;
				try {
					urls = Database.getUrlsList(word);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				var array = urls.split("\\+++");
				//urlList = (ArrayList<String>) Arrays.asList(array);
				urlList = new ArrayList<>();
				for(var url: array) {
					urlList.add(url);
				}
				//urlList.remove(0);
				listIndex = 0;
				playList();
				//PlayMP3.play(array[0]);
			}
		});
		
		var delButton = new JButton("Delete");
		delButton.addActionListener(arg -> {
			//System.out.println("delete");
			urlList.remove(listIndex);
			if(listIndex == urlList.size()) {
				listIndex = 0;
			}
			playList();
		});
		
		var saveButton = new JButton("Save");
		saveButton.addActionListener(arg -> {
			System.out.println("save!");
			String transc = transcField.getText();
			String transl = translField.getText();
			String example = exampleField.getText();
			var card = new WordCard(currentWord, transc, transl, example, urlList);
			System.out.println(card);
			try {
				Database.saveCard(card);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		
		var tableModel = new CardTableModel(App.cardList);
		var table = new JTable(tableModel);
		//table.setAutoResizeMode(-1);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		table.getColumnModel().getColumn(3).setPreferredWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(6).setPreferredWidth(400);
		table.getColumnModel().getColumn(7).setPreferredWidth(400);
		//table.getColumnModel().getColumn(0).setMinWidth(10);
		var tableScrollPane = new JScrollPane(table);
		
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
		//add(addButton, gc);
		add(delButton, gc);
		
		gc.gridwidth = 4;
		gc.gridx = 0;
		gc.gridy++;
		//gc.anchor = GridBagConstraints.BOTH;
		//gc.anchor = GridBagConstraints.HORIZONTAL;
		add(translField, gc);
		
		gc.gridy++;
		add(exampleField, gc);
		
		gc.gridy++;
		//add(soundUrlField, gc);
		//add(soundUrlLabel, gc);
		add(scrollPane, gc);
		
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		add(saveButton, gc);
		
		gc.gridy++;
		//gc.gridx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(tableScrollPane, gc);
	}
	
	private static void playList() {
		PlayMP3.play(urlList.get(listIndex));
		var builder = new StringBuilder("<html>");
		for(int i = 0; i < urlList.size(); i++) {
			if(i == listIndex) {
				builder.append("<p style='color:blue'>");
			} else {
				builder.append("<p>");
			}
			builder.append(urlList.get(i)).append("</p>");
		}
		builder.append("</html>");
		soundUrlLabel.setText(builder.toString());
	}
	
}
