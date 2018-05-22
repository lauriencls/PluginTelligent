package core.model;

import java.util.List;

// Les classes étendant ModelLoader doivent servir à récupérer les instances du modèle et à les sérialiser après leur modification.
// Pour chaque classe du modèle spécifique à votre plugin, votre ModelLoader spécifique devra disposer d'une liste d'instance de ces dernières.
// Ces listes devront être statiques
public abstract class ModelLoader {
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
	
	// Dans cette méthode doivent être récupérés toutes les instances du modèle, peu importe leur provenance
	public abstract void load();

	// Dans cette méthode doivent être sauvegardés toutes les instances du modèle, peu importe la destination
	public abstract void save();
	
	// Selon le string passé en paramètre renvoyé la liste correspondante
	//permet d'accéder depuis l'extérieur et sans connaître l'implémentation exacte aux listes d'instances du modèle
	public abstract List<Model> getRessourceByName(String className);
	
	
}
