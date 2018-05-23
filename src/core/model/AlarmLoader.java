package core.model;

import java.time.Instant;

import model.Alarm;
import model.Message;

/**
 * M�thode de chargement et de gestion des alarmes
 */
public abstract class AlarmLoader {
	
	/**
	 * Cr��e l'alarme dont les informations sont donn�es en param�tre
	 */
	public Alarm createAlarm(Message message, Instant triggerDateTime, boolean isActive) {
		Alarm alarm = new Alarm(message, triggerDateTime, isActive);
		if(alarm.isActive()) {
			triggerAlarm(alarm);	
		}
		return alarm;
	}
	
	/**
	 * Modifie l'heure de d�clenchement de l'alarme
	 */
	public void setupAlarmTriggerDate(Alarm alarm, Instant triggerDateTime) {
		if(Instant.now().isAfter(triggerDateTime)) {
			alarm.setTriggerDateTime(triggerDateTime);	
		} else {
			System.out.println("La date donn�e est inf�rieure � la date actuelle");
		}	
	}
	
	/**
	 * Change l'�tat d'activation de l'alarme (activ�e/d�sactiv�e)
	 */
	public void changeActivationAlarm(Alarm alarm) {
		alarm.setActive(!alarm.isActive());
		if(alarm.isActive()) {
			triggerAlarm(alarm);
		}
	}
	
	
	/**
	 * D�clenche l'alarme dont la date de d�clenchement est donn�e
	 */
	public abstract void triggerAlarm(Alarm alarm);

}
