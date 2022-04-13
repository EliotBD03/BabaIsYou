package Model;

import java.util.ArrayList;

/**
 *cette classe représente tous les objets contrôlables par le joueur
 */
public abstract class Item extends Environment implements Entity
{
    //représente la position en x d'un objet
    protected int posX;
    //représente la position en x d'un objet
    protected int posY;
    //représente l'apparence d'un objet
    protected String skin;
    //représente la caractéristique d'être contrôlé
    protected boolean youstatus = false;
    //représente la caractéristique d'un objet à être "poussée"
    protected boolean pushstatus = false;
    //représente la caractéristique d'un objet à ne pas pouvoir être bougé
    protected boolean stopstatus = false;
    //représente si l'objet a "gagné"
    private static boolean winstatus = false;
    //représente l'objet qui a aucun status
    protected boolean nostatus = true;
    //représente les coordonnés des objets qui sont win
    private ArrayList<int[]> coordonates_win = new ArrayList<int[]>();
    //la map temporaire contenant tous les objets qui n'ont pas de status
    private static Entity[][] temp_object_map  = new Entity[mapO.length][mapO[0].length];


    /**
     * methode permettant d'être propre aux classes enfantes de ITEM
     * @return but : la methode thingisyou()
     */
    @Override
    public abstract boolean thingIsYou();

