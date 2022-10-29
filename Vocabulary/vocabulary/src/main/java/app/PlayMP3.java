package app;
import java.io.File;
import java.net.URL;

import jaco.mp3.player.MP3Player;



public class PlayMP3 {
	public static MP3Player mp3Player;
	
    //public static void main(String[] args) {
	//public static void audio(String urlString) {
	public static void play(String[] urlString) {
        try {
            //var url = new URL(urlString);

            //mp3Player = new MP3Player(url);
            mp3Player = new MP3Player();
            for(int i = 0; i < urlString.length; i++) {
            	var url = new URL(urlString[i]);
            	mp3Player.addToPlayList(url);
            }
            //mp3Player.setRepeat(true);
            //mp3Player.addToPlayList(url);
            
            mp3Player.play();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
	
	public static void repeat() {
		mp3Player.play();
	}
	
	public static void stop() {
		mp3Player.stop();
	}
}