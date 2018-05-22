package plugins.alarm;

import java.time.Instant;

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
	public void triggerAlarm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeActivationAlarm(Alarm alarm) {
		alarm.setActive(!alarm.isActive());
	}
	
	public static void main(String[] args){
		PlugIntelligentAlarm pia = new PlugIntelligentAlarm();
		Instant dateSonnerie = Instant.now();
		Message message = new Message("test", "Je suis un test", dateSonnerie);
		Alarm alarm = pia.createAlarm(message, dateSonnerie, false);
		pia.setupAlarmTriggerDate(alarm, dateSonnerie.plusSeconds(50));
		pia.changeActivationAlarm(alarm);
	}
	

}
