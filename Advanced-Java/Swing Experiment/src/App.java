import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;    
class TableExample {    
    JFrame f;    
    TableExample(){    
    f=new JFrame();    
    String data[][]={ {"101","Amit","670000"},    
                          {"102","Jai","780000"},    
                          {"101","Sachin","700000"}};    
    String column[]={"ID","NAME","SALARY"};         
    JTable jt=new JTable(data,column);    
    jt.setBounds(30,40,200,300);          
    JScrollPane sp=new JScrollPane(jt);    
    f.add(sp);          
    f.setSize(300,400);    
    f.setVisible(true);    
}     
   
}  


public class App {

	public static void main(String[] args) {
		 new TableExample();
		 //var music = new Music();
		 //music.setFile("https://file-examples.com/storage/fe7d3a0d44631509da1f416/2017/11/file_example_MP3_5MG.mp3");
		 //music.setFile("file_example.mp3");
		 //music.play();
		 //new Music2().play("");
		 
		 //Music2.play("");
		 
		 /*try {
			Music2.play();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 
		 try {
			new Music2().play2();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
