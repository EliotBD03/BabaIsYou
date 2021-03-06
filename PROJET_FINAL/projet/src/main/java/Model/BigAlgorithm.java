package Model;

import java.util.HashMap;
import java.util.Map;


/**
 * cette classe permet de savoir pour quel item du jeu, on y retrouve sa règle
 */

public class BigAlgorithm extends Environment {
    private static final Enum[][] tabloc = new Enum[getLength()][getWidth()];
    private static Enum[][] tabperm;
    private static final Enum[] character = {Rules.BABA, Rules.ROCK, Rules.FLAG, Rules.WALL, Rules.GOOP, Rules.LAVA, Rules.GLUE};
    private static final Enum[] actions = {Rules.YOU, Rules.STOP, Rules.WIN, Rules.PUSH, Rules.SINK, Rules.BABA, Rules.ROCK, Rules.FLAG, Rules.WALL, Rules.GOOP, Rules.LAVA, Rules.KILL, Rules.GLUE, Rules.STICKY};
    public static final Map<Rules, Entity> dico = new HashMap<Rules, Entity>();


    /**
     * constructeur permettant de ne pas exécuter les méthodes à chaque fois
     */

    public BigAlgorithm() {
        setTab(mapO);
        int[][] current_is = findIS();
        setTabperm(findPermX(current_is), findPermY(current_is));
        dico.put(Rules.BABA, new Baba());
        dico.put(Rules.FLAG, new Flag());
        dico.put(Rules.ROCK,new Rock());
        dico.put(Rules.WALL, new Wall());
        dico.put(Rules.GOOP, new Goop());
    }

    /**
     * permet d'actualiser le tableau de permissions après changement de position d'une instance dans le tableau d'objet
     */
    public static void actualise() {
        setTab(mapO);
        int[][] current_is = findIS();
        setTabperm(findPermX(current_is), findPermY(current_is));
    }

    /**
     * accesseur pour le tableau des permissions
     *
     * @return tableau à 2 dimensions
     */
    public static Enum[][] getTabperm() {
        return tabperm;
    }

    /**
     * cree notre tableau "tabloc" a deux dimensions regroupant les règles (ex: BabaIsYou)
     *
     * @param map0 le tableau issus de map représentant les objets
     */

    private static void setTab(Entity[][] map0) {
        for (int i = 1; i <= map0.length - 1; i++)
            for (int j = 1; j <= map0[i].length - 1; j++)
                if (map0[i][j] != null) {
                    tabloc[i][j] = whatobj(map0[i][j].getClass());
                } else
                    tabloc[i][j] = Rules.NONE;
    }

    /**
     * rassemble les tableaux de permissions en x et en y dans un même tableau
     * @param tabpermX issue de la methode findPermX
     * @param tabpermY issue de la methode findPermY
     */
    private static void setTabperm(Enum[][] tabpermX, Enum[][] tabpermY)
    {
        tabperm = new Enum[20][2];
        int i = 0;
        for(Enum[] elemX : tabpermX)
        {
            tabperm[i][0] = elemX[0]; tabperm[i][1] = elemX[1];
            i++;
        }
        for(Enum[] elemY : tabpermY)
        {
            tabperm[i][0] = elemY[0]; tabperm[i][1] = elemY[1];
            i++;
        }
    }

    /**
     * cherche dans le tableau "tabloc" les "Is" permettant de faire l'union de deux objets (exemple : Baba et You)
     *
     * @return un tableau à deux dimensions regroupant les Is et leurs positions de cette manière :
     * un sous tableau = Is, les elements de Is => position en y et en x (dans cet ordre)
     */

    private static int[][] findIS() {
        int[][] res = new int[10][2];
        int k = 0;
        for (int i = 0; i <= tabloc.length - 1; i++)
            for (int j = 0; j <= tabloc[i].length - 1; j++)
                if (tabloc[i][j] == Rules.IS) {
                    res[k][0] = i;
                    res[k][1] = j;
                    k++;
                }
        tabperm = new Enum[res.length * 2][2];
        return res;
    }

