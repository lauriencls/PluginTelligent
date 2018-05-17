package plugins;

import core.UI.AfficheurAbstrait;

public class Afficheur extends AfficheurAbstrait {
    public Afficheur() {
    }

    @Override
    public void afficher(String p) {
        System.out.print(p);
    }
}