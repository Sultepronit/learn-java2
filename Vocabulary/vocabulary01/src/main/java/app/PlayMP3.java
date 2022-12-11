package app;
import java.net.URL;

import jaco.mp3.player.MP3Player;



public class PlayMP3 {
	public static MP3Player mp3Player;

	public static void play(String urlString) {
		if(urlString.equals("---")) return;
        try {

            mp3Player = new MP3Player();
            //System.out.println("Before");
            var url = new URL(urlString);
            //System.out.println("After");
            mp3Player.addToPlayList(url);
            //System.out.println("After2");
            
            mp3Player.play();
            //System.out.println("After3");
            
        } catch (Exception e) {
            //System.err.println(e.getMessage());
        	System.out.println("Cannot play a sound...");
        }
    }
	
	public static void repeat() {
		mp3Player.play();
	}
	
	public static void stop() {
		mp3Player.stop();
	}
}