package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MyPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public MyPanel() {
		setBackground(Color.white);
		
		//var icon = createImageIcon("images/middle.gif");
		JLabel image = new JLabel();
		image.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
		
		var html = "<html><p style='font-size:2em; font-family: \"Chilanka\"; "
				+ "text-align: center'>before</p> "
				+ "<img width='150px' src=\"http://www.yosida.com/images/kanji/984C.gif\">"
				+ "after</html>";
		var imageLabel = new JLabel(html);
		
		var nameLabel = new JLabel("おとり");
		nameLabel.setFont(new Font("VL PGothik", 40, 90));
		var passLabel = new JLabel("媒鳥");
		passLabel.setFont(new Font("PMincho", 40, 90));
		//nameLabel.setText("new!");
		setLayout(new BorderLayout());
		add(passLabel, BorderLayout.NORTH);
		add(nameLabel, BorderLayout.CENTER);
		add(image, BorderLayout.SOUTH);
		add(imageLabel, BorderLayout.EAST);
	}
}
