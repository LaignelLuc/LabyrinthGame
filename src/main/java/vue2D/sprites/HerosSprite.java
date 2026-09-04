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

public class HerosSprite extends ASprite implements EventHandler<KeyEvent> {

    private Heros hero;
    private ILabyrinthe labyrinthe;

    public HerosSprite(Heros hero, ILabyrinthe labyrinthe) {
        super(hero, new Image("file:icons/link/LinkRunShieldD1.gif"));
        this.hero = hero;
        this.labyrinthe = labyrinthe;
    }

    @Override
    public void setPosition(ISalle nouvellePos) {
        this.hero.setPosition(nouvellePos);
    }

    @Override
    public ISalle getPosition() {
        return this.hero.getPosition();
    }

    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        ISalle nouvellePos = this.hero.faitSonChoix(sallesAccessibles);
        if (nouvellePos.getEtage().getNum() != this.labyrinthe.getEtageCourant().getNum()) {
            this.labyrinthe.setEtageCourant(nouvellePos.getEtage());
        }
        return nouvellePos;
    }

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
