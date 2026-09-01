package labyrinthe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author INFO Professors team
 */
public class Etage extends ArrayList<ISalle> implements IEtage {

    private int largeur;
    private int hauteur;
    private int num;

    public Etage() {
        largeur = 40;
        hauteur = 40;
        num = 1;
    }

    public Etage(int id) {
        this.num = id;
    }

    @Override
    public void charger(String file) throws IOException {
        this.clear();
        List<String> lignes = Files.readAllLines(Paths.get(file));
        // dimensions
        String entete = lignes.get(0);
        String[] mots = entete.split(" ");
        largeur = Integer.parseInt(mots[0]);
        hauteur = Integer.parseInt(mots[1]);
        lignes.remove(0);
        // salles
        for (String ligne : lignes) {
            mots = ligne.split(" ");
            ESalle type = giveTypeFromLetter(mots[3]);
            Salle salleActuelle = new Salle(this, Integer.parseInt(mots[0]),
                    Integer.parseInt(mots[1]), type);
            this.add(salleActuelle);
        }
    }

    private ESalle giveTypeFromLetter(String type) {
        switch (type) {
            case "N" :
                return ESalle.NORMALE;
            case "E" :
                return ESalle.ENTREE;
            case "S" :
                return ESalle.SORTIE;
            case "M":
                return ESalle.ESCALIER_MONTANT;
            case "D" :
                return ESalle.ESCALIER_DESCENDANT;
            default:
                return ESalle.NORMALE;
        }
    }

    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public int getHauteur() {
        return hauteur;
    }

    @Override
    public int getNum() {
        return num;
    }

}
