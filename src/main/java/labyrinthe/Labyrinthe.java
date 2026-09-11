package labyrinthe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import personnages.IPersonnage;

/**
 *
 * @author professor team
 */
public class Labyrinthe extends ArrayList<ISalle> implements ILabyrinthe {
    
    /**
     * Etage actuel du labyrinhe qui est affiché.
     */
    private IEtage etageCourant = new Etage();
    
    /**
     * Constructeur du labyrinthe
     */
    public Labyrinthe() {
        IEtage etage1 = new Etage(1);
        IEtage etage2 = new Etage(2);
        try {
            etage1.charger("/mnt/netta/users/llaignel/Documents/projetRepriseS3/2026-projetreprise-laignelluc/etages/etage1N.txt");
            this.addAll(etage1);
            etage2.charger("/mnt/netta/users/llaignel/Documents/projetRepriseS3/2026-projetreprise-laignelluc/etages/etage2N.txt");
            this.addAll(etage2);
            etageCourant = etage1;
        } catch (Exception e) {
            System.out.println(e+": Erreur dans le chargement des étages.");
        }
    }
    
    /**
     * Renvoie la liste des salles accessibles au héros.
     * @param heros le héros.
     * @return la liste des salles accessibles.
     */
    @Override
    public Collection<ISalle> sallesAccessibles(IPersonnage heros) {
        Set<ISalle> salleAccessibles = new HashSet<>();
        ISalle salleHeros = heros.getPosition();

        ISalle salleNord = new Salle(salleHeros.getEtage(), salleHeros.getX(), salleHeros.getY() + 1, ESalle.NORMALE);
        ISalle salleSud = new Salle(salleHeros.getEtage(), salleHeros.getX(), salleHeros.getY() - 1, ESalle.NORMALE);
        ISalle salleEst = new Salle(salleHeros.getEtage(), salleHeros.getX() + 1, salleHeros.getY(), ESalle.NORMALE);
        ISalle salleOuest = new Salle(salleHeros.getEtage(), salleHeros.getX() - 1, salleHeros.getY(), ESalle.NORMALE);

        ISalle salleAuDessus = null;
        if (salleHeros.getType() == ESalle.ESCALIER_MONTANT) {
            salleAuDessus = new Salle(new Etage(salleHeros.getEtage().getNum() + 1), salleHeros.getX(), salleHeros.getY(), ESalle.ESCALIER_DESCENDANT);
        }

        ISalle salleEnDessous = null;
        if (salleHeros.getType() == ESalle.ESCALIER_DESCENDANT) {
            salleEnDessous = new Salle(new Etage(salleHeros.getEtage().getNum() - 1), salleHeros.getX(), salleHeros.getY(), ESalle.ESCALIER_MONTANT);
        }

        for (ISalle vraieSalle : this) {
            if (vraieSalle.equals(salleNord) || vraieSalle.equals(salleSud)
                    || vraieSalle.equals(salleEst) || vraieSalle.equals(salleOuest)
                    || vraieSalle.equals(salleAuDessus) || vraieSalle.equals(salleEnDessous)) {

                salleAccessibles.add(vraieSalle);
            }
        }

        return salleAccessibles;
    }
    
    /**
     * Donne l'entrée du labyrinthe.
     * @return l'entrée de la salle.
     */
    @Override
    public ISalle getEntree() {
        boolean found = false;
        ISalle entry = this.getFirst();
        if (entry.getType() == ESalle.ENTREE) {
            return entry;
        }
        int i = 1;
        while (!found && i < this.size()) {
            if (this.get(i).getType() == ESalle.ENTREE) {
                entry = this.get(i);
                found = true;
            }
            i++;
        }
        return entry;
    }
    
    /**
     * Donne la sortie du labyrinthe.
     * @return la sortie du labyrinthe.
     */
    @Override
    public ISalle getSortie() {
        boolean found = false;
        ISalle exit = this.get(0);
        if (exit.getType() == ESalle.SORTIE) {
            return exit;
        }
        int i = 1;
        while (!found && i < this.size()) {
            if (this.get(i).getType() == ESalle.SORTIE) {
                exit = this.get(i);
                found = true;
            }
            i++;
        }
        return exit;
    }
    
    /**
     * Donne l'étage actuel du labyrinthe.
     * @return l'étage actuel.
     */
    @Override
    public IEtage getEtageCourant() {
        return this.etageCourant;
    }
    
    /**
     * Change l'étage courant du labyrinthe.
     * @param etage le nouvel étage courant.
     */
    @Override
    public void setEtageCourant(IEtage etage) {
        this.etageCourant = etage;
    }
    
    @Override
    public Collection<ISalle> chemin(ISalle u, ISalle v) {
        return null;
    }
    
    /**
     * Renvoie une salle normale aléatoire du labyrinthe.
     * @return une salle normale aléatoire du labyrinthe.
     */
    @Override
    public ISalle getSalleAleatoire() {
        Random random = new Random();
        List<ISalle> sallesNormales = this.stream()
                .filter(s -> s.getType() == ESalle.NORMALE)
                .collect(Collectors.toList());
        return sallesNormales.get(random.nextInt(sallesNormales.size()));
    }
}
