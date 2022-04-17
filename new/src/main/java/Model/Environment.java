package Model;

/**
 * cette classe représente le concept de carte
 * ie: c'est que va se créer deux maps : map de String(affichable dans le terminale) et la map d'objet (qui va être manipulé)
 */
public class Environment {
        //la longueur des deux maps
        private static int length;
        //la largeur des deux maps
        private static int width;
        //extension
        private static int  extensionx;
        private static int extensiony;
        //la map de String
        protected static String[][] map;
        //la map d'objet
        protected static Entity[][] mapO;

        protected static BigAlgorithm rules;

    /**
     * accesseur pour la map de String
     * @return map
     */
    public String[][] getMap(){return map;}
    public static String[][] getStringMap(String[][] map)
    {
        String[][] res = new String[map.length][map[0].length];
        for (int i = 0; i <= map.length - 1; i++)
            for (int j = 0; j <= map[i].length - 1; j++)
                res[i][j] = map[i][j];
        return res;
    }

    /**
     * accesseur pour la longueur de la map
     * @return length
     */
    public static int getLength() {return length;}

    /**
     * accesseur pour la largeur de la map
     * @return width
     */
    public static int getWidth() {return width;}
    /**
     * permet de set la map avec les objets au bon endroit
     * @param dataList apres avoir execute de la methode Extract.setDataList()
     */
     public void setMap(String[][] dataList)
     {
         setStringMap(dataList);
         setObjectMap(dataList);
         actualiseMap();
         rules = new BigAlgorithm();
     }

    /**
     * cette méthode va créer la map de String à partir d'un tableau de donnés
     * @param dataList le tableau de donnés
     */
     private void setStringMap(String[][] dataList)
     {
         extensionx = 20 - Integer.parseInt(dataList[0][1]);
         extensiony = 20 - Integer.parseInt(dataList[0][0]);
         length = Integer.parseInt(dataList[0][1]) + extensionx;
         width = Integer.parseInt(dataList[0][0]) + extensiony;
         map = new String[length][width];

         for (int i = 0; i <= length - 1; i++)
             for (int j = 0; j <= width - 1; j++)
             {
                 map[i][j] = " ";
                 //les X représenteront les limites de la map
                 if (j == 0 || j == width - 1 || i == 0 || i == length - 1)
                        map[i][j] = "X";
             }
        }

        /**
         * apres avoir fait la map "d'objet", on actualise la map de String
         */
        public void actualiseMap()
        {
            for (int i = 1; i <= mapO.length - 2; i++)
                for(int j = 1; j <= mapO[i].length - 2; j++)
                    if (mapO[i][j] != null)
                    {
                        map[i][j] = mapO[i][j].getSkin();
                    }
                    else
                        map[i][j] = " ";
        }
        /**
         * faire une sous carte contenant les objects aux différents endroits
         */
        private void setObjectMap(String [][] dataList)
        {
            this.mapO = new Entity[this.length][this.width];
                    for (int i = 1; i <= dataList.length - 1 ; i++ )
                            //on va centrer les éléments de la map (extension / 2)
                            this.mapO[Integer.parseInt(dataList[i][2]) + 1][Integer.parseInt(dataList[i][1]) + 1] = whatobj(dataList[i][0]);
        }

        /**
         * permet de savoir quel est le type d'instance qu'on doit faire
         * @param thing l'element du tableau correspondant a l'instance
         * @return l'instance demandé
         */
        private Entity whatobj(String thing)
        {
            switch (thing)
            {
                case "is":
                    return new BlockRules.Is();
                case "stop":
                    return new BlockRules.Stop();
                case "push":
                    return new BlockRules.Push();
                case "wall":
                    return new Wall(true);
                case "rock":
                    return new Rock(true);
                case "baba":
                    return new Baba(true);
                case "flag":
                    return new Flag(true);
                case "goop":
                    return new Goop(true);
                case "you":
                    return new BlockRules.You();
                case "win":
                    return new BlockRules.Win();
                case "text_wall":
                    return new BlockRules.TextWall();
                case "text_baba":
                    return new BlockRules.TextBaba();
                case "text_flag":
                    return new BlockRules.TextFlag();
                case "text_rock":
                    return new BlockRules.TextRock();
                case "text_goop":
                    return new BlockRules.TextGoop();
                case "sink":
                    return new BlockRules.Sink();
            }
            return null;
        }
        /**
         * affiche la map
         */

        public String toString() {
            String res = "";
            for (int i = 0; i <= length - 1; i++) {
                for (int j = 0; j <= width - 1; j++)
                    res += map[i][j];
                res += "\n";
            }
            return res;
        }

    /**
     * cette méthode va rechercher à partir d'un type, les coordonnés de l'élément de même type dans le tableau d'objet
     * @param thing le type de l'élément que l'on cherche
     * @return la position de l'objet ((y,x)->plus facile pour l'implémentation dans un tableau)
     */
    protected int[] searchtype(Class<?> thing)
    {
        int x = -1 ; int y = -1;
        int[] res = new int[2];

        for(int i = 0; i <= mapO.length - 1; i++)
            for(int j = 0; j <= mapO[i].length - 1; j++)
                if (thing.isInstance(mapO[i][j]))
                {
                    y = i; x = j;
                }
        res[0] = y; res[1] = x;
        return res;
    }
    }
