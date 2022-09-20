import jaco.mp3.player.MP3Player;

import java.io.File;

public class PlayMP3 {
    public static void main(String[] args) {

        try {

            File f = new File("file_example.mp3");

            MP3Player mp3Player = new MP3Player(f);
            mp3Player.play();

            while (!mp3Player.isStopped()) {
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}