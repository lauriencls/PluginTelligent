package core;
import java.util.List;

import core.interfaces.AfficheurAbstrait;
import core.model.Personne;
import loaders.PluginsLoader;
import loaders.PluginDescriptor;

public class Appli {
    public AfficheurAbstrait getAfficheur() {
        return afficheur;
    }

    public void setAfficheur(AfficheurAbstrait afficheur) {
        this.afficheur = afficheur;
    }

    public Appli() {
        try {
        	PluginsLoader pl = new PluginsLoader();
            List<PluginDescriptor> afficheurs = pl.getAllPluginsByType("afficheur", AfficheurAbstrait.class);
            displayPlugins(afficheurs);
            afficheur = (AfficheurAbstrait) pl.getPlugin(afficheurs.get(0));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    
    private void displayPlugins(List<PluginDescriptor> pds){
    	for(PluginDescriptor pd :
    		pds){
    		System.out.print(pd.getNom());
    	}
    }

    private AfficheurAbstrait afficheur;

    public void run(){
        Personne p = new Personne("toto", "nantes");
        afficheur.afficher(p.toString());
    }

}
