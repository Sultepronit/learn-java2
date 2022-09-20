//package com.javapointers.javase;
 
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class KeyListenerExample2 implements KeyListener, ActionListener {
 
    JFrame frame;
    //JTextField tf;
    JLabel lbl;
    JButton btn;
 
    public KeyListenerExample2() {
        frame = new JFrame();
        lbl = new JLabel();
        //tf = new JTextField(15);
        var tf = new JLabel("Listener");
        tf.setFocusable(true);
        tf.addKeyListener(this);
        btn = new JButton("Clear");
        btn.addActionListener(this);
        JPanel panel = new JPanel();
        panel.add(tf);
        panel.add(btn);
 
        frame.setLayout(new BorderLayout());
        frame.add(lbl, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }
 
    @Override
    public void keyTyped(KeyEvent ke) {
        lbl.setText("You have typed "+ke.getKeyChar());
    }
 
    @Override
    public void keyPressed(KeyEvent ke) {
        lbl.setText("You have pressed "+ke.getKeyChar());
    }
 
    @Override
    public void keyReleased(KeyEvent ke) {
        lbl.setText("You have released "+ke.getKeyChar());
    }
 
    @Override
    public void actionPerformed(ActionEvent ae) {
        //tf.setText("");
    }
 
    public static void main(String args[]) {
        new KeyListenerExample2();
    } 
}