
    public class Map {
        private static int length;
        private static int width;

        protected static String[][] map;
        public static Entity[][] mapO;


        /*
            public void setLength(int length){this.length = length;}
            public void setWidth(int width){this.width = width;}
        */
        public static int getLength() {
            return length;
        }

        public static int getWidth() {
            return width;
        }
        /**
         * permet de set la map avec les objets au bon endroit
         * @param dataList apres avoir execute de la methode Extract.setDataList()
         */
        public void setMap(String[][] dataList)
        {
            setStringMap(dataList);
            setObjectMap(dataList);
            actualiseMap();
        }

        private void setStringMap(String[][] dataList) {
            this.length = Integer.parseInt(dataList[0][1]) + 1;
            this.width = Integer.parseInt(dataList[0][0]) + 1;
            this.map = new String[length][width];

            for (int i = 0; i <= this.length - 1; i++)
                for (int j = 0; j <= this.width - 1; j++) {
                    map[i][j] = " ";
                    if (j == 0 || j == width - 1 || i == 0 || i == length - 1)
                        map[i][j] = "X";
                }
            for (int i = 1; i <= dataList.length - 1; i++)
                map[Integer.parseInt(dataList[i][2])][Integer.parseInt(dataList[i][1])] = "E";
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
         * faire une sous carte contenant les objects aux differents endroits
         */
        private void setObjectMap(String [][] dataList)
        {
            this.mapO = new Entity[this.length][this.width];
                    for (int i = 1; i <= dataList.length - 1 ; i++ )
                            this.mapO[Integer.parseInt(dataList[i][2])][Integer.parseInt(dataList[i][1])] = whatobj(dataList[i][0]);
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
                    return new Is();
                case "stop":
                    return new Stop();
                case "push":
                    return new Push();
                case "wall":
                    return new Wall();
                case "rock":
                    return new Rock();
                case "baba":
                    return new Baba(true);
                case "flag":
                    return new Flag();
                case "you":
                    return new You();
                case "win":
                    return new Win();
                case "text_wall":
                    return new TextWall();
                case "text_baba":
                    return new TextBaba();
                case "text_flag":
                    return new TextFlag();
                case "text_rock":
                    return new TextRock();
            }
            return null;
        }
        /**
         * affiche la map
         */

        public int[] searchtype(Class<?> thing)
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
// comprend pas pq qd je fais y ou x + 1, ça passe deux fois dans le if
        public void actualiseInstance(Class<?> thing, int posY, int posX)
        {
            for(int i = 0; i <= mapO.length - 1; i++)
                for(int j = 0; j <= mapO[i].length - 1; j++)
                    if (thing.isInstance(mapO[i][j]))
                    {
                        Entity temp = mapO[i][j];
                        mapO[i][j] = null ;
                        mapO[posY][posX] = temp;
                        //System.out.println("c'est actualise");
                    }
        }

        @Override
        public String toString() {
            String res = "";
            for (int i = 0; i <= length - 1; i++) {
                for (int j = 0; j <= width - 1; j++)
                    res += map[i][j];
                res += "\n";
            }
            return res;
        }
    }
