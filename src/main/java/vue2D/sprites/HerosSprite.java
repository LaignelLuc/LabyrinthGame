package vue2D.sprites;

import java.util.Collection;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import labyrinthe.ESalle;
import labyrinthe.Etage;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import labyrinthe.Salle;
import personnages.Heros;

/**
 * Classe gérant le sprite du héros
 * @author llaignel
 */
public class HerosSprite extends ASprite implements EventHandler<KeyEvent> {
    /**
     * Héros associé au sprite.
     */
    private Heros hero;
    
    /**
     * Labyrinthe du jeu.
     */
    private ILabyrinthe labyrinthe;
    
    
    /**
     * Constructeur pour le sprite du héros.
     * @param hero heros lié au sprite.
     * @param labyrinthe labyrinthe lié au jeu.
     */
    public HerosSprite(Heros hero, ILabyrinthe labyrinthe) {
        super(hero, new Image("file:icons/link/LinkRunShieldD1.gif"));
        this.hero = hero;
        this.labyrinthe = labyrinthe;
    }
    
    /**
     * Override changeant la position du sprite dans le labyrinthe.
     * @param nouvellePos nouvelle position du sprite.
     */
    @Override
    public void setPosition(ISalle nouvellePos) {
        this.hero.setPosition(nouvellePos);
    }
    
    /**
     * Override donnant la position du sprite dans labyrinthe.
     * @return la position du sprite du héros.
     */
    @Override
    public ISalle getPosition() {
        return this.hero.getPosition();
    }
    
    /**
     * Choix de salle pour le déplacement du héros.
     * @param sallesAccessibles liste des salles accessibles au héros.
     * @return la nouvelle position du sprite du héros.
     */
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        //Empêche le déplacement tant que le sprite est en animation.
        if(enDeplacement()){
            return getPosition();
        }
        ISalle nouvellePos = this.hero.faitSonChoix(sallesAccessibles);
        if (nouvellePos.getEtage().getNum() != this.labyrinthe.getEtageCourant().getNum()) {
            this.labyrinthe.setEtageCourant(nouvellePos.getEtage());
        }
        return nouvellePos;
    }
    
    /**
     * Fonction permettant la navigation du sprite à l'aide du clavier.
     * @param event Quand l'utilisateur appuie sur une touche du clavier.
     */
    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == null) {
            return;
        }
        ISalle pos = this.getPosition();

        ISalle destination = null;
        switch (event.getCode()) {
            case LEFT ->
                destination = new Salle(pos.getEtage(), pos.getX() - 1, pos.getY(), ESalle.NORMALE);
            case RIGHT ->
                destination = new Salle(pos.getEtage(), pos.getX() + 1, pos.getY(), ESalle.NORMALE);
            case UP ->
                destination = new Salle(pos.getEtage(), pos.getX(), pos.getY() - 1, ESalle.NORMALE);
            case DOWN ->
                destination = new Salle(pos.getEtage(), pos.getX(), pos.getY() + 1, ESalle.NORMALE);
            case M -> {
                if (pos.getType() == ESalle.ESCALIER_MONTANT) {
                    Etage etageSuivant = new Etage(pos.getEtage().getNum() + 1);
                    destination = new Salle(etageSuivant, pos.getX(), pos.getY(), ESalle.ESCALIER_DESCENDANT);
                }
            }
            case D -> {
                if (pos.getType() == ESalle.ESCALIER_DESCENDANT) {
                    Etage etagePrecedent = new Etage(pos.getEtage().getNum() - 1);
                    destination = new Salle(etagePrecedent, pos.getX(), pos.getY(), ESalle.ESCALIER_MONTANT);
                }
            }
            default -> {
            }
        }

        if (destination != null) {
            this.hero.setSalleChoisie(destination);
        }
    }
}
