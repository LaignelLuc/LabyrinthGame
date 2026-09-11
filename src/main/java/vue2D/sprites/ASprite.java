/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import labyrinthe.ESalle;
import labyrinthe.ISalle;
import labyrinthe.Salle;
import personnages.IPersonnage;
import static vue2D.AVue.UNITE;

/**
 *
 * @author llaignel
 */
public abstract class ASprite implements ISprite{
    
    /**
     * Image du sprite.
     */
    private Image sprite;
    
    /**
     * Personnage (héros ou monstre).
     */
    private IPersonnage personnage;
    
    /**
     * Coordonnée x de la salle actuelle du sprite.
     */
    private int xPixCourant = -1;
    
    /**
     * Coordonnée y de la salle actuelle du sprite.
     */
    private int yPixCourant = -1;
    
    /**
     * Coordonnée x de la salle cible du sprite.
     */
    private int xPixCible;
    
    /**
     * Coordonnée x de la salle cible du sprite.
     */
    private int yPixCible;
    
    /**
     * Le pas en pixel pour l'animation du déplacement.
     */
    private static final int PAS = 2;
    
    public ASprite(IPersonnage personnage,Image sprite){
        this.sprite = sprite;
        this.personnage = personnage;
    }
    
    /**
     * Dessine le sprite et l'anime.
     * @param g 
     */
    @Override
    public void dessiner(GraphicsContext g){
        mouvement();
        g.drawImage(sprite, xPixCourant, yPixCourant,UNITE,UNITE);
    }
    
    /**
     * Permet de calculer et mettre en place l'animation du sprite lors d'un mouvement.
     */
    public void mouvement(){
        ISalle positionPerso = this.getPosition();
        xPixCible = positionPerso.getX() * UNITE;
        yPixCible = positionPerso.getY() * UNITE;

        if (xPixCourant == -1) {
            xPixCourant = xPixCible;
            yPixCourant = yPixCible;
        }

        // Rapprochement vers la destination de 2px par 2px (axe X)
        if (xPixCourant < xPixCible) {
            xPixCourant += PAS;
            if (xPixCourant > xPixCible) {
                xPixCourant = xPixCible;
            }
        } else if (xPixCourant > xPixCible) {
            xPixCourant -= PAS;
            if (xPixCourant < xPixCible) {
                xPixCourant = xPixCible;
            }
        }

        // Rapprochement vers la destination de 2px par 2px (axe Y)
        if (yPixCourant < yPixCible) {
            yPixCourant += PAS;
            if (yPixCourant > yPixCible) {
                yPixCourant = yPixCible;
            }
        } else if (yPixCourant > yPixCible) {
            yPixCourant -= PAS;
            if (yPixCourant < yPixCible) {
                yPixCourant = yPixCible;
            }
        }
    }
    
    /**
     * Renvoie si le sprite est en mouvement.
     * @return true si le sprite bouge encore, false sinon. 
     */
    public boolean enDeplacement(){
        return xPixCourant != xPixCible || yPixCourant != yPixCible;
    }
    
    /**
     * Donnes des nouvelles coordonnées au sprite.
     * @param xpix position x.
     * @param ypix position y.
     */
    @Override
    public void setCoordonnees(int xpix, int ypix){
        ISalle positionPerso = this.getPosition();
        ISalle nouvellePos = new Salle(positionPerso.getEtage(),
        xpix, ypix,ESalle.NORMALE);
        this.setPosition(nouvellePos);
    }
    
}
