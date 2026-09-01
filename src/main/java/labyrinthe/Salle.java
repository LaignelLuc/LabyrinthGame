package labyrinthe;

import java.util.Objects;

/**
 *
 * @author llaignel
 */
public class Salle implements ISalle {

    private Etage etage;
    private int x;
    private int y;
    private ESalle type;

    public Salle(Etage unEtage, int unX, int unY, ESalle unType) {
        etage = unEtage;
        x = unX;
        y = unY;
        type = unType;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public ESalle getType() {
        return type;
    }

    @Override
    public Etage getEtage() {
        return etage;
    }

    @Override
    public boolean estAdjacente(ISalle autre) {
        int autreX = autre.getX();
        int autreY = autre.getY();
        int autreEtage = autre.getEtage().getNum();
        if (type == ESalle.ESCALIER_MONTANT
                && autre.getType() == ESalle.ESCALIER_DESCENDANT) {
            if (autreEtage == etage.getNum() + 1
                    && autreX == x && autreY == y) {
                return true;
            }
        } else if (type == ESalle.ESCALIER_DESCENDANT
                && autre.getType() == ESalle.ESCALIER_MONTANT) {
            if (autreEtage == etage.getNum() - 1
                    && autreX == x && autreY == y) {
                return true;
            }
        } else {
            if(autre.getEtage().getNum() == etage.getNum() &&
                    x - autreX == 1 || x - autreX == -1 &&
                    y - autreY == 1 || y - autreY == -1){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.etage);
        hash = 47 * hash + this.x;
        hash = 47 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Salle other = (Salle) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return Objects.equals(this.etage, other.etage);
    }

}
