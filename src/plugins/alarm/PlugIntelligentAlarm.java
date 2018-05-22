package plugins.alarm;

import java.awt.Frame;
import java.awt.Toolkit;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.UI.Body;
import core.model.AlarmLoader;
import model.Alarm;
import model.Message;

public class PlugIntelligentAlarm extends AlarmLoader {

	Body body;
	
	/**
	 * Constructeur de la classe
	 */
	public PlugIntelligentAlarm() {
		super();
	}

	@Override
	public Alarm createAlarm(Message message, Instant triggerDateTime, boolean isActive) {
		Alarm alarm = new Alarm(message, triggerDateTime, isActive);
		triggerAlarm(alarm);
		return alarm;
	}

	@Override
	public void setupAlarmTriggerDate(Alarm alarm, Instant triggerDateTime) {
			alarm.setTriggerDateTime(triggerDateTime);
	}

	@Override
	public void triggerAlarm(Alarm alarm) {
		Thread t = new Thread();
		t.start();
		if(!alarm.getTriggerDateTime().isBefore(Instant.now(Clock.systemUTC()))) {
			while(!alarm.getTriggerDateTime().equals(Instant.now(Clock.systemUTC())))
			{
			}
					
			//Sonnerie de l'alarme
			Toolkit.getDefaultToolkit().beep();
			System.out.println(alarm.getMessage().toString());
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, alarm.getMessage().getTitle() + " - " + alarm.getMessage().getBody(), "RAPPEL", JOptionPane.PLAIN_MESSAGE);
			t.stop();	
		}
		
		
	}

	@Override
	public void changeActivationAlarm(Alarm alarm) {
		alarm.setActive(!alarm.isActive());
		if(alarm.isActive()) {
			triggerAlarm(alarm);
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
