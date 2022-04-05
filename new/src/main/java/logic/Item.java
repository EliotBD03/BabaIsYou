package logic;

public abstract class Item extends Map implements Entity
{
    public int posX;
    public int posY;

    protected String skin;
    protected boolean pushstatus = false;

    public int[] getPos()
    {
        System.out.println(posY + " " + posX);
        int[] pos = {this.posY, this.posX};
        return pos;
    }


    // comprend pas pq qd je fais y ou x + 1, ça passe deux fois dans le if

    protected boolean thingIsYou(Enum[][] tabperm, Rules object)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] == object  && tabperm[i][1] == Rules.YOU)
                return true;
        }
        return false;
    }

    protected boolean canMoveX(Enum[][] tabperm, Rules object, int posx)
    {
        if(posx >= getWidth() - 1 || posx <= 0 || !(thingIsYou(tabperm, object)) || mapO[posY][posx] != null)
        {
            return false;
        }
        return true;
    }

    protected boolean canMoveY(Enum[][] tabperm,Rules object,int posy)
    {
        //if((posY + y < getLength() - 1) && (super.mapO[this.posY + y][this.posX] == null) && (posY + y > 0))
        if(posy >= getLength() - 1 || posy <= 0 || !(thingIsYou(tabperm, object)) || mapO[posy][posX] != null)
        {
            return false;
        }
        return true;
    }

    protected boolean thingIsPushingX(Enum[][] tabperm, int x)
    {
        if(mapO[posY][posX + x] != null && mapO[posY][posX + x].canBePushed(tabperm) && posX + x + x < getWidth() - 1 && posX + x +x > 0)
            return true;
        return false;
    }

    protected boolean thingIsPushingY(Enum[][] tabperm, int y)
    {
        if(mapO[posY + y][posX] != null && mapO[posY + y][posX].canBePushed(tabperm) && posY + y + y < getLength() - 1 && posY + y + y > 0)
            return true;
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
            System.out.println(item);
            switch (input.charAt(0))
            {
                case 'z':
                    if (canMoveY(BigAlgorithm.getTabperm(),item,this.posY - 1))
                    {
                        posY = Actions.up(posY);
                    }
                    else if(thingIsPushingY(BigAlgorithm.getTabperm(),-1))
                    {
                        posY = Actions.pushY(-1, posX, posY, mapO);
                    }
                    break;
                case 's':
                    if (canMoveY(BigAlgorithm.getTabperm(),item,this.posY + 1))
                    {
                        posY = Actions.down(posY);
                    }
                    else if(thingIsPushingY(BigAlgorithm.getTabperm(),1))
                    {
                        posY = Actions.pushY(1, posX, posY, mapO);
                    }
                    break;
                case 'q':
                    if (canMoveX(BigAlgorithm.getTabperm(),item,this.posX - 1))
                    {
                        posX = Actions.left(posX);
                    }
                    else if(thingIsPushingX(BigAlgorithm.getTabperm(),-1))
                    {
                        posX = Actions.pushX(-1, posX, posY, mapO);
                    }
                    break;
                case 'd':
                    if (canMoveX(BigAlgorithm.getTabperm(),item, this.posX + 1))
                    {
                        posX = Actions.right(posX);
                    }
                    else if(thingIsPushingX(BigAlgorithm.getTabperm(),1))
                    {
                        posX = Actions.pushX(1, posX, posY, mapO);
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
