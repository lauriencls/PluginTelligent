package core.UI;

import java.awt.FlowLayout;

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
	
	protected void drawBody(){
		drawChannelPanel();
		drawListMessages();
		drawTextEntry();
	}
	
	protected abstract void drawTextEntry();
	
	protected abstract void drawListMessages();
	
	protected abstract void drawChannelPanel();
	
	protected abstract void addMessage(Message message);
	
	public void draw(){
		this.userInterface.setLayout(new FlowLayout());
		
		drawBody();
		
		this.userInterface.setTitle("PlugIntelligent");  
		this.userInterface.setSize(250, 100);        
		this.userInterface.setVisible(true);         
	}

	public UserInterface getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}
	
	
}
