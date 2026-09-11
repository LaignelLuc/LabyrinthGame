/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import labyrinthe.ISalle;

/**
 * Classe abstraite implémentent l'interface IPersonnage.
 * @author llaignel
 */
public abstract class APersonnage implements IPersonnage{
    
    /**
     * Position du personnage.
     */
    protected ISalle position;
    
    /**
     * Donne la position du personnage.
     * @return la position du personnage.
     */
    @Override
    public ISalle getPosition(){
        return position;
    }
    
    /**
     * Change la position du personnage.
     * @param s la nouvelle position du personnage.
     */
    @Override
    public void setPosition(ISalle s){
        position = s;
    }
}