    public boolean thingisyou(Enum[][] tabperm, Rules object)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] == object  && tabperm[i][1] == Rules.YOU)
                return true;
        }
        return false;
    }
    /**
     * methode permettant d'être propre aux classes enfantes de ITEM
     * @return but : la methode thingisstop()
     */
    @Override
    public abstract boolean thingIsStop();

    public boolean thingisstop(Enum[][] tabperm, Rules object)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] == object  && tabperm[i][1] == Rules.STOP)
                return true;
        }
        return false;
    }

    @Override
    public boolean canMoveX(Enum[][] tabperm, Rules object,int posy, int posx)
    {
        //vrai si : -posx ne situe pas en dehors des limites de la map
        //          -l'élément se situant à l'endroit indiqué par les coordonnés
        //           est null
        if(posx < getWidth() - 1 && posx > 0 && mapO[posy][posx] == null)
            return true;
        //vrai si : pareil que celle du dessus sauf que l'élément doit avoir "nostatus"
        if(posx < getWidth() - 1 && posx > 0 && mapO[posy][posx].noStatus())
            return true;
        //faux si : aucune des conditions n'est respecté
        return false;
    }

    @Override
    public boolean canMoveY(Enum[][] tabperm,Rules object,int posy, int posx)
    {
        //vrai si : -posy ne situe pas en dehors des limites de la map
        //          -l'élément se situant à l'endroit indiqué par les coordonnés
        //           est null
        if(posy < getLength() - 1 && posy > 0 && mapO[posy][posx] == null)
            return true;
        //vrai si : pareil que celle du dessus sauf que l'élément doit avoir "nostatus"
        if(posy < getLength() - 1 && posy > 0 && mapO[posy][posx].noStatus())
            return true;
        //faux si : aucune des conditions n'est respecté
        return false;
    }

    @Override
    public boolean thingIsPushingX(Enum[][] tabperm, int posy, int posx)
    {
        //vrai si : -posx se situe dans les limites de la map
        //          -l'élément se situant à l'endroit indiqué par les coordonnés
        //           peut être poussé
        if(posx+ 1 < getWidth() - 1 && posX - 1 > 0 && mapO[posy][posx] != null && mapO[posy][posx].canBePushed(tabperm))
            return true;
        //faux si : la condition n'est pas respectée
        return false;
    }

    @Override
    public boolean thingIsPushingY(Enum[][] tabperm, int posy, int posx)
    {
        //vrai si : -posy se situe dans les limites de la map
        //          -l'élément se situant à l'endroit indiqué par les coordonnés
        //           peut être poussé
        if( posy + 1 < getLength() - 1 && posy  - 1> 0 && mapO[posy][posx] != null && mapO[posy][posx].canBePushed(tabperm))
        {
            return true;
        }
        //faux si : la condition n'est pas respectée
        return false;
    }

    /**
     * methode permettant d'être propre aux classes enfantes de ITEM
     * @param input : entrée utilisateur
     */

    protected abstract void move(String input);

    /**
     * la méthode move consiste à effectuer des changements dans la map de type Entity
     * par l'intermédiaire d'une entrée utilisateur et de l'objet issu de Rules correspondant
     * à l'instance de la méthode
     * @param input entrée utilisateur
     * @param item but : correspond à l'objet qui l'instancie (voir dans les classes enfants)
     */

    protected void move(String input, Rules item)
    {
        //on cherche les coordonnés des objets qui ont la caractéristique win
        searchWin();
        //on initialise la map temporaire de type Entity
        //où se retrouve tous les objets qui n'ont aucun status ("noStatus()")
        setTempObjectMap();
        //on vérifie si l'instance qui emploie la méthode peut bien être contrôlé
        if(thingIsYou())
        {
            //on va chercher les 4 cas possibles d'entrée
            //car la différence entre les entrées influera sur le comportement des méthodes
            switch (input.charAt(0))
            {
                //si c'est 'z', on compte aller vers le haut
                case 'z':
                    //les deux boucles for vont nous permettre de parcourir tout le tableau d'objet
                    //pour vérifier à chaque fois si l'objet en position (j,i) est du même type
                    //que l'instance qui utilise cette méthode (voir la première condition)
                    for(int i = 0; i <= mapO.length - 1; i++)
                        for(int j = 0; j <= mapO[i].length - 1; j++)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {
                                //on va vérifier 3 cas: -est ce que l'objet bouge ?
                                //                      -est ce que l'objet a gagné ?
                                //                      -est ce que l'objet pousse ?
                                //note : on ne peut réaliser qu'une action à la fois(else if)
                                if (canMoveY(BigAlgorithm.getTabperm(),item,i - 1, j))
                                    posY = Actions.up(mapO, i, j);

                                else if(thingHasWin(i - 1, j))
                                    winstatus = true;

                                else if(thingIsPushingY(BigAlgorithm.getTabperm(),i - 1, j))
                                    posY = Actions.pushY(-1, i, j, mapO);
                            }
                    break;
                //si c'est 's', on compte aller vers le bas
                case 's':
                    for(int i = mapO.length - 1; i >= 0; i--)
                        for(int j = mapO[i].length - 1; j >= 0; j--)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {
                                //on regarde si on a le droit d'avancer
                                if (canMoveY(BigAlgorithm.getTabperm(),item,i+ 1,j))
                                    posY = Actions.down(mapO, i, j);
                                //on regarde si on a atteint un objet qui est win
                                else if(thingHasWin( i+ 1, j))
                                    winstatus = true;
                                //on regarde si on pousse quelque chose
                                else if(thingIsPushingY(BigAlgorithm.getTabperm(),i + 1, j))
                                    posY = Actions.pushY(1, i, j, mapO);
                            }
                    break;
                //si c'est 'q', on compte aller vers la gauche
                case 'q':
                    for(int i = 0; i <= mapO.length - 1; i++)
                        for(int j = 0; j <= mapO[i].length - 1; j++)
                            if(this.getClass().isInstance(mapO[i][j]))
                            {
                                if (canMoveX(BigAlgorithm.getTabperm(),item,i,j - 1))
                                    posX = Actions.left(mapO, i, j);

                                else if(thingHasWin( i, j - 1))
                                    winstatus = true;

                                else if(thingIsPushingX(BigAlgorithm.getTabperm(),i, j - 1))
                                    posX = Actions.pushX(-1, i, j, mapO);
                            }
                    break;
                //si c'est 'd', on compte aller vers la droite
                case 'd':
                    for(int i = mapO.length - 1; i >= 0; i--)
                        for(int j = mapO[i].length - 1; j >= 0; j--)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {

                                if (canMoveX(BigAlgorithm.getTabperm(),item, i, j + 1))
                                    posX = Actions.right(mapO, i, j);

                                else if(thingHasWin( i, j + 1))
                                    winstatus = true;

                                else if(thingIsPushingX(BigAlgorithm.getTabperm(),i, j + 1))
                                    posX = Actions.pushX(1, i, j, mapO);
                            }
                    break;
            }
        }
        //on va fusionner la map actuel avec la map temporaire
        actualiseObjectMap();
    }

    /**
     * cette méthode permet de savoir si à l'élément en positon (j,i) a la caractéristique d'être win
     * @param i la position en y
     * @param j la postion en x
     * @return vrai si l'élément en position (j,i) est win, faux sinon
     */

    private boolean thingHasWin(int i, int j)
    {
        //on cherche dans notre arraylist si on a les coordonnés donnés en paramètres
        //si c'est le cas -> true sinon -> false
        for(int k = 0; k <= coordonates_win.size() - 1; k++)
            if(i == coordonates_win.get(k)[0] && j == coordonates_win.get(k)[1])
                return true;
        return false;
    }

    /**
     * cette méthode va chercher dans le tableau d'objet toutes les coordonnés des éléments qui sont win
     * et va les transférer dans l'arraylist "coordonates_win"
     */
    private void searchWin()
    {
        //on cherche type de l'objet qui est win
        Entity win_object = BigAlgorithm.dico.get(whichItem(Rules.WIN));
        //on va cherher dans tout le tableau (les deux boucles)
        for(int i = 0; i <= mapO.length - 1; i++)
            for(int j = 0; j <= mapO[i].length -1; j++)
            {
                //si on a aucun objet win, on réinitialise l'arraylist
                if(win_object == null)
                    coordonates_win = new ArrayList<int[]>();
                //sinon si on vérifie si l'objet est du type qu'on cherche
                    // si oui, alors on l'ajoute dans l'arraylist
                else if(mapO[i][j] != null && mapO[i][j].getClass() == win_object.getClass())
                {
                    temp_object_map[i][j] = mapO[i][j];
                    int[] pos = {i,j};
                    coordonates_win.add(pos);
                }
            }
    }

    private Enum whichItem(Rules rules)
    {
        for(int i = 0; i <= BigAlgorithm.getTabperm().length -1; i++)
            if(BigAlgorithm.getTabperm()[i][1] == rules && BigAlgorithm.getTabperm()[i][0] != null)
                return BigAlgorithm.getTabperm()[i][0];
        return null;
    }
