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
    private Image sprite;
    private IPersonnage personnage;
    
    private int xPixCourant = -1;
    private int yPixCourant = -1;
    
    private int xPixCible;
    private int yPixCible;
    
    private static final int PAS = 2;
    
    public ASprite(IPersonnage personnage,Image sprite){
        this.sprite = sprite;
        this.personnage = personnage;
    }
    
    @Override
    public void dessiner(GraphicsContext g){
        mouvement();
        g.drawImage(sprite, xPixCourant, yPixCourant,UNITE,UNITE);
    }
    
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
    
    public boolean enDeplacement(){
        return xPixCourant != xPixCible || yPixCourant != yPixCible;
    }
    
    
    @Override
    public void setCoordonnees(int xpix, int ypix){
        ISalle positionPerso = this.getPosition();
        ISalle nouvellePos = new Salle(positionPerso.getEtage(),
        xpix, ypix,ESalle.NORMALE);
        this.setPosition(nouvellePos);
    }
    
}
