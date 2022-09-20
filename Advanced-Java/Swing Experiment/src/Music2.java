import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music2 {
	/*AudioInputStream audioIn = AudioSystem.getAudioInputStream(Music2.class.getResource("music.wav"));
	Clip clip = AudioSystem.getClip();
	clip.open(audioIn);
	clip.start();*/
	
	
	public static void play(String filename)
	{
		filename = "/home/step/Downloads/file_example.mp3";
		//filename = "https://file-examples.com/storage/fe7d3a0d44631509da1f416/2017/11/file_example_MP3_5MG.mp3";
		//filename = "text.txt";
		
	  try
	  {
	    Clip clip = AudioSystem.getClip();
	    clip.open(AudioSystem.getAudioInputStream(new File(filename)));
	    clip.start();
	  }
	  catch (Exception exc)
	  {
	    exc.printStackTrace(System.out);
	  }
	}
	public static void play() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		URL url2 = new URL("https://file-examples.com/storage/fe7d3a0d44631509da1f416/2017/11/file_example_MP3_5MG.mp3");
		Clip clip2 = AudioSystem.getClip();
		AudioInputStream ais2 = AudioSystem.getAudioInputStream(url2);
		clip2.open(ais2);
		clip2.start();
	}
	
	public void play2() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		System.out.println("ke!");
		/*SoundPlayerUsingJavaZoom player = new SoundPlayerUsingJavaZoom();
		{
			  AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("file_example.mp3"));
			  Clip clip = AudioSystem.getClip();
			  clip.open(audioInputStream);
			  System.out.println("ke!!!");
			  clip.start( );
		}*/
		
	}
}
