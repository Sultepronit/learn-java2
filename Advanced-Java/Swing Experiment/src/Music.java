import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
    Clip clip;
    AudioInputStream sound;
    public void setFile(String soundFileName) {
        {
        	System.out.println(soundFileName);
            File file = new File(soundFileName);
            try {
				sound = AudioSystem.getAudioInputStream(file);
			} catch (UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				clip.open(sound);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
    }
    public void play() {
        clip.start();
    }
    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }
}