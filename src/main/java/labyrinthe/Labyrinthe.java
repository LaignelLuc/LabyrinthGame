package labyrinthe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import personnages.IPersonnage;

/**
 *
 * @author professor team
 */
public class Labyrinthe extends ArrayList<ISalle> implements ILabyrinthe {
    
    private IEtage etageCourant = new Etage();
    
    
    public Labyrinthe() {
        try {
            etageCourant.charger("/mnt/netta/users/llaignel/Documents/projetRepriseS3/2026-projetreprise-laignelluc/etages/etage1N.txt");
            this.addAll(etageCourant);
            etageCourant.charger("/mnt/netta/users/llaignel/Documents/projetRepriseS3/2026-projetreprise-laignelluc/etages/etage2N.txt");
            this.addAll(etageCourant);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<ISalle> sallesAccessibles(IPersonnage heros) {
        Set salleAccessibles = new HashSet();
        ISalle salleHeros = heros.getPosition();
        
        ISalle salleNord = new Salle(salleHeros.getEtage(),
        salleHeros.getX(),salleHeros.getY()+1, ESalle.NORMALE);
        
        ISalle salleSud = new Salle(salleHeros.getEtage(),
        salleHeros.getX(),salleHeros.getY()-1, ESalle.NORMALE);
        
        ISalle salleEst = new Salle(salleHeros.getEtage(),
        salleHeros.getX()+1,salleHeros.getY(), ESalle.NORMALE);
        
        ISalle salleOuest = new Salle(salleHeros.getEtage(),
        salleHeros.getX()-1,salleHeros.getY()+1, ESalle.NORMALE);
        
        if(this.contains(salleNord)){
            this.add(salleNord);
        }
        if(this.contains(salleSud)){
            this.add(salleSud);
        }
        if(this.contains(salleEst)){
            this.add(salleEst);
        }
        if(this.contains(salleOuest)){
            this.add(salleOuest);
        }
        
        if(salleHeros.getType()==ESalle.ESCALIER_MONTANT){
            Etage etageAuDessus = new Etage(etageCourant.getNum()+1);
            
            ISalle salleAuDessus = new Salle(etageAuDessus,
            salleHeros.getX(),salleHeros.getY(),ESalle.ESCALIER_DESCENDANT);
            
            if(this.contains(salleAuDessus)){
                salleAccessibles.add(salleAuDessus);
            }
        }
        if(salleHeros.getType()==ESalle.ESCALIER_DESCENDANT){
            Etage etageEnDessous = new Etage(etageCourant.getNum()-1);
            
            ISalle salleEnDessous = new Salle(etageEnDessous,
            salleHeros.getX(),salleHeros.getY(),ESalle.ESCALIER_MONTANT);
            
            if(this.contains(salleEnDessous)){
                salleAccessibles.add(salleEnDessous);
            }
        }
        return salleAccessibles;
    }

    @Override
    public ISalle getEntree() {
        boolean found = false;
        ISalle entry = this.getFirst();
        if(entry.getType() == ESalle.ENTREE){
            return entry;
        }
        int i = 1;
        while(!found && i < this.size()){
            if(this.get(i).getType()== ESalle.ENTREE){
                entry = this.get(i);
                found = true;
            }
            i++;
        }
        return entry;
    }

    @Override
    public ISalle getSortie() {
        boolean found = false;
        ISalle exit = this.get(0);
        if(exit.getType() == ESalle.SORTIE){
            return exit;
        }
        int i = 1;
        while(!found && i < this.size()){
            if(this.get(i).getType()== ESalle.SORTIE){
                exit = this.get(i);
                found = true;
            }
            i++;
        }
        return exit;   
    }

    @Override
    public IEtage getEtageCourant() {
        return this.etageCourant;
    }

    @Override
    public void setEtageCourant(IEtage etage) {
        this.etageCourant = etage;
    }

    @Override
    public Collection<ISalle> chemin(ISalle u, ISalle v) {
        return null;
    }
    
}
