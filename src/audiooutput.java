import java.io.File; 
import java.io.IOException; 
  
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;

public class audiooutput 
{
	private Clip clip = null;
	private AudioInputStream audioInputStream = null;
	
	public audiooutput(String soundfile) throws UnsupportedAudioFileException, IOException, LineUnavailableException //https://www.geeksforgeeks.org/play-audio-file-using-java/
	{
		audioInputStream = AudioSystem.getAudioInputStream(new File(soundfile).getAbsoluteFile()); 
  
        clip = AudioSystem.getClip(); 
	}
	
	public void Start() throws LineUnavailableException, IOException
	{
        clip.open(audioInputStream); 
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
		clip.start();
	}
	
	public void Stop() 
	{
        clip.stop(); 
        clip.close();
        audioInputStream = null;
	}
}
