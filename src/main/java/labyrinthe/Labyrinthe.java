package labyrinthe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
        throw new UnsupportedOperationException("Not supported yet."); 
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
