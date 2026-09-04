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
public class Heros extends APersonnage {
    private ISalle salleChoisie;
    
    public Heros(ISalle uneSalle){
        position = uneSalle;
    }
    
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles){
        if(sallesAccessibles.contains(salleChoisie)){
            position = salleChoisie;
            return position;
        }
        return position;
    }
    
    public void setSalleChoisie(ISalle salle){
        salleChoisie = salle;
    }
}
