/**
 * cette classe permet de savoir pour quel item du jeu ,on y retrouve sa règle
 */

public class BigAlgorithm extends Map
{
    public static Enum[][] tabloc = new Enum[getLength()][getWidth()];
    public static Enum[][] tabperm;
    private Enum[] character = {Rules.BABA,Rules.ROCK, Rules.FLAG, Rules.WALL};
    private Enum[] actions = {Rules.YOU, Rules.STOP, Rules.WIN, Rules.PUSH};


    /**
     * constructeur permettant de ne executer les methodes à chaques fois
     */

    public BigAlgorithm()
    {
        setTab(super.mapO);
        findPerm(findIS());
    }

    /**
     * accesseur pour le tableau des permissions
     * @return tableau à 2 dimensions
     */
    public static Enum[][] getTabperm(){return tabperm;}

    /**
     * cree notre tableau "tabloc" a deux dimensions regroupant les regles (ex: BabaIsYou)
     * @param map0 le tableau issue de map representant les objets
     */

    public void setTab(Entity[][] map0)
    {
        for (int i = 1; i <= map0.length - 1; i++)
            for(int j = 1; j <= map0[i].length - 1; j++)
            if(map0[i][j] != null)
            {
                //System.out.println("on passe la condition");
                tabloc[i][j] = whatobj(map0[i][j].getClass());
            }
            else
                tabloc[i][j] = Rules.NONE;
    }

    /**
     * cherche dans le tableau "tabloc" les "Is" permettant de faire l'union de deux objets (ex: Baba et You)
     * @return un tableau à deux dimensions regroupant les Is et leurs positions de cette maniere:
     * un sous tableau = Is , les elements de Is => position en y et en x (dans cet ordre)
     */

    public int[][] findIS()
    {
        int x = -1; int y = -1;
        int[][] res = new int[10][2];
        int k = 0;
        for(int i = 0; i <= tabloc.length - 1; i++)
            for(int j = 0 ; j <= tabloc[i].length - 1; j++)
                if(tabloc[i][j] == Rules.IS)
                {
                    res[k][0] = i ; res[k][1] = j;
                    k++;
                }
        tabperm = new Enum[res.length * 2][2];
        return res;
    }

    /**
     * cree un tableau à double dimensions representant l'objet (ex: Baba) et sa regle (ex: You)
     * @param isLoc le tableau cree par la methode "findIs()"
     */

    public void findPerm(int[][] isLoc)
    {

        int k = 0;
        int l = 0;
        for(int[] is : isLoc)
        {
            if (is[0] > 0)
                {
                    for (int i = 0; i <= character.length - 1; i++)
                    {
                        if (tabloc[is[0] - 1][is[1]] == character[i])
                        {
                            tabperm[k][0] = character[i];
                            k++;
                        }
                    }
                }

            if (is[1] > 0)
                {
                    for (int i = 0; i <= character.length - 1; i++)
                    {
                        if (tabloc[is[0]][is[1] - 1] == character[i])
                        {
                            tabperm[k][0] = character[i];
                            k++;

                        }
                    }
                }

            if(is[0] < tabloc[0].length - 1)
            {
                // à faire
                for (int j = 0; j <= actions.length - 1; j++)
                {
                    if (tabloc[is[0] + 1][is[1]] == actions[j])
                    {
                        tabperm[l][1] = actions[j];
                        l++;
                    }
                }
            }

            if(is[1] < tabloc[0].length - 1)
            {
                for(int j = 0; j <= actions.length - 1; j++)
                {
                    if(tabloc[is[0]][is[1] + 1] == actions[j])
                    {
                        tabperm[l][1] = actions[j];
                        l++;
                    }
                }
            }
        }
    }

    /**
     * permet de savoir quel objet ou regle se trouve en fonction du String issu du fichier texte "level"
     * @param thing l'element du fichier texte representant un objet ou une regle
     * @return l'objet de type "Rules" (voir Rules.java) representant un objet ou une regle
     */

    private Enum whatobj(Class<?> thing)
    {
        if (Is.class.equals(thing)) {
            return Rules.IS;
        }
        else if (Stop.class.equals(thing)) {
            return Rules.STOP;
        }
        else if (Push.class.equals(thing)) {
            return Rules.PUSH;
        }
        else if (TextWall.class.equals(thing)) {
            return Rules.WALL;
        }
        else if (TextRock.class.equals(thing)) {
            return Rules.ROCK;
        }
        else if (TextBaba.class.equals(thing)) {
            return Rules.BABA;
        }
        else if (Flag.class.equals(thing)) {
            return Rules.FLAG;
        }
        else if (You.class.equals(thing)) {
            return Rules.YOU;
        }
        else if (Win.class.equals(thing)) {
            return Rules.WIN;
        }
        return Rules.NONE;
    }

    /**
     * permet d'actualiser le tableau de permissions après changement de position d'une instance dans le tableau d'objet
     */
    public void actualise()
    {
        setTab(mapO);
        findPerm(findIS());
    }

/*
    public static void actualiseX(Enum thing, int x)
    {
        switch (x)
        {
            case 1:
                for(int i = 0; i <= tabloc.length - 1; i++)
                    for(int j = 0; j <= tabloc[i].length - 1; j++)
                        if(tabloc[i][j] == thing && j + 1 < getWidth() - 1)
                        {
                            Enum temp = tabloc[i][j];
                            tabloc[i][j] = null;
                            tabloc[i][j+ 1] = temp;
                        }
            case -1:
                for(int i = 0; i <= tabloc.length - 1; i++)
                    for(int j = 0; j <= tabloc[i].length - 1; j++)
                        if(tabloc[i][j] == thing && j - 1 > 0)
                        {
                            Enum temp = tabloc[i][j];
                            tabloc[i][j] = null;
                            tabloc[i][j- 1] = temp;
                        }
        }

    }

    public static void actualiseY(Enum thing, int y)
    {
        switch (y)
        {
            case 1:
                for(int i = 0; i <= tabloc.length - 1; i++)
                    for(int j = 0; j <= tabloc[i].length - 1; j++)
                        if(tabloc[i][j] == thing && i + 1 < getLength() - 1)
                        {
                            Enum temp = tabloc[i][j];
                            tabloc[i][j] = null;
                            tabloc[i + 1][j] = temp;
                        }
            case -1:
                for(int i = 0; i <= tabloc.length - 1; i++)
                    for(int j = 0; j <= tabloc[i].length - 1; j++)
                        if(tabloc[i][j] == thing && i - 1 > 0)
                        {
                            Enum temp = tabloc[i][j];
                            tabloc[i][j] = null;
                            tabloc[i - 1][j] = temp;
                        }
        }
    }
*/
    /**
     * actualise le tableau findperm après déplacement
     */


}