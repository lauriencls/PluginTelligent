package model;

import java.util.Date;

import core.model.Model;

public class Alarm extends Model {
	private Message message;
	private Date triggerDateTime;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Date getTriggerDateTime() {
		return triggerDateTime;
	}

	public void setTriggerDateTime(Date triggerDateTime) {
		this.triggerDateTime = triggerDateTime;
	}

	public Alarm(String uniqueName, Message message, Date triggerDateTime) {
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
