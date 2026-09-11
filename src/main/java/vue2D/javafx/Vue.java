package vue2D.javafx;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import labyrinthe.ILabyrinthe;
import vue2D.IVue;
import vue2D.AVue;
import vue2D.sprites.ISprite;

/**
 *
 * @author INFO Professors team
 */
public class Vue extends AVue implements IVue {
    
    /**
     * Dessin du labyrinthe.
     */
    Dessin dessin;
    
    /**
     * Labyrinthe du jeu.
     */
    ILabyrinthe labyrinthe;
    
    public Scene scene;
    
    /**
     * Constructeur de la vue du labyrinthe.
     * @param labyrinthe labyrinthe du jeu.
     */
    public Vue(ILabyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
        dessin = new Dessin(labyrinthe, this);
        Group root = new Group();
        this.scene = new Scene(root);
        root.getChildren().add(dessin);
    }
    
    /**
     * Ajoute un sprite au labyrinthe.
     * @param sprite le sprite à ajouter.
     * @return si l'ajout est réussi.
     */
    @Override
    public boolean add(ISprite sprite) {
        super.add(sprite);
        if (sprite instanceof EventHandler) {
            System.out.println(" registering keylistener ");
            this.scene.setOnKeyPressed((EventHandler) sprite);
        }
        return true;
    }
    
    /**
     * Appelle les fonctions permettant de dessiner le labyrinthe.
     */
    @Override
    public void dessiner() {
        dessin.dessinFond();
        dessin.dessinSalles(labyrinthe.getEtageCourant());
        dessin.dessinSprites();
    }

}
