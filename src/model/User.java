package model;

import core.model.Model;

public class User extends Model {
	
	private String login;

	public User(String login) {
		super();
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

}
