package logic;

public abstract class Item extends Map implements Entity
{
    protected int posX;
    protected int posY;
    protected String skin;
    protected boolean pushstatus = false;


    public int[] getPos()
    {
        int[] pos = {posY, posX};
        return pos;
    }


    // comprend pas pq qd je fais y ou x + 1, ça passe deux fois dans le if
    private void actualiseInstance(int x, int y)
    {
        for(int i = 0; i <= mapO.length - 2; i++)
            for(int j = 0; j <= mapO[i].length - 2; j++)
                if (this.getClass().isInstance(mapO[i][j]))
                {
                    Entity temp =  mapO[i][j];
                    mapO[i][j] = null ;
                    mapO[i + y][j + x] = temp;
                    //System.out.println("c'est actualise");
                }
    }

    protected boolean thingIsYou(Enum[][] tabperm, Rules object)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] == object  && tabperm[i][1] == Rules.YOU)
                return true;
        }
        return false;
    }

    protected boolean canMoveX(Enum[][] tabperm, Rules object,int posy, int posx)
    {
        if(posx >= getWidth() - 1 || posx <= 0 || !(thingIsYou(tabperm, object)) || mapO[posy][posx] != null)
        {
            return false;
        }
        return true;
    }

    protected boolean canMoveY(Enum[][] tabperm,Rules object,int posy, int posx)
    {
        //if((posY + y < getLength() - 1) && (super.mapO[this.posY + y][this.posX] == null) && (posY + y > 0))
        if(posy >= getLength() - 1 || posy <= 0 || !(thingIsYou(tabperm, object)) || mapO[posy][posx] != null)
        {
            return false;
        }
        //System.out.println(mapO[posy][posX] != null + "\n" + mapO[posy][posX);
        return true;
    }

    protected boolean thingIsPushingX(Enum[][] tabperm, int posy, int posx)
    {
        if(posx+ 1 < getWidth() - 1 && posX - 1 > 0 && mapO[posy][posx] != null && mapO[posy][posx].canBePushed(tabperm))
            return true;
        return false;
    }

    protected boolean thingIsPushingY(Enum[][] tabperm, int posy, int posx)
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
        if(thingIsYou(BigAlgorithm.getTabperm(), item))
        {
            switch (input.charAt(0))
            {
                case 'z':
                    for(int i = 0; i <= mapO.length - 1; i++)
                        for(int j = 0; j <= mapO[i].length - 1; j++)
                            if (this.getClass().isInstance(mapO[i][j]))
                        {
                            if (canMoveY(BigAlgorithm.getTabperm(),item,i - 1, j))
                            {
                                    posY = Actions.up(mapO, i, j);
                            }

                            else if(thingIsPushingY(BigAlgorithm.getTabperm(),i - 1, j))
                            {
                                posY = Actions.pushY(-1, i, j, mapO);
                            }
                        }
                    //actualiseInstance(0, -1);
                    break;
                case 's':
                    for(int i = mapO.length - 1; i >= 0; i--)
                        for(int j = mapO[i].length - 1; j >= 0; j--)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {
                                if (canMoveY(BigAlgorithm.getTabperm(),item,i+ 1,j))
                                {
                                    posY = Actions.down(mapO, i, j);
                                }

                                else if(thingIsPushingY(BigAlgorithm.getTabperm(),i + 1, j))
                                {
                                    posY = Actions.pushY(1, i, j, mapO);
                                }
                            }
                    break;
                case 'q':
                    for(int i = 0; i <= mapO.length - 1; i++)
                        for(int j = 0; j <= mapO[i].length - 1; j++)
                            if(this.getClass().isInstance(mapO[i][j]))
                            {
                                if (canMoveX(BigAlgorithm.getTabperm(),item,i,j - 1))
                                {
                                    posX = Actions.left(mapO, i, j);
                                }
                                else if(thingIsPushingX(BigAlgorithm.getTabperm(),i, j - 1))
                                {
                                    posX = Actions.pushX(-1, i, j, mapO);
                                }
                            }
                    break;
                case 'd':
                    for(int i = mapO.length - 1; i >= 0; i--)
                        for(int j = mapO[i].length - 1; j >= 0; j--)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {
                                if (canMoveX(BigAlgorithm.getTabperm(),item, i, j + 1))
                                    posX = Actions.right(mapO, i, j);

                                else if(thingIsPushingX(BigAlgorithm.getTabperm(),i, j + 1))
                                    posX = Actions.pushX(1, i, j, mapO);
                            }
                    break;
            }
        }
    }

    protected boolean thingHasWin(Enum[][] tabperm)
    {
        Enum win_object = whichItem(Rules.WIN);
        int[] wichinstance = whichPosition(win_object);
        if(posY == wichinstance[0] && posX == wichinstance[1])
            return true;
        return false;
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


}
