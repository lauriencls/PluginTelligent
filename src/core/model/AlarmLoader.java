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
	public abstract Alarm createAlarm(Message message, Instant triggerDateTime, boolean isActive);
	
	/**
	 * Modifie l'heure de d�clenchement de l'alarme
	 */
	public abstract void setupAlarmTriggerDate(Alarm alarm, Instant triggerDateTime);
	
	/**
	 * Change l'�tat d'activation de l'alarme (activ�e/d�sactiv�e)
	 */
	public abstract void changeActivationAlarm(Alarm alarm);
	
	
	/**
	 * D�clenche l'alarme dont la date de d�clenchement est donn�e
	 */
	public abstract void triggerAlarm();

}
