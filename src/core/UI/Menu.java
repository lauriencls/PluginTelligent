package core.UI;

public abstract class Menu {
	private String uniqueName;
	private UserInterface userInterface;

	public Menu(String uniqueName, UserInterface userInterface) {
		super();
		this.uniqueName = uniqueName;
		this.userInterface = userInterface;
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
	
	public abstract void draw();
}
