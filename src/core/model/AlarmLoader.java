package core.model;

import java.time.Instant;

import model.Message;

/**
 * M�thode de chargement et de gestion des alarmes
 */
public abstract class AlarmLoader {
	
	/**
	 * Cr��e l'alarme dont les informations sont donn�es en param�tre
	 */
	public abstract void createAlarm(Message message, Instant triggerDateTime, boolean isActive);
	
	/**
	 * Param�tre l'alarme dont les informations sont donn�es en param�tre
	 */
	public abstract void setupAlarm(Message message, Instant triggerDateTime, boolean isActive);
	
	/**
	 * D�clenche l'alarme dont la date de d�clenchement est donn�e
	 */
	public abstract void triggerAlarm(Instant triggerDateTime);

}
