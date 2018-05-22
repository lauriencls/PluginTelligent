package core.model;

import java.util.List;

public abstract class ModelLoader {
	// TODO � commenter
	private String uniqueName;
	
	public ModelLoader(String uniqueName) {
		super();
		this.uniqueName = uniqueName;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	
	public abstract void load();
	
	public abstract void save();
	
	/*
	 * Returns a list of objects Model
	 */
	public abstract List<Model> getRessourceByName(String className);
	
	
}
