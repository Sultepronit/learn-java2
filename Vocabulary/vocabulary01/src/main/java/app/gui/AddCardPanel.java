package app.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.PlayMP3;
import app.controllers.Controller;
import app.database.Database;
import app.model.WordCard;

public class AddCardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//private static List<WordCard> cardList;
	
	private static JLabel soundUrlLabel = new JLabel();
	private static CardTableModel tableModel;// = new CardTableModel(cardList);
	
	private static String currentWord = null; 
	private static ArrayList<String> urlList = null;
	private static int listIndex = 0;
	private static int editedCardId;

	public AddCardPanel(List<WordCard> cardList) {
		editedCardId = 0;
		
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
		//scrollPane.
		//scrollPane.setSize(100, 100);
		//scrollPane.setSize(new Dimension(150, 410));
		scrollPane.setPreferredSize(new Dimension(950, 110));
		//scrollPane.setMinimumSize(new Dimension(950, 110));
		
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
					System.out.println("Here1");
					e.printStackTrace();
				}
				System.out.println("Here2");
				var array = urls.split("\\+++");
				//urlList = (ArrayList<String>) Arrays.asList(array);
				urlList = new ArrayList<>();
				for(var url: array) {
					urlList.add(url);
				}
				listIndex = 0;
				System.out.println("Here3");
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
			//var card = new WordCard(currentWord, transc, transl, example, urlList);
			var card = new WordCard(editedCardId, currentWord, transc, transl, example, urlList);
			System.out.println(card);
			wordField.setText("");
			transcField.setText("");
			translField.setText("");
			exampleField.setText("");
			
			try {
				if(editedCardId == 0) Database.saveCard(card);
				else Database.editCard(card);
				Controller.refresh();
				tableModel.fireTableDataChanged();
				editedCardId = 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		
		tableModel = new CardTableModel(cardList);
		var table = new JTable(tableModel);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				//System.out.println(me);
				if(me.getClickCount() == 2) {
					JTable target = (JTable)me.getSource();
					int row = target.getSelectedRow();
					var card = cardList.get(row);
					/*System.out.println(row);
					System.out.println(card);*/
					wordField.setText(card.getWord());
					transcField.setText(card.getTransc());
					translField.setText(card.getTransl());
					exampleField.setText(card.getExample());
					//System.out.println(card.getId());
					editedCardId = card.getId();
				}
			}
		});
		
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
		//tableScrollPane.setPreferredSize(new Dimension(950, 910));
		
		setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		gc.gridwidth = 2;
		//gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.HORIZONTAL;
		//gc.anchor = GridBagConstraints.PAGE_START;
		add(wordField, gc);
		
		gc.gridwidth = 1;
		gc.gridx = 2;
		add(transcField, gc);
		
		/*gc.gridx++;
		add(playButton, gc);*/
		
		/*gc.gridx++;
		add(delButton, gc);*/
		//gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridwidth = 3;
		gc.gridx = 0;
		gc.gridy++;
		add(translField, gc);
		
		gc.gridy++;
		add(exampleField, gc);
		
		//gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridy++;
		add(scrollPane, gc);
		
		gc.fill = GridBagConstraints.VERTICAL;
		gc.gridwidth = 1;
		gc.gridy++;
		gc.gridx = 0;
		add(playButton, gc);
		
		gc.gridx++;
		add(delButton, gc);
		
		//gc.gridy++;
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.weightx = 0.1;
		add(saveButton, gc);
		
		gc.gridwidth = 3;
		gc.gridy++;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx = 2;
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
