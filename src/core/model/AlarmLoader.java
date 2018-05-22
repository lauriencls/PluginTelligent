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
	public abstract Alarm createAlarm(Message message, Instant triggerDateTime, boolean isActive);
	
	/**
	 * Modifie l'heure de déclenchement de l'alarme
	 */
	public abstract void setupAlarmTriggerDate(Alarm alarm, Instant triggerDateTime);
	
	/**
	 * Change l'état d'activation de l'alarme (activée/désactivée)
	 */
	public abstract void changeActivationAlarm(Alarm alarm);
	
	
	/**
	 * Déclenche l'alarme dont la date de déclenchement est donnée
	 */
	public abstract void triggerAlarm();

}
