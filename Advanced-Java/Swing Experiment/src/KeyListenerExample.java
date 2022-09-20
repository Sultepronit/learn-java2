// importing the necessary libraries  
import java.awt.*;    
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JTextField;    
// class which inherits Frame class and implements KeyListener interface  
public class KeyListenerExample extends Frame implements KeyListener {    
private static final long serialVersionUID = 1L;
	// object of Label and TextArea  
    Label l;    
    TextArea area;    
// class constructor  
    KeyListenerExample() {    
         // creating the label   
        l = new Label();    
// setting the location of label  
        l.setBounds (20, 50, 200, 20);    
// creating the text area  
        //l.addKeyListener(this);    
        area = new TextArea();    
// setting location of text area  
        area.setBounds (20, 80, 300, 300);    
// adding KeyListener to the text area   
        area.addKeyListener(this);   
        var nameLabel = new JLabel("おとり");
        var nameField = new JTextField(15);//15 characters long
 // adding label and text area to frame  
   add(l);  
   add(area);    
   add(nameLabel);
   add(nameField);
// setting size, layout and visibility of frame  
        setSize (400, 400);    
        setLayout (null);    
        setVisible (true);    
    }    
// even if we do not define the interface methods, we need to override them  
    public void keyPressed(KeyEvent e) {}    
// overriding the keyReleased() method of KeyListener interface   
    public void keyReleased (KeyEvent e) {    
// defining a string which is fetched by the getText() method of TextArea class  
        String text = area.getText();    
// splitting the string in words  
        String words[] = text.split ("\\s");    
// printing the number of words and characters of the string   
        l.setText ("Words: " + words.length + " Characters:" + text.length());    
    }    
    public void keyTyped(KeyEvent e) {System.out.println("!!!"); }    
  // main method  
    public static void main(String[] args) {    
        new KeyListenerExample();    
    }    
}    