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
 * Classe gérant le sprite des monstres.
 * @author llaignel
 */
public class MonstreSprite extends ASprite{
    
    /**
     * Monstre associer au sprite.
     */
    private Monstre monstre;
    
    /**
     * Labyrinthe du jeu.
     */
    private ILabyrinthe labyrinthe;
    
    /**
     * Constructeur du sprite.
     * @param monstre monstre lié au sprite.
     * @param labyrinthe labyrinthe du jeu.
     */
    public MonstreSprite(Monstre monstre, ILabyrinthe labyrinthe){
        super(monstre, new Image("file:icons/monstre0.gif"));
        this.monstre = monstre;
        this.labyrinthe = labyrinthe;
    }
    
    /**
     * Choix d'une salle aléatoire pour le déplacement du monstre.
     * @param sallesAccessibles salle accessible du monstre.
     * @return la nouvelle salle du monstre ou la salle courante si toujours en animation.
     */
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        //Empêche le mouvement tant que le sprite est en animation.
        if(enDeplacement()){
            return getPosition();
        }
        deplacementAleatoires(sallesAccessibles);
        return this.monstre.faitSonChoix(sallesAccessibles);
    }
    
    /**
     * Met la salle choisie à une salle aléatoire parmis les salles accessibles.
     * @param sallesAccessibles liste des salles accessibles.
     */
    public void deplacementAleatoires(Collection<ISalle> sallesAccessibles){
        Random random = new Random();
        ArrayList<ISalle> listeSalleAccessibles = new ArrayList<>(sallesAccessibles);
        this.monstre.setSalleChoisie(listeSalleAccessibles.get(random.nextInt(listeSalleAccessibles.size())));
    }
    
    /**
     * Renvoie la position du monstre.
     * @return la position du monstre. 
     */
    @Override
    public ISalle getPosition() {
        return this.monstre.getPosition();
    }

    /**
     * Change la position du monstre.
     * @param s la nouvelle salle.
     */
    @Override
    public void setPosition(ISalle s) {
        this.monstre.setPosition(s);
    }
    
}
