/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import javafx.scene.image.Image;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import personnages.Monstre;

/**
 *
 * @author llaignel
 */
public class MonstreSprite extends ASprite{
    
    private Monstre monstre;
    private ILabyrinthe labyrinthe;
    
    public MonstreSprite(Monstre monstre, ILabyrinthe labyrinthe){
        super(monstre, new Image("file:icons/monstre0.gif"));
        this.monstre = monstre;
        this.labyrinthe = labyrinthe;
    }
    
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        if(enDeplacement()){
            return getPosition();
        }
        deplacementAleatoires(sallesAccessibles);
        return this.monstre.faitSonChoix(sallesAccessibles);
    }
    
    public void deplacementAleatoires(Collection<ISalle> sallesAccessibles){
        Random random = new Random();
        ArrayList<ISalle> listeSalleAccessibles = new ArrayList<>(sallesAccessibles);
        this.monstre.setSalleChoisie(listeSalleAccessibles.get(random.nextInt(listeSalleAccessibles.size())));
    }
    
    @Override
    public ISalle getPosition() {
        return this.monstre.getPosition();
    }

    @Override
    public void setPosition(ISalle s) {
        this.monstre.setPosition(s);
    }
    
}
