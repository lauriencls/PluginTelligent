package plugins.alarm;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.model.AlarmLoader;
import model.Alarm;

public class RobinAlarm extends AlarmLoader {

	@Override
	public void triggerAlarm(Alarm alarm) {
		if(alarm.isActive()){
		      try {
		          
		          File soundFile = new File("/Users/robin_delaporte/Documents/workspace/PluginTelligent/src/ressource/bark.wav"); //you could also get the sound file with an URL
		          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
		         Clip clip = AudioSystem.getClip();
		         clip.open(audioIn);
		         clip.start();
		         JFrame frame = new JFrame();
		            JOptionPane.showMessageDialog(frame, alarm.getMessage().getTitle() + " - " + alarm.getMessage().getBody(), "RAPPEL", JOptionPane.PLAIN_MESSAGE);

		      } catch (UnsupportedAudioFileException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      } catch (LineUnavailableException e) {
		         e.printStackTrace();
		      }   
		    }	}

}
