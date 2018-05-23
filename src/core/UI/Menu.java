package core.UI;

public abstract class Menu {
	private String uniqueName;
	protected UserInterface userInterface;

	public Menu() {
	}

	public Menu(String uniqueName) {
		super();
		this.uniqueName = uniqueName;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	
	public void draw(){
		drawMenu();
	}
	
	public abstract void drawMenu();
}
