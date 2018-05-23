package loader;

import java.util.List;

public class PluginDescriptor {
	private String type;
	private String nom;
	private String className;
	private Class<?> interf;
	//List of class Name
	private List<String> dependencies;
	private boolean loaded;

	public PluginDescriptor(String type, String nom, String className, Class<?> interf, List<String> dependencies) {
		super();
		this.type = type;
		this.nom = nom;
		this.className = className;
		this.interf = interf;
		this.dependencies = dependencies;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Class<?> getInterf() {
		return interf;
	}
	public void setInterf(Class<?> interf) {
		this.interf = interf;
	}

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<String> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}
	public boolean isLoaded() {
		return loaded;
	}
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
}
