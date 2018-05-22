package model;

import core.model.Model;
import java.time.Instant;

public class Message extends Model {
	private String title;
	private String body;
	private Instant createdTime;

	public Message(String uniqueName, String title, String body, Instant createdTime) {
		super(uniqueName);
		this.title = title;
		this.body = body;
		this.createdTime = createdTime;
	}

	public Message(String uniqueName) {
		super(uniqueName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Instant getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Instant createdTime) {
		this.createdTime = createdTime;
	}

 
}
