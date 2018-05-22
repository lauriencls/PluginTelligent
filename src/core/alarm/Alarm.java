package core.alarm;

import model.Message;
import java.time.Instant;
import core.model.Model;

public class Alarm extends Model {
	private String uniqueName;
	private Message message;
	private Instant triggerDateTime;
	private boolean isActive;
	
	/**
	 * Constructeur d'une alarme
	 * @param uniqueName : Nom de l'alarme
	 * @param message : Message li�
	 * @param triggerDateTime : Date de d�clenchement
	 * @param isActive : D�termine si l'alarme est active
	 */
	public Alarm(String uniqueName, Message message, Instant triggerDateTime, boolean isActive) {
		super(uniqueName);
		this.message = message;
		this.triggerDateTime = triggerDateTime;
		this.isActive = isActive;
	}
	
	public Alarm(String uniqueName) {
		super(uniqueName);
	}

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

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

//setupAlarm
//triggerAlarm
	
}
