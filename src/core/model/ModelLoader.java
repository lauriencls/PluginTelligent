package core.model;

import java.util.ArrayList;
import java.util.List;

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
	
	/*
	 * Returns a list of objects Model
	 */
	public abstract List<Model> getRessourceByUniqueName();
	
	
}
