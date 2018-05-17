package core.model;

public abstract class Model {
	private String uniqueName;

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	public Model(String uniqueName) {
		super();
		this.uniqueName = uniqueName;
	}
	
	public abstract void load();
}
