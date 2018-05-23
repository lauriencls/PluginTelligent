package core.model;

import java.time.Instant;

import model.Alarm;
import model.Message;

/**
 * Méthode de chargement et de gestion des alarmes
 */
public abstract class AlarmLoader {
	
	/**
	 * Créée l'alarme dont les informations sont données en paramètre
	 */
	public Alarm createAlarm(Message message, Instant triggerDateTime, boolean isActive) {
		Alarm alarm = new Alarm(message, triggerDateTime, isActive);
		if(alarm.isActive()) {
			triggerAlarm(alarm);	
		}
		return alarm;
	}
	
	/**
	 * Modifie l'heure de déclenchement de l'alarme
	 */
	public void setupAlarmTriggerDate(Alarm alarm, Instant triggerDateTime) {
		if(Instant.now().isAfter(triggerDateTime)) {
			alarm.setTriggerDateTime(triggerDateTime);	
		} else {
			System.out.println("La date donnée est inférieure à la date actuelle");
		}	
	}
	
	/**
	 * Change l'état d'activation de l'alarme (activée/désactivée)
	 */
	public void changeActivationAlarm(Alarm alarm) {
		alarm.setActive(!alarm.isActive());
		if(alarm.isActive()) {
			triggerAlarm(alarm);
		}
	}
	
	
	/**
	 * Déclenche l'alarme dont la date de déclenchement est donnée
	 */
	public abstract void triggerAlarm(Alarm alarm);

}
