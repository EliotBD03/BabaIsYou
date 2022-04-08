package logic;

import java.util.ArrayList;
import java.util.Iterator;


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
    //représente la caractéristique d'être contrôlable
    protected boolean youstatus = false;
    //représente la caractéristique d'un objet à être "poussée"
    protected boolean pushstatus = false;
    //représente la caractéristique d'un objet à ne pas pouvoir être bougé
    protected boolean stopstatus = false;
    //représente si l'objet a "gagné"
    private static boolean winstatus = false;
    //représente l'objet qui a aucun status
    protected boolean nostatus = true;

    private ArrayList<int[]> coordonates_win = new ArrayList<int[]>();

    private static Entity[][] temp_object_map  = new Entity[mapO.length][mapO[0].length];



    /**
     * @param tabperm tableau issu de la classe BigAlgorithm
     * @param object représente l'objet qui va être contrôlé par le joueur
     * @return vrai l'instance en question est contrôlable faux sinon
     */
    @Override
    public boolean thingIsYou(Enum[][] tabperm, Rules object)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] == object  && tabperm[i][1] == Rules.YOU)
                return true;
        }
        return false;
    }
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
        if(posx < getWidth() - 1 && posx > 0 && mapO[posy][posx] == null)
            return true;
        if(posx < getWidth() - 1 && posx > 0 && mapO[posy][posx].noStatus())
            return true;

        return false;
    }

    @Override
    public boolean canMoveY(Enum[][] tabperm,Rules object,int posy, int posx)
    {
        if(posy < getLength() - 1 && posy > 0 && mapO[posy][posx] == null)
            return true;
        if(posy < getLength() - 1 && posy > 0 && mapO[posy][posx].noStatus())
            return true;

        return false;
    }

    @Override
    public boolean thingIsPushingX(Enum[][] tabperm, int posy, int posx)
    {
        if(posx+ 1 < getWidth() - 1 && posX - 1 > 0 && mapO[posy][posx] != null && mapO[posy][posx].canBePushed(tabperm))
            return true;
        return false;
    }

    @Override
    public boolean thingIsPushingY(Enum[][] tabperm, int posy, int posx)
    {
        if( posy + 1 < getLength() - 1 && posy  - 1> 0 && mapO[posy][posx] != null && mapO[posy][posx].canBePushed(tabperm))
        {
            return true;
        }
        return false;
    }

    /**
     * change la position de baba (et des objets s'il pousse) en fonction des entrées de l'utilisateur
     * @param input entrée de l'utilisateur
     */

    protected abstract void move(String input);

    protected void move(String input, Rules item)
    {
        setTempObjectMap();
        if(youstatus)
        {
            switch (input.charAt(0))
            {
                case 'z':
                    for(int i = 0; i <= mapO.length - 1; i++)
                        for(int j = 0; j <= mapO[i].length - 1; j++)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {
                                if (canMoveY(BigAlgorithm.getTabperm(),item,i - 1, j))
                                    posY = Actions.up(mapO, i, j);


                                else if(thingHasWin(i - 1, j))
                                    winstatus = true;

                                else if(thingIsPushingY(BigAlgorithm.getTabperm(),i - 1, j))
                                    posY = Actions.pushY(-1, i, j, mapO);
                            }
                    break;
                case 's':
                    for(int i = mapO.length - 1; i >= 0; i--)
                        for(int j = mapO[i].length - 1; j >= 0; j--)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {
                                if (canMoveY(BigAlgorithm.getTabperm(),item,i+ 1,j))
                                    posY = Actions.down(mapO, i, j);

                                else if(thingHasWin( i+ 1, j))
                                    winstatus = true;

                                else if(thingIsPushingY(BigAlgorithm.getTabperm(),i + 1, j))
                                    posY = Actions.pushY(1, i, j, mapO);
                            }
                    break;
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
                case 'd':
                    for(int i = mapO.length - 1; i >= 0; i--)
                        for(int j = mapO[i].length - 1; j >= 0; j--)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {

                                if (canMoveX(BigAlgorithm.getTabperm(),item, i, j + 1))
                                {
                                    posX = Actions.right(mapO, i, j);
                                }

                                else if(thingHasWin( i, j + 1))
                                    winstatus = true;

                                else if(thingIsPushingX(BigAlgorithm.getTabperm(),i, j + 1))
                                {
                                    posX = Actions.pushX(1, i, j, mapO);
                                }
                            }
                    break;
            }
        }
        actualiseObjectMap();
    }

    private boolean thingHasWin(int i, int j)
    {
        Iterator<int[]> li = coordonates_win.iterator();
        while(li.hasNext())
            if(i == li.next()[0] && j == li.next()[1])
                return true;
        return false;
    }

    private void searchWin()
    {
        Enum win_object = whichItem(Rules.WIN);
        for(int i = 0; i <= mapO.length - 1; i++)
            for(int j = 0; j <= mapO[i].length -1; j++)
                if(mapO[i][j].getClass().isInstance(BigAlgorithm.dico.get(win_object)))
                {
                    temp_object_map[i][j] = mapO[i][j];
                    int[] pos = {i,j};
                    coordonates_win.add(pos);
                }
    }

    private Enum whichItem(Rules rules)
    {
        for(int i = 0; i <= BigAlgorithm.getTabperm().length -1; i++)
            if(BigAlgorithm.getTabperm()[i][1] == rules && BigAlgorithm.getTabperm()[i][0] != null)
                return BigAlgorithm.getTabperm()[i][0];
        return null;
    }

    public int[] whichPosition(Enum  item)
    {
        if(item == Rules.WALL)
        {
            int[] pos = searchtype(logic.Wall.class);
            return  pos;
        }
        else if (item == Rules.FLAG)
        {
            int[] pos = searchtype(logic.Flag.class);
            return  pos;
        }
        else if (item == Rules.BABA)
        {
            int[] pos = searchtype(logic.Baba.class);
            return  pos;
        }
        else if (item == Rules.ROCK)
        {
            int[] pos = searchtype(logic.Rock.class);
            return  pos;
        }
        else
            return null;
    }

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

     public abstract boolean noStatus();

     public boolean nostatus(Enum[][] tabperm, Rules object)
     {
        for(int i = 0; i <= tabperm.length - 1; i++)
            {
                if (tabperm[i][0] == object)
                    return false;
            }
        return true;
     }

}

