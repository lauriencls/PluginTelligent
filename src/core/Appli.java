package core;
import java.util.List;

import core.UI.UserInterface;
import core.model.Personne;
import loader.PluginDescriptor;
import loader.PluginsLoader;

public abstract class Appli {
	private UserInterface userInterface;
	

    public Appli() {
    	PluginsLoader pl = new PluginsLoader();
        //List<PluginDescriptor> afficheurs = pl.getAllPluginsByType("afficheur", AfficheurAbstrait.class);
        //displayPlugins(afficheurs);
        //afficheur = (AfficheurAbstrait) pl.getPlugin(afficheurs.get(0));
    }
    
    private void displayPlugins(List<PluginDescriptor> pds){
    	for(PluginDescriptor pd :
    		pds){
    		System.out.print(pd.getNom());
    	}
    }

    public void run(){
        Personne p = new Personne("toto", "nantes");
        //afficheur.afficher(p.toString());
    }

}
