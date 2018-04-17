package loaders;

public class PluginDescriptor {
	private String type;
	private String nom;
	private String className;
	private Class<?> interf;

	public PluginDescriptor(String type, String nom, String className, Class<?> interf) {
		super();
		this.type = type;
		this.nom = nom;
		this.className = className;
		this.interf = interf;
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
}
