package plugins;

import core.UI.AfficheurAbstrait;

public class MonAfficheur extends AfficheurAbstrait {
    @Override
    public void afficher(String p) {
        System.out.print("mon afficheur : " + p);
    }
}