/*
    public int[] whichPosition(Enum  item)
    {
        if(item == Rules.WALL)
        {
            int[] pos = searchtype(Wall.class);
            return  pos;
        }
        else if (item == Rules.FLAG)
        {
            int[] pos = searchtype(Flag.class);
            return  pos;
        }
        else if (item == Rules.BABA)
        {
            int[] pos = searchtype(Baba.class);
            return  pos;
        }
        else if (item == Rules.ROCK)
        {
            int[] pos = searchtype(Rock.class);
            return  pos;
        }
        else
            return null;
    }
*/
    public static boolean win()
    {
        return winstatus;
    }


    private void setTempObjectMap()
    {
        boolean flag = false;
        Entity[][] res = new Entity[mapO.length][mapO[0].length];
            for(int i = 0; i <= mapO.length - 1; i++)
                for(int j = 0; j <= mapO[j].length - 2; j++)
                    try
                    {
                        if(temp_object_map[i][j] != null && res[i][j] != temp_object_map[i][j])
                        {
                            break;
                        }
                        if (mapO[i][j].noStatus())
                        {
                            flag = true;
                            res[i][j] = mapO[i][j];
                            mapO[i][j] = null;
                        }
                    }catch (Exception e){}
        if(flag)
            temp_object_map = res;
    }




     private void actualiseObjectMap()
     {
        for(int i = 0; i<= mapO.length - 1; i++)
            for(int j = 0; j <= mapO[i].length - 1; j++)
                if(mapO[i][j] == null && temp_object_map[i][j] != null)
                    mapO[i][j] = temp_object_map[i][j];
     }

     @Override
     public abstract boolean noStatus();

     protected boolean nostatus(Enum[][] tabperm, Rules object)
     {
        for(int i = 0; i <= tabperm.length - 1; i++)
            {
                if (tabperm[i][0] == object)
                    return false;
            }
        return true;
     }

}

