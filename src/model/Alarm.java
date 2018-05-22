package model;

import java.time.Instant;

import core.model.Model;

public class Alarm extends Model {
	private Message message;
	private Instant triggerDateTime;

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

	public Alarm(String uniqueName, Message message, Instant triggerDateTime) {
		super(uniqueName);
		this.message = message;
		this.triggerDateTime = triggerDateTime;
	}

	public Alarm(String uniqueName) {
		super(uniqueName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

}
