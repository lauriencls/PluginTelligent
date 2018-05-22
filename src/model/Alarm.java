package model;

import java.time.Instant;
import core.model.Model;

/**
 * Classe correspondant à une alarme. Une alarme est liée à un message, et est déclenchée à une date si elle est active
 */
public class Alarm extends Model {
	private Message message;
	private Instant triggerDateTime;
	private boolean isActive;
	
	/**
	 * Constructeur d'une alarme
	 * @param uniqueName : Nom de l'alarme
	 * @param message : Message lié
	 * @param triggerDateTime : Date de déclenchement
	 * @param isActive : Détermine si l'alarme est active
	 */
	public Alarm(Message message, Instant triggerDateTime, boolean isActive) {
		setupAlarm(message, triggerDateTime, isActive);
	}
	
	public Alarm(){}
	

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Instant getTriggerDateTime() {
		return triggerDateTime;
	}

	public void setTriggerDateTime(Instant triggerDateTime) {
		this.triggerDateTime = triggerDateTime;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * Paramétrage de l'alarme
	 * @param message : Message affiché par l'alarme
	 * @param triggerDateTime : Date de déclencheemnt de l'alarme
	 * @param isActive : Détermine si l'alarme est active
	 */
	private void setupAlarm(Message message, Instant triggerDateTime, boolean isActive) {
		this.message = message;
		this.triggerDateTime = triggerDateTime;
		this.isActive = isActive;
	}


	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}
	
}
