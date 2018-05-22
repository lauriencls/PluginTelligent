package core.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import model.Message;

public abstract class Body {
	private String uniqueName;
	private String title;
	private String icon;
	protected UserInterface userInterface;

	public Body() {
	}

	public Body(String uniqueName) {
		super();
		this.uniqueName = uniqueName;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	
	public UserInterface getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}

	protected void drawBody(){
		
		drawChannelPanel();
		drawListMessages();
		drawTextEntry();
	}
	
	protected abstract void drawTextEntry();
	
	protected abstract void drawListMessages();
	
	protected abstract void drawChannelPanel();
	
	public abstract void addMessage(Message message);
	
	public void draw(){		
		drawBody();
		
		
		        
	}

	public UserInterface getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}
	
	
}
