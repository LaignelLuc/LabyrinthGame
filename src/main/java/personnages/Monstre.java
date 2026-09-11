/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.Collection;
import labyrinthe.ISalle;

/**
 * Personnage monstre du labyrinthe.
 * @author llaignel
 */
public class Monstre extends APersonnage{
    
    /**
     * Salle choisie par le monstre.
     */
    private ISalle salleChoisie;
    
    /**
     * Constructeur de l'objet monstre.
     * @param uneSalle 
     */
    public Monstre(ISalle uneSalle) {
        position = uneSalle;
    }
    
    /**
     * Met a jour la position du monstre si la salle choisie est une salle accessibles.
     * @param sallesAccessibles liste des salles accessibles.
     * @return la position du monstre.
     */
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
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
     * Change la salle choisie par le monstre.
     * @param salle la nouvelle salle.
     */
    public void setSalleChoisie(ISalle salle) {
        salleChoisie = salle;
    }
    
    
}
