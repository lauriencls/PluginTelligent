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
	public abstract void createAlarm(String uniqueName, Message message, Instant triggerDateTime, boolean isActive);
	
	/**
	 * Paramètre l'alarme dont les informations sont données en paramètre
	 */
	public abstract void setupAlarm(String uniqueName, Message message, Instant triggerDateTime, boolean isActive);
	
	/**
	 * Retourne l'alarme correspondant au nom donné
	 */
	public abstract Alarm getAlarmByName(String privateName);
	
	/**
	 * Déclenche l'alarme dont la date de déclenchement est donnée
	 */
	public abstract void triggerAlarm(Instant triggerDateTime);

}
