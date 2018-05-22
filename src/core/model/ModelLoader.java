package core.model;

import java.util.List;

public abstract class ModelLoader {
	// TODO � commenter
	private String uniqueName;
	
	public ModelLoader() {
		super();
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	
	public abstract void load();
	
	public abstract void save(List<Model> objects);
	
	/*
	 * Returns a list of objects Model
	 */
	public abstract List<Model> getRessourceByName(String className);
	
	
}
