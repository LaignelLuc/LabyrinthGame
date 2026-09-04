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
    
    public ASprite(IPersonnage personnage,Image sprite){
        this.sprite = sprite;
        this.personnage = personnage;
    }
    
    @Override
    public void dessiner(GraphicsContext g){
        ISalle positionPerso = this.getPosition();
        g.drawImage(sprite, positionPerso.getX()*UNITE, positionPerso.getY()*UNITE,UNITE,UNITE);
    }
    
    @Override
    public void setCoordonnees(int xpix, int ypix){
        ISalle positionPerso = this.getPosition();
        ISalle nouvellePos = new Salle(positionPerso.getEtage(),
        xpix, ypix,ESalle.NORMALE);
        this.setPosition(nouvellePos);
    }
    
}
