package core.model;

import java.time.Instant;

import model.Message;

/**
 * Méthode de chargement et de gestion des alarmes
 */
public abstract class AlarmLoader {
	
	/**
	 * Créée l'alarme dont les informations sont données en paramètre
	 */
	public abstract void createAlarm(Message message, Instant triggerDateTime, boolean isActive);
	
	/**
	 * Paramètre l'alarme dont les informations sont données en paramètre
	 */
	public abstract void setupAlarm(Message message, Instant triggerDateTime, boolean isActive);
	
	/**
	 * Déclenche l'alarme dont la date de déclenchement est donnée
	 */
	public abstract void triggerAlarm(Instant triggerDateTime);

}
