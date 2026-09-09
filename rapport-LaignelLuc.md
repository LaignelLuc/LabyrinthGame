# Journal de bord du projet de reprise S3

## Séance 1
Première séance plutôt simple. Pas de grande difficulté pour comprendre le code ou la structure des fichiers textes pour les labyrinthe. UML plutôt simple à faire même si un peu fastidieux.
## Séance 2
Réalisation des tâches jusqu'à l'exercice 6. Problème: le projet était sous java 11. J'ai donc dû trouver une solution pour le passer en java 21. J'ai donc modifier le fichier pom.xml.
Spécifiquement à ces endroits:
```xml

<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
</properties>


<!--et-->

<configuration>
        <release>21</release>
</configuration>
```

## Séance 3
Terminé l'objectif 1. Le problème a été de comprendre comment le module d'interface graphique fonctionne car je ne l'avais jamais utilisé avant. J'ai donc regardé la documentation sur internet.

## Séance 4 et 5
Réalisation des tâches jusqu'à lexercice 11. Bloqué sur le 12 pour afficher le héros. Je dois aussi me renseigner sur comment fonction les events handler sur java.



## Séance 6

Toujours bloqué sur la question 12. Je dois trouver comment modifier la position choisie du héros. Néanmoins j'ai trouvé comment fonctionne le KeyEvent handler.


## Séance 7
J'ai finalement réussi à finir l'objectif 2. Donc entre autre j'ai terminé l'exercice 12 et 13. Pour ça il a fallu que je relie le sprite du héros à lui même. Pour ce faire j'ai crée un attribut héros qu'on met dans le constructeur du sprite. Pour initier le sprite, il faut donc donner dans le constructeur le sprite qui lui est associé:
```Java
private Heros hero;
private ILabyrinthe labyrinthe;

public HerosSprite(Heros hero, ILabyrinthe labyrinthe) {
    super(hero, new Image("file:icons/link/LinkRunShieldD1.gif"));
    this.hero = hero;
    this.labyrinthe = labyrinthe;
}
```
On override aussi les getters et setters pour qu'ils prennent la position du héro.

```Java
@Override
public void setPosition(ISalle nouvellePos) {
    this.hero.setPosition(nouvellePos);
}

@Override
public ISalle getPosition() {
    return this.hero.getPosition();
}

```
Enfin, on override la fonction fait son choix pour qu'elle vérifie que la case où le héro veut se déplacer existe bien grâce à la liste des salles accessibles(qui est donné grâce à la fonction correspondante), et que pour monter et descendre, le héros soit bien sur un escalier.

```Java
@Override
public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
    ISalle nouvellePos = this.hero.faitSonChoix(sallesAccessibles);
    if (nouvellePos.getEtage().getNum() != this.labyrinthe.getEtageCourant().getNum()) {
        this.labyrinthe.setEtageCourant(nouvellePos.getEtage());
    }
    return nouvellePos;
}

@Override
public void handle(KeyEvent event) {
    if (event.getCode() == null) {
        return;
    }
    ISalle pos = this.getPosition();

    ISalle destination = null;
    switch (event.getCode()) {
        case LEFT ->
            destination = new Salle(pos.getEtage(), pos.getX() - 1, pos.getY(), ESalle.NORMALE);
        case RIGHT ->
            destination = new Salle(pos.getEtage(), pos.getX() + 1, pos.getY(), ESalle.NORMALE);
        case UP ->
            destination = new Salle(pos.getEtage(), pos.getX(), pos.getY() - 1, ESalle.NORMALE);
        case DOWN ->
            destination = new Salle(pos.getEtage(), pos.getX(), pos.getY() + 1, ESalle.NORMALE);
        case M -> {
            if (pos.getType() == ESalle.ESCALIER_MONTANT) {
                Etage etageSuivant = new Etage(pos.getEtage().getNum() + 1);
                destination = new Salle(etageSuivant, pos.getX(), pos.getY(), ESalle.ESCALIER_DESCENDANT);
            }
        }
        case D -> {
            if (pos.getType() == ESalle.ESCALIER_DESCENDANT) {
                Etage etagePrecedent = new Etage(pos.getEtage().getNum() - 1);
                destination = new Salle(etagePrecedent, pos.getX(), pos.getY(), ESalle.ESCALIER_MONTANT);
            }
        }
        default -> {
        }
    }

    if (destination != null) {
        this.hero.setSalleChoisie(destination);
    }
}
```

