package app.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import app.model.WordCard;

public class CardTableModel extends AbstractTableModel {
	
	private static List<WordCard> cardList;
	
	public CardTableModel(List<WordCard> CardList) {
		this.cardList = CardList;
	}

	public int getRowCount() {
		return cardList.size();
	}

	public int getColumnCount() {
		return 8;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		var card = cardList.get(rowIndex);
		switch(columnIndex) {
		case 0: 
			return card.getId();
		case 1: 
			return card.getStatus();
		case 2:
			return card.getForward();
		case 3:
			return card.getBackward();
		case 4:
			return card.getWord();
		case 5:
			return card.getTransc();
		case 6: 
			return card.getTransl();
		case 7: 
			return card.getExample();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		/*switch (column) {
		case 0:
			return "id";
		case 4:
			return "word";
		}*/
		return "";
	}
	
	

}
