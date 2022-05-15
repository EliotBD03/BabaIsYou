package Presenter;
import Model.*;
import View.Controller;
import javafx.scene.image.Image;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * cette classe est la classe "Main" du Presenter.
 * C'est par là que va transiter les informations de Model
 * et de toute autre classe du Presenter.
 */
public class Game
{
    //notre utilisateur
    private static User player;
    //le niveau en cours (le chemin du fichier)
    private static Level level;
    //la carte du jeu
    private static Environment map;
    //lien avec l'interface graphique
    private static Controller controller;
    //les différents objets du jeu (Item)
    private static Baba baba;
    private static Flag flag;
    private static Rock rock;
    private static Wall wall;
    private static Goop goop;
    private static Lava lava;
    private static Glue glue;
    //la map de String temporaire
    private static  String[][] temp;
    //notre dictionnaire pour faire le lien
    //entre les Strings et les images
    private static final Map<Character, Image> dico = new HashMap<Character, Image>();
    //la taille de la map (en longueur et largeur)
    public static int getLength(){return map.getMap().length;}

    /**
     * constructeur qui va établir les niveaux faits/disponibles et l'index de niveaux courant
     * dernièrement utilisé
     * @param userName le pseudo du joueur
     * @throws URISyntaxException pour controller
     */
    public Game(String userName) throws URISyntaxException
    {
        player =  User.getUser(userName);
        level = new Level(player.getId());
        controller = new Controller();

        dico.put(' ',controller.fonfImage); dico.put('X',controller.borderImage);
        dico.put('O', controller.babaImage); dico.put('w', controller.wallImage);
        dico.put('@', controller.flagImage); dico.put('#', controller.rockImage);
        dico.put('B', controller.textBabaImage); dico.put('I', controller.isImage);
        dico.put('Y', controller.youImage); dico.put('W', controller.textWallImage);
        dico.put('S', controller.stopImage); dico.put('R', controller.textRockImage);
        dico.put('P', controller.pushImage); dico.put('F', controller.textFlagImage);
        dico.put('G', controller.winImage); dico.put('?', controller.goopImage);
        dico.put('M', controller.textGoopImage); dico.put('D', controller.textSinkImage);
        dico.put('L', controller.textLavaImage);dico.put('K', controller.killImage);
        dico.put('*', controller.lavaImage); dico.put('$', controller.glueImage);
        dico.put('C', controller.textGlueImage); dico.put('T', controller.stickyImage);
    }

    /**
     * à la différence de makeTheGame(int index),
     * cette méthode est plutôt utilisée pour les
     * sauvegardes
     * @param fileName la sauvegarde
     */

    public void makeTheGame(String fileName)
    {
        Extract extract = new Extract(fileName);
        map = new Environment();
        map.setMap(extract.getDataList());

        baba = new Baba();
        flag = new Flag();
        rock = new Rock();
        wall = new Wall();
        goop = new Goop();
        lava = new Lava();
        glue = new Glue();
    }

    /**
     * Méthode ayant pour but d'effectuer un déplacement dans le jeu.
     * @param event l'entrée du joueur
     * @return Elle retourne vrai si le joueur a gagné, faux sinon
     */
    public boolean makeMove(String event)
    {
        baba.move(event);
        flag.move(event);
        rock.move(event);
        wall.move(event);
        goop.move(event);
        lava.move(event);
        glue.move(event);
        return Item.win();
    }

    /**
     * méthode permettant de récupérer une image du jeu
     * en fonction de la position du String donné
     * en paramètre.
     * @param i la position du String en y
     * @param j la position du String en x
     * @return l'url de l'image correspondante
     */
    public static Image getSprite(int i , int j)
    {
        return dico.get(map.getMap()[i][j].charAt(0));
    }

    /**
     * cette méthode permet de savoir quelle image il faut
     * changer en fonction de deux cartes:
     * - avant l'entrée du joueur
     * - après l'entrée du joueur
     * @return la coordonnée de l'image à modifier
     */

    public ArrayList<int[]> getChanges()
    {
        ArrayList<int[]> changeCoord = new ArrayList<>();
        temp = Environment.getStringMap(map.getMap());
        map.actualiseMap();
        String[][] actual = map.getMap();
        for(int i = 0; i <= actual.length -1; i++)
            for(int j = 0; j < actual[j].length -1; j++)
                if(!(actual[i][j].equals(temp[i][j])))
                {
                    int[] pos = {i,j};
                    changeCoord.add(pos);
                }
        return changeCoord;
    }

    /**
     * Méthode utilisée pour changer de niveau.
     * Elle va simplement changer l'url du niveau
     * et va réinitialiser la carte.
     * @return vrai si on peut continuer à jouer (si on n'est pas au dernier niveau), faux sinon
     */
    public boolean nextLevel()
    {
        boolean continu = level.goNext();
        if(continu)
        {
            Extract extract = new Extract(level.getCurrentLevel());
            map.setMap(extract.getDataList());
        }
        return continu;
    }

    /**
     * Méthode utilisée pour réinitialiser la carte du jeu
     */
    public void resetLevel()
    {
        Extract extract = new Extract(level.getCurrentLevel());
        map.setMap(extract.getDataList());
    }

    /**
     * Méthode servant à sauvegarder une partie.
     * ATTENTION, ssi le joueur a mis un pseudo.
     */

    public void getSave()
    {
        if(player.getId() == null)
            return;
        level.writeLevelAccess(player.getId());
        Save save = new Save();
        save.newSave(map.getMap());
    }

    /**
     * Méthode servant à reprendre une partie.
     * @return l'url de la dernière sauvegarde
     */
    public static String getLastSave()
    {
        level.fromSave();
        Save save = new Save();
        return save.getLastSave();
    }

    /**
     * méthode mettant en place un niveau en fonction de son index
     * @param index l'index de la liste des niveaux
     * @return l'url du niveau correspondant
     */
    public boolean setLevel(int index)
    {
        return level.setCurrentIndex(index);
    }

    /**
     * méthode servant à récupérer le niveau actuel
     * @return l'url du niveau
     */
    public String getLevel()
    {
        return level.getCurrentLevel();
    }
}