## Séance 8 et 9

Réalisation de l'objectif 4. L'ajout des monstres se fait plutôt simplement. Dans les grandes lignes, il suffit juste de copier la plus part des attribut et méthode de héros avec quelques différences près.
Par exemple du côté des sprites on va implémenter une gestion aléatoire des mouvements du monstre.

```Java
@Override
public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
    deplacementAleatoires(sallesAccessibles);
    return this.monstre.faitSonChoix(sallesAccessibles);
}
    
public void deplacementAleatoires(Collection<ISalle> sallesAccessibles){
    Random random = new Random();
    ArrayList<ISalle> listeSalleAccessibles = new ArrayList<>(sallesAccessibles);
    this.monstre.setSalleChoisie(listeSalleAccessibles.get(random.nextInt(listeSalleAccessibles.size())));
}
```
Pour ça j'ai aussi pris l'initiative de rajouter une fonction qui renvoie une salle normale aléatoire du labyrinthe.

```Java
@Override
public ISalle getSalleAleatoire() {
    Random random = new Random();
    List<ISalle> sallesNormales = this.stream()
            .filter(s -> s.getType() == ESalle.NORMALE)
            .collect(Collectors.toList());
    return sallesNormales.get(random.nextInt(sallesNormales.size()));
}
```


On doit aussi gérer dans Dessin le fait que l'on ai des monstre sur deux étages différents. Il ne faut donc pas afficher les monstres de l'étage 2.

```Java
public void dessinSprites() {
    for (ISprite s : sprites) {
        if (s.getPosition().getEtage().getNum() == labyrinthe.getEtageCourant().getNum()) {
            s.dessiner(tampon);
        }
    }
}
```
## Séance 10

Début de l'objectif 4. J'ai implémenté l'animation sur les mouvements des personnages.

Pour ça on ajoute tous d'abord une fonction qui va calculer le mouvement du personnage 2 pixels par 2 pixels. Il nous faut pour ça 4 attributs: la valeur x et la valeur y de la position courante du personnage et la valeur x et y de la position vers laquelle le personnage va se déplacer.

On l'appelle juste avant le dessin. On rajoute aussi une fonction qui renvoie si le personnage est en déplacement.

```Java
private int xPixCourant = -1;
private int yPixCourant = -1;

private int xPixCible;
private int yPixCible;

private static final int PAS = 2;

public ASprite(IPersonnage personnage,Image sprite){
    this.sprite = sprite;
    this.personnage = personnage;
}

@Override
public void dessiner(GraphicsContext g){
    mouvement();
    g.drawImage(sprite, xPixCourant, yPixCourant,UNITE,UNITE);
}

public void mouvement(){
    ISalle positionPerso = this.getPosition();
    xPixCible = positionPerso.getX() * UNITE;
    yPixCible = positionPerso.getY() * UNITE;

    if (xPixCourant == -1) {
        xPixCourant = xPixCible;
        yPixCourant = yPixCible;
    }

    // Rapprochement vers la destination de 2px par 2px (axe X)
    if (xPixCourant < xPixCible) {
        xPixCourant += PAS;
        if (xPixCourant > xPixCible) {
            xPixCourant = xPixCible;
        }
    } else if (xPixCourant > xPixCible) {
        xPixCourant -= PAS;
        if (xPixCourant < xPixCible) {
            xPixCourant = xPixCible;
        }
    }

    // Rapprochement vers la destination de 2px par 2px (axe Y)
    if (yPixCourant < yPixCible) {
        yPixCourant += PAS;
        if (yPixCourant > yPixCible) {
            yPixCourant = yPixCible;
        }
    } else if (yPixCourant > yPixCible) {
        yPixCourant -= PAS;
        if (yPixCourant < yPixCible) {
            yPixCourant = yPixCible;
        }
    }
}

public boolean enDeplacement(){
    return xPixCourant != xPixCible || yPixCourant != yPixCible;
}
    
```

On rajoute donc aussi dans le faitSonChoix() de HerosSprite et MonstreSprite la vérification de si il est en mouvement.

```Java
if(enDeplacement()){
    return getPosition();
}
```

Donc si le sprite est toujours en mouvement, d'autres mouvements ne seront pas possible.