    /**
     * cree un tableau représentant toutes les règles issues de blocs de règles aligné en Y
     * @param isLoc la position des blocs Is issue de la methode findIs()
     * @return tableau à deux dimensions : pour chacun des sous tableaux, on a des objets de type Rules(Enum)
     */
    private static Enum[][] findPermY(int[][] isLoc) {
        Enum[][] tabpermY = new Enum[10][2];
        int k = 0;
        for (int[] is : isLoc)
        {
            if (is[0] > 0)
            {
                //on cherche s'il n'y a pas un "personnage" (ex : baba, flag, ect) au dessus du is
                for (int i = 0; i <= character.length - 1; i++)
                {
                    if (tabloc[is[0] - 1][is[1]] == character[i])
                    {
                        tabpermY[k][0] = character[i];
                    }
                }

            }

            if (tabpermY[k][0] != null)
            {
                if (is[0] < tabloc[0].length - 1)
                {
                    // on cherche s'il n 'y a pas une action (ex : you, win, ect) en dessous du is
                    for (int j = 0; j <= actions.length - 1; j++)
                    {
                        if (tabloc[is[0] + 1][is[1]] == actions[j] && tabpermY[k][0] != null)
                        {
                            tabpermY[k][1] = actions[j];
                            k++;
                        }
                    }
                    //si on a trouvé aucune action correspondante, alors on retire notre personnage des permissions
                    if(tabpermY[k][1] == null)
                        tabpermY[k][0] = null;
                }
            }
        }
        return tabpermY;
    }

    /**
     * cree un tableau représentant toutes les règles issues de blocs de règles aligné en X
     * @param isLoc la position des blocs Is issue de la methode findIs()
     * @return tableau à deux dimensions : pour chacun des sous tableaux, on a des objets de type Rules(Enum)
     */

    private static Enum[][] findPermX(int[][] isLoc)
    {
        Enum[][] tabpermX = new Enum[10][2];
        int k = 0;
        for (int[] is : isLoc)
        {
            if (is[1] > 0)
            {
                //on cherche s'il n' y a pas un "personnage" (ex: baba, flag, wall, ect) à gauche du is
                for (int i = 0; i <= character.length - 1; i++)
                {
                    if (tabloc[is[0]][is[1] - 1] == character[i])
                    {
                        tabpermX[k][0] = character[i];
                    }
                }
            }
            if (tabpermX[k][0] != null)
            {
                if (is[1] < tabloc[0].length - 1)
                {
                    // on cherche s'il n 'y a pas une action (ex : you, win, ect) à droite du is
                    for (int j = 0; j <= actions.length - 1; j++)
                    {
                        if (tabloc[is[0]][is[1] + 1] == actions[j] && tabpermX[k][0] != null)
                        {
                            tabpermX[k][1] = actions[j];
                            k++;
                        }

                    }
                    //si on a trouvé aucune action correspondante, alors on retire notre personnage des permissions
                    if(tabpermX[k][1] == null)
                        tabpermX[k][0] = null;
                }
            }
        }
        return tabpermX;
    }

    /**
     * permet de savoir quel objet ou règle se trouve en fonction du String issu du fichier texte "level"
     *
     * @param thing l'élément du fichier texte représentant un objet ou une règle
     * @return l'objet de type "Rules" (voir Rules.java) représentant un objet ou une règle
     */

    private static Enum whatobj(Class<?> thing) {
        if (BlockRules.Is.class.equals(thing)) {
            return Rules.IS;
        } else if (BlockRules.Stop.class.equals(thing)) {
            return Rules.STOP;
        } else if (BlockRules.Push.class.equals(thing)) {
            return Rules.PUSH;
        } else if (BlockRules.TextWall.class.equals(thing)) {
            return Rules.WALL;
        } else if (BlockRules.TextRock.class.equals(thing)) {
            return Rules.ROCK;
        } else if (BlockRules.TextBaba.class.equals(thing)) {
            return Rules.BABA;
        } else if (BlockRules.TextFlag.class.equals(thing)) {
            return Rules.FLAG;
        } else if (BlockRules.You.class.equals(thing)) {
            return Rules.YOU;
        } else if (BlockRules.Win.class.equals(thing)) {
            return Rules.WIN;
        } else if (BlockRules.TextGoop.class.equals(thing)) {
            return Rules.GOOP;
        }else if (BlockRules.Sink.class.equals(thing)){return Rules.SINK;}
        else if (BlockRules.TextLava.class.equals(thing)){return Rules.LAVA;}
        else if (BlockRules.Kill.class.equals(thing)){return Rules.KILL;}
        else if (BlockRules.Sticky.class.equals(thing)){return Rules.STICKY;}
        else if (BlockRules.TextGlue.class.equals(thing)){return Rules.GLUE;}

        return Rules.NONE;
    }
}
