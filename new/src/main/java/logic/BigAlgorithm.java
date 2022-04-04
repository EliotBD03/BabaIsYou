package logic;

/**
 * cette classe permet de savoir pour quel item du jeu ,on y retrouve sa règle
 */

public class BigAlgorithm extends Map {
    public static Enum[][] tabloc = new Enum[getLength()][getWidth()];
    private static Enum[][] tabperm;
    private Enum[] character = {Rules.BABA, Rules.ROCK, Rules.FLAG, Rules.WALL};
    private Enum[] actions = {Rules.YOU, Rules.STOP, Rules.WIN, Rules.PUSH};


    /**
     * constructeur permettant de ne executer les methodes à chaques fois
     */

    public BigAlgorithm() {
        setTab(super.mapO);
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
     * cree notre tableau "tabloc" a deux dimensions regroupant les regles (ex: BabaIsYou)
     *
     * @param map0 le tableau issue de map representant les objets
     */

    public void setTab(Entity[][] map0) {
        for (int i = 1; i <= map0.length - 1; i++)
            for (int j = 1; j <= map0[i].length - 1; j++)
                if (map0[i][j] != null) {
                    //System.out.println("on passe la condition");
                    tabloc[i][j] = whatobj(map0[i][j].getClass());
                } else
                    tabloc[i][j] = Rules.NONE;
    }

    /**
     * rassemble les tableaux de permisions en x et en y dans un même tableau
     * @param tabpermX issue de la methode findPermX
     * @param tabpermY issue de la methode findPermY
     */
    public void setTabperm(Enum[][] tabpermX, Enum[][] tabpermY)
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
     * cherche dans le tableau "tabloc" les "Is" permettant de faire l'union de deux objets (ex: Baba et You)
     *
     * @return un tableau à deux dimensions regroupant les Is et leurs positions de cette maniere:
     * un sous tableau = Is , les elements de Is => position en y et en x (dans cet ordre)
     */

    public int[][] findIS() {
        int x = -1;
        int y = -1;
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
     * cree un tableau representant toutes les regles issue de blocs de regles aligné en Y
     * @param isLoc la position des blocs Is issue de la methode findIs()
     * @return tableau à deux dimensions : pour chacun des sous tableaux, on des objets de type Rules(Enum)
     */
    public Enum[][] findPermY(int[][] isLoc) {
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
                        System.out.println("perso : " + tabpermY[k][0] + " pos : " + k + " " + 0 + " instruc 1");
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
                        if (tabloc[is[0] + 1][is[1]] == actions[j])
                        {
                            tabpermY[k][1] = actions[j];
                            System.out.println("action : " + tabpermY[k][1] + " pos : " + k + " " + 1 + " instruc 3");
                            k++;
                        }
                    }
                }
            }
        }
        return tabpermY;
    }

    /**
     * cree un tableau representant toutes les regles issue de blocs de regles aligné en X
     * @param isLoc la position des blocs Is issue de la methode findIs()
     * @return tableau à deux dimensions : pour chacun des sous tableaux, on des objets de type Rules(Enum)
     */

    public Enum[][] findPermX(int[][] isLoc)
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
                        System.out.println("perso : " + tabpermX[k][0] + " pos : " + k + " " + 0 + " instruc 2");
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
                        if (tabloc[is[0]][is[1] + 1] == actions[j])
                        {
                            tabpermX[k][1] = actions[j];
                            System.out.println("action : " + tabpermX[k][1] + " pos : " + k + " " + 1 + " instruc 4");
                            k++;
                        }
                    }
                }
            }
        }
        return tabpermX;
    }

    /**
     * permet de savoir quel objet ou regle se trouve en fonction du String issu du fichier texte "level"
     *
     * @param thing l'element du fichier texte representant un objet ou une regle
     * @return l'objet de type "Rules" (voir Rules.java) representant un objet ou une regle
     */

    private Enum whatobj(Class<?> thing) {
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
        }
        return Rules.NONE;
    }

    private Entity wichItem(Enum item)
    {
        if(item == Rules.WALL)
        {
            int[] pos = searchtype(Wall.class);
            return  mapO[pos[0]][pos[1]];
        }
        else if (item == Rules.FLAG)
        {
            int[] pos = searchtype(Flag.class);
            return  mapO[pos[0]][pos[1]];
        }
        else if (item == Rules.BABA)
        {
            int[] pos = searchtype(Baba.class);
            return  mapO[pos[0]][pos[1]];
        }
        else if (item == Rules.ROCK)
        {
            int[] pos = searchtype(Rock.class);
            return  mapO[pos[0]][pos[1]];
        }
        else
            return null;
    }

    /**
     * permet d'actualiser le tableau de permissions après changement de position d'une instance dans le tableau d'objet
     */
    public void actualise() {
        setTab(mapO);
        int[][] current_is = findIS();
        setTabperm(findPermX(current_is), findPermY(current_is));
        int i = 0;
    }

    public Entity searchThingYou()
    {
        for(int i = 0; i<= tabperm.length - 1; i++)
            if(tabperm[i][1] == Rules.YOU && tabperm[i][0] != null)
                return wichItem(tabperm[i][0]);
        return null;
    }
}
