package application;
import java.io.File;
import java.net.URL;

import jaco.mp3.player.MP3Player;



public class PlayMP3 {
	public static MP3Player mp3Player;
	
    //public static void main(String[] args) {
	public static void audio(String urlString) {

        try {

            //File f = new File("file_example.mp3");
            //var url = new URL("https://assets.languagepod101.com/dictionary/japanese/audiomp3.php?kana=%E3%81%99&kanji=%E5%B7%A3");
            var url = new URL(urlString);

            //MP3Player mp3Player = new MP3Player(f);
            MP3Player mp3Player = new MP3Player(url);
            mp3Player.play();
            //mp3Player.

            /*while (!mp3Player.isStopped()) {
                Thread.sleep(5000);
            }*/
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
	
	public static void play() {
		mp3Player.play();
	}
}