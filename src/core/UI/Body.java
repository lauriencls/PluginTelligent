package core.UI;

public abstract class Body {
	private String uniqueName;
	private UserInterface userInterface;

	public Body(String uniqueName, UserInterface userInterface) {
		super();
		this.uniqueName = uniqueName;
		this.userInterface = userInterface;
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
	
	public abstract void drawBody();
	
	public void draw(){
		drawBody();
	}
	
	
}
