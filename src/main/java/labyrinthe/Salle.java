package labyrinthe;


/**
 * Classe gérant les salles du labyrinthe.
 * @author llaignel
 */
public class Salle implements ISalle {
    
    /**
     * L'étage auquel se trouve la salle.
     */
    private IEtage etage;
    
    /**
     * Coordonnée X de la salle dans le labyrinthe.
     */
    private int x;
    /**
     * Coordonnée Y de la salle du labyrinthe.
     */
    private int y;
    
    /**
     * Type de la salle.
     */
    private ESalle type;
    
    /**
     * Constructeur de l'objet salle.
     * @param unEtage Etage de la salle.
     * @param unX coordonnée X.
     * @param unY coordonnée Y.
     * @param unType Type de la salle.
     */
    public Salle(IEtage unEtage, int unX, int unY, ESalle unType) {
        etage = unEtage;
        x = unX;
        y = unY;
        type = unType;
    }
    
    /**
     * Getter de la coordonnée X de la salle.
     * @return la coordonnée X.
     */
    @Override
    public int getX() {
        return x;
    }
    
    /**
     * Getter de la coordonnée Y de la salle.
     * @return la coordonnée Y.
     */
    @Override
    public int getY() {
        return y;
    }
    
    /**
     * Getter pour le type de la salle.
     * @return le type de la salle.
     */
    @Override
    public ESalle getType() {
        return type;
    }
    
    /**
     * Getter pour l'étage de la salle.
     * @return l'étage de la salle.
     */
    @Override
    public IEtage getEtage() {
        return etage;
    }
    
    /**
     * Permet de savoir si une salle est adjacente à une autre.
     * @param autre autre salle.
     * @return si la salle est adjacente à l'autre salle.
     */
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
            if (autre.getEtage().getNum() == etage.getNum()
                    && x - autreX == 1 || x - autreX == -1
                    ^ y - autreY == 1 || y - autreY == -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.etage != null ? this.etage.getNum() : 0);
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

        if (this.etage == null || other.etage == null) {
            return false;
        }
        return this.etage.getNum() == other.etage.getNum();
    }

}
