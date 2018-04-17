package plugins;

import core.interfaces.AfficheurAbstrait;

public class Afficheur extends AfficheurAbstrait {
    public Afficheur() {
    }

    @Override
    public void afficher(String p) {
        System.out.print(p);
    }
}