package application;

import java.util.Collection;
import java.util.List;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import personnages.Heros;
import personnages.Monstre;
import vue2D.IVue;
import vue2D.sprites.HerosSprite;
import vue2D.sprites.ISprite;
import vue2D.sprites.MonstreSprite;

/**
 *
 * @author arpecher
 */
public class Core {
    
    ILabyrinthe labyrinthe;

    // Entité du labyrinthe dont le héro
    ISprite heros;
    ISprite monstre1;
    ISprite monstre2;
    ISprite monstre3;
    ISprite monstre4;
    ISprite monstre5;
    ISprite monstre6;
    ISprite monstre7;
    ISprite monstre8;
    ISprite monstre9;
    ISprite monstre10;
    
    /**
     * Initialisation du labyrinthe.
     */
    protected void initLabyrinthe() {
        // creation du labyrinthe
        labyrinthe = new labyrinthe.Labyrinthe();
    }
    
    /**
     * Initialisation des sprites.
     * @param vue vue du labyrinthe.
     */
    protected void initSprites(IVue vue) {
        // creation du heros 
        Heros h = new personnages.Heros(labyrinthe.getEntree());
        this.heros = new HerosSprite(h, labyrinthe);
        vue.add(this.heros);
        
        // creation de multiple monstre
        ISalle salleInit1 = labyrinthe.getSalleAleatoire();
        ISalle salleInit2 = labyrinthe.getSalleAleatoire();
        ISalle salleInit3 = labyrinthe.getSalleAleatoire();
        ISalle salleInit4 = labyrinthe.getSalleAleatoire();
        ISalle salleInit5 = labyrinthe.getSalleAleatoire();
        ISalle salleInit6 = labyrinthe.getSalleAleatoire();
        ISalle salleInit7 = labyrinthe.getSalleAleatoire();
        ISalle salleInit8 = labyrinthe.getSalleAleatoire();
        ISalle salleInit9 = labyrinthe.getSalleAleatoire();
        ISalle salleInit10 = labyrinthe.getSalleAleatoire();
        
        Monstre monstre1 = new personnages.Monstre(salleInit1);
        this.monstre1 = new MonstreSprite(monstre1,labyrinthe);
        
        Monstre monstre2 = new personnages.Monstre(salleInit2);
        this.monstre2 = new MonstreSprite(monstre2,labyrinthe);

        Monstre monstre3 = new personnages.Monstre(salleInit3);
        this.monstre3 = new MonstreSprite(monstre3,labyrinthe);
        
        Monstre monstre4 = new personnages.Monstre(salleInit4);
        this.monstre4 = new MonstreSprite(monstre4,labyrinthe);
        
        Monstre monstre5 = new personnages.Monstre(salleInit5);
        this.monstre5 = new MonstreSprite(monstre5,labyrinthe);
        
        Monstre monstre6 = new personnages.Monstre(salleInit6);
        this.monstre6 = new MonstreSprite(monstre6,labyrinthe);
        
        Monstre monstre7 = new personnages.Monstre(salleInit7);
        this.monstre7 = new MonstreSprite(monstre7,labyrinthe);
        
        Monstre monstre8 = new personnages.Monstre(salleInit8);
        this.monstre8 = new MonstreSprite(monstre8,labyrinthe);
        
        Monstre monstre9 = new personnages.Monstre(salleInit9);
        this.monstre9 = new MonstreSprite(monstre9,labyrinthe);
        
        Monstre monstre10 = new personnages.Monstre(salleInit10);
        this.monstre10 = new MonstreSprite(monstre10,labyrinthe);
        
        vue.addAll(List.of(this.monstre1,this.monstre2,this.monstre3,this.monstre4,
                this.monstre5, this.monstre6, this.monstre7, this.monstre8,
                this.monstre9, this.monstre10));
    }
    
    /**
     * Fonction gérant le jeu.
     * @param vue Vue du jeu.
     */
    protected void jeu(IVue vue) {
        // boucle principale
        ISalle destination = null;
        while (!labyrinthe.getSortie().equals(heros.getPosition())) {
            labyrinthe.setEtageCourant(heros.getPosition().getEtage());
            // choix et deplacements de chaque sprite
            for (ISprite s : vue) {
                Collection<ISalle> sallesAccessibles = labyrinthe.sallesAccessibles(s);
                destination = s.faitSonChoix(sallesAccessibles); // on demande au personnage de faire son choix de salle
                s.setPosition(destination); // deplacement

            }
            // detection des collisions
            boolean collision = false;
            ISprite monstre = null;
            for (ISprite s : vue) {
                if (s != heros) {
                    if (s.getPosition() == heros.getPosition()) {
                        System.out.println("Collision !!");
                        collision = true;
                        monstre = s;
                    }
                }
            }
            if (collision) {
                vue.remove(monstre);
                vue.remove(heros);
                System.out.println("Perdu !");
                System.out.println("Plus que " + vue.size() + " personnages ...");
            }

            temporisation(10);
        }
        System.out.println("Gagné!");
    }

    protected void temporisation(int nb) {
        try {
            Thread.sleep(nb); // pause de nb millisecondes
        } catch (InterruptedException ie) {
        }
    }
}
