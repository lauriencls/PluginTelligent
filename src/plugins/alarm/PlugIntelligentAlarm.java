package plugins.alarm;

import java.awt.Toolkit;
import java.time.Clock;
import java.time.Instant;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.model.AlarmLoader;
import model.Alarm;
import model.Message;

public class PlugIntelligentAlarm extends AlarmLoader {
	
	/**
	 * Constructeur de la classe
	 */
	public PlugIntelligentAlarm() {
		super();
	}	

	@Override
	public void triggerAlarm(Alarm alarm) {
		Thread t = new Thread();
		t.start();
		if(!alarm.getTriggerDateTime().isBefore(Instant.now(Clock.systemUTC()))) {
			while(!alarm.getTriggerDateTime().equals(Instant.now(Clock.systemUTC())))
			{
				//On attend d'avoir atteint la date à laquelle l'alarme doit sonner
			}
					
			//Sonnerie de l'alarme
			Toolkit.getDefaultToolkit().beep();
			System.out.println(alarm.getMessage().toString());
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, alarm.getMessage().getTitle() + " - " + alarm.getMessage().getBody(), "RAPPEL", JOptionPane.PLAIN_MESSAGE);
		}
		
		
	}
	
	public static void main(String[] args){
		PlugIntelligentAlarm pia = new PlugIntelligentAlarm();
		Instant dateSonnerie = Instant.now();
		Message message = new Message("test", "Je suis un test", dateSonnerie);
		Alarm alarm = pia.createAlarm(message, dateSonnerie, false);
		pia.setupAlarmTriggerDate(alarm, dateSonnerie.plusSeconds(5));
		pia.changeActivationAlarm(alarm);
		pia.triggerAlarm(alarm);
	}
	

}
