package model;

import core.model.Model;

public class Message extends Model {
	private String title;
	private String body;
	private String createdTime;

	public Message(String uniqueName, String title, String body, String createdTime) {
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

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

 
}
