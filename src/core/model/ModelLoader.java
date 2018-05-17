package core.model;

public abstract class ModelLoader {
	private String uniqueName;

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	public ModelLoader(String uniqueName) {
		super();
		this.uniqueName = uniqueName;
	}
	
	public abstract void load();
}
