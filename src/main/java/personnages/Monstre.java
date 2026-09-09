/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.Collection;
import labyrinthe.ISalle;

/**
 *
 * @author llaignel
 */
public class Monstre extends APersonnage{
    
    private ISalle salleChoisie;

    public Monstre(ISalle uneSalle) {
        position = uneSalle;
    }
    
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
    
    public void setSalleChoisie(ISalle salle) {
        salleChoisie = salle;
    }
    
    
}
