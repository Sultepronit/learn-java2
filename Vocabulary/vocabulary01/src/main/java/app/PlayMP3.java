package app;
import java.io.File;
import java.net.URL;

import jaco.mp3.player.MP3Player;



public class PlayMP3 {
	public static MP3Player mp3Player;
	
	//public static void play(String[] urlString) {
	public static void play(String urlString) {
        try {

            mp3Player = new MP3Player();
            /*for(int i = 0; i < urlString.length; i++) {
            	var url = new URL(urlString[i]);
            	mp3Player.addToPlayList(url);
            }*/
            var url = new URL(urlString);
            mp3Player.addToPlayList(url);
            
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