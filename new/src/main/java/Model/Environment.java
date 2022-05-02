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
        //la map de String
        protected static String[][] map;
        //la map d'objet
        protected static Entity[][] mapO;

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
     * accesseur pour la map d'objet
     * @return la map d'objet de type "Entity"
     */

    public static Entity[][] getMapO(){return mapO;}

    /**
     * permet de set la map avec les objets au bon endroit
     * @param dataList apres avoir execute de la methode Extract.setDataList()
     */
     public void setMap(String[][] dataList)
     {
         setStringMap(dataList);
         setObjectMap(dataList);
         actualiseMap();
         new BigAlgorithm();
     }

    /**
     * cette méthode va créer la map de String à partir d'un tableau de donnés
     * @param dataList le tableau de donnés
     */
     private void setStringMap(String[][] dataList)
     {
         length = 21;
         width = 21;
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
            mapO = new Entity[length][width];
            for (int i = 1; i <= dataList.length - 1 ; i++ )
            {
                try
                {
                    Entity object = whatobj(dataList[i][0]);
                    mapO[Integer.parseInt(dataList[i][2]) + 1][Integer.parseInt(dataList[i][1])+ 1] = object;
                }
                catch (Exception e)
                {
                    mapO = new Entity[length][width];
                    System.out.println("the element : " + dataList[i][0] + " does not respect the format of the game.");
                }
            }
        }

        /**
         * permet de savoir quel est le type d'instance qu'on doit faire
         * @param thing l'element du tableau correspondant a l'instance
         * @return l'instance demandé
         */
        private Entity whatobj(String thing) throws Exception
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
                        return new Wall();
                    case "rock":
                        return new Rock();
                    case "baba":
                        return new Baba();
                    case "flag":
                        return new Flag();
                    case "goop":
                        return new Goop();
                    case "lava":
                        return new Lava();
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
                    case "text_lava":
                        return new BlockRules.TextLava();
                    case "kill":
                        return new BlockRules.Kill();
                }
                if(thing.equals(""))
                    return null;
                else
                    throw new Exception();
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
