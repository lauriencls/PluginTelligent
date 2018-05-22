package plugins.alarm;

import java.awt.Toolkit;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

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
	public Alarm createAlarm(Message message, Instant triggerDateTime, boolean isActive) {
		return new Alarm(message, triggerDateTime, isActive);
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
		pia.setupAlarmTriggerDate(alarm, dateSonnerie.plusSeconds(50));
		pia.changeActivationAlarm(alarm);
		pia.triggerAlarm(alarm);
	}
	

}
