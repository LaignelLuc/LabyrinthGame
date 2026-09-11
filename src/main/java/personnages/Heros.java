/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.Collection;
import labyrinthe.ISalle;

/**
 * Classe gérant l'objet héros.
 * @author llaignel
 */
public class Heros extends APersonnage {
    
    /**
     * Salle choisie par le héros pour le déplacement.
     */
    private ISalle salleChoisie;
    
    /**
     * Constructeur du héros.
     * @param uneSalle Salle initiale du héros.
     */
    public Heros(ISalle uneSalle) {
        position = uneSalle;
    }
    
    /**
     * Donne la nouvelle position du héros.
     * @param sallesAccessibles salles accessibles.
     * @return la nouvelle position du héros.
     */
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        //Si la salle choisie est une salle accessible, elle devient la nouvelle position du héros.
        if (salleChoisie != null) {
            sallesAccessibles.stream()
                    .filter(s -> s.equals(salleChoisie))
                    .findFirst()
                    .ifPresent(s -> position = s);

            salleChoisie = null;
        }
        return position;
    }
    
    /**
     * Permet de changer la salle choisie par le héros.
     * @param salle la nouvelle salle choisie.
     */
    public void setSalleChoisie(ISalle salle) {
        salleChoisie = salle;
    }
}
