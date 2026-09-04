/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;


import java.util.Collection;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import labyrinthe.ISalle;
import labyrinthe.Labyrinthe;
import personnages.Heros;
import personnages.IPersonnage;

/**
 *
 * @author llaignel
 */
public class HerosSprite extends ASprite implements EventHandler<KeyEvent>{
    
    private ISalle position;
    
    public HerosSprite(Heros hero){
        super(hero,new Image("file:icons/link/LinkRunR1.gif"));
    }
    
    @Override
    public void setPosition(ISalle nouvellePos){
        position = nouvellePos;
    }
    
    @Override
    public ISalle getPosition(){
        return position;
    }
    
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles){
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void handle(KeyEvent event){
        if (null != event.getCode())switch (event.getCode()) {
            case LEFT ->{
                this.setCoordonnees(this.getPosition().getX()-1, 
                        this.getPosition().getY());
            }
            case RIGHT->{
                this.setCoordonnees(this.getPosition().getX()+1, 
                        this.getPosition().getY());
            }
            case UP ->{
                this.setCoordonnees(this.getPosition().getX(), 
                        this.getPosition().getY()+1);
            }
            case DOWN ->{
                this.setCoordonnees(this.getPosition().getX(), 
                        this.getPosition().getY()-1);
            }
            case D ->{
                this.setCoordonnees(this.getPosition().getX()-1, 
                        this.getPosition().getY());
            }
            case M ->{
                this.setCoordonnees(this.getPosition().getX()-1, 
                        this.getPosition().getY());
            }
        }
    }
}
