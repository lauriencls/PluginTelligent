package model;

import core.model.Model;
import java.time.Instant;

public class Message extends Model {
	private String title;
	private String body;
	private Instant createdTime;

	public Message(String title, String body, Instant createdTime) {
		this.title = title;
		this.body = body;
		this.createdTime = createdTime;
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

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

 
}
