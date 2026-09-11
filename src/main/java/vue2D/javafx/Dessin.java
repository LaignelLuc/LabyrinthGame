package vue2D.javafx;

import java.util.Collection;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import labyrinthe.ESalle;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import vue2D.AVue;
import vue2D.sprites.ISprite;
import labyrinthe.IEtage;

/**
 *
 * @author INFO Professors team
 */
public class Dessin extends Canvas {
    
    /**
     * Liste des sprites des personnages.
     */
    private Collection<ISprite> sprites;
    
    /**
     * Taille des sprites.
     */
    private int unite;

    // donnees labyrinthe labyrinthe
    private ILabyrinthe labyrinthe;
    private ISalle entree;
    private ISalle sortie;
    private int largeur;
    private int hauteur;

    private GraphicsContext tampon;
    private Image murImage;
    private Image solImage;
    private Image escalierM;
    private Image escalierD;
    private Image marbreImage;
    private int tailleLinkH = 6;
    private int tailleLinkL = 2;
    
    /**
     * Grille pour aligner les salles du labyrinthe.
     */
    private boolean[][] grille;
    
    /**
     * Constructeur du dessin du labyrinthe.
     * @param labyrinthe labyrinthe du jeu.
     * @param sprites liste des sprites.
     */
    public Dessin(ILabyrinthe labyrinthe, Collection<ISprite> sprites) {
        this.labyrinthe = labyrinthe;
        this.sprites = sprites;
        this.unite = AVue.UNITE;
        entree = labyrinthe.getEntree();
        sortie = labyrinthe.getSortie();
        largeur = labyrinthe.getEtageCourant().getLargeur();
        hauteur = labyrinthe.getEtageCourant().getHauteur();
        setWidth(largeur * unite);
        setHeight(hauteur * unite);
        tampon = this.getGraphicsContext2D();

        grille = new boolean[largeur][hauteur];
        for (ISalle s : labyrinthe.getEtageCourant()) {
            grille[s.getX()][s.getY()] = true;
        }

        chargementImages();
        dessinFond();
        dessinSalles(labyrinthe.getEtageCourant());
        dessinSprites();
    }
    
    /**
     * Fonction qui charge les images et sprites.
     */
    public void chargementImages() {
        murImage = new Image("file:icons/mur0.gif");
        solImage = new Image("file:icons/pyramide.png");
        escalierM = new Image("file:icons/up.gif");
        escalierD = new Image("file:icons/down.gif");
        marbreImage = new Image("file:icons/marbre.jpg");
    }
    
    /**
     * Dessine les sprites.
     */
    public void dessinSprites() {
        for (ISprite s : sprites) {
            if (s.getPosition().getEtage().getNum() == labyrinthe.getEtageCourant().getNum()) {
                s.dessiner(tampon);
            }
        }
    }
    
    /**
     * Dessine le fond du labyrinthe.
     */
    public void dessinFond() {
        tampon.drawImage(solImage, 0, 0, unite * largeur,
                unite * hauteur);
    }
    
    /**
     * Dessine les salles du labyrinthes.
     * @param etage étage auquel se trouve les salles.
     */
    public void dessinSalles(IEtage etage) {
        for (ISalle s : etage) {
            Color c = Color.rgb(200, 200, 200);
            dessinSalle(s, c);
        }
    }
    
    /**
     * Dessine une salle du labyrinthe.
     * @param s une salle.
     * @param c une couleur pour la salle.
     */
    public void dessinSalle(ISalle s, Color c) {
        int x = s.getX();
        int y = s.getY();
        int px = x * unite;
        int py = y * unite;

        Color couleur = c;
        if (s.getType() == ESalle.ENTREE) {
            couleur = Color.rgb(100, 200, 100);
        } else if (s.getType() == ESalle.SORTIE) {
            couleur = Color.rgb(200, 100, 100);
        }

        tampon.setFill(couleur);
        tampon.fillRect(px, py, unite, unite);

        switch (s.getType()) {
            case ESCALIER_MONTANT ->
                tampon.drawImage(escalierM, px, py, unite, unite);
            case ESCALIER_DESCENDANT ->
                tampon.drawImage(escalierD, px, py, unite, unite);
            case NORMALE ->
                tampon.drawImage(marbreImage, px, py, unite, unite);
        }
    }

    public void dessinPlusCourtChemin(ISprite p) {
        // ...
    }

}
