import core.interfaces.AfficheurAbstrait;
import loaders.ConfigLoader;

public class Appli {
    public AfficheurAbstrait getAfficheur() {
        return afficheur;
    }

    public void setAfficheur(AfficheurAbstrait afficheur) {
        this.afficheur = afficheur;
    }

    public Appli() {
        try {
            afficheur = (AfficheurAbstrait) new ConfigLoader().donnePlugin("afficheur", AfficheurAbstrait.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private AfficheurAbstrait afficheur;

    public void run(){
        Personne p = new Personne("toto", "nantes");
        afficheur.afficher(p.toString());
    }

}
