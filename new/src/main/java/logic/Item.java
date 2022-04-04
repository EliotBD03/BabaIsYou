package logic;

public abstract class Item extends Map implements Entity
{
    protected int posX;
    protected int posY;
    protected String skin;


    // comprend pas pq qd je fais y ou x + 1, ça passe deux fois dans le if
    protected void actualiseInstance(Class<?> thing, int posY, int posX)
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
    public boolean canBePushed(Enum[][] tabperm, Rules object)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] ==  object && tabperm[i][1] == Rules.PUSH)
                return true;
        }
        return false;
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

    protected boolean canMoveX(Enum[][] tabperm, Rules object, int posx)
    {
        if(posx >= getWidth() - 1 || posx <= 0 || !(thingIsYou(tabperm, object) || mapO[posY][posx] != null))
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

    protected boolean thingIsPushingX(Enum[][] tabperm, Rules object, int x)
    {
        System.out.println("posx = " + posX + " posy = " + posY);
        if(mapO[posY][posX + x] != null && mapO[posY][posX + x].canBePushed(tabperm, object) && posX + x + x < getWidth() - 1 && posX + x +x > 0)
            return true;
        return false;
    }

    protected boolean thingIsPushingY(Enum[][] tabperm, Rules object, int y)
    {
        System.out.println("posx = " + posX + " posy = " + posY);
        if(mapO[posY + y][posX] != null && mapO[posY + y][posX].canBePushed(tabperm, object) && posY + y + y < getLength() - 1 && posY + y + y > 0)
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
            switch (input.charAt(0))
            {
                case 'z':
                    if (canMoveY(BigAlgorithm.getTabperm(),item,this.posY - 1))
                    {
                        posY = Actions.up(posY);
                    }
                    else if(thingIsPushingY(BigAlgorithm.getTabperm(),item,-1))
                    {
                        posY = Actions.pushY(-1, posX, posY, mapO);
                    }
                    actualiseInstance(Baba.class, posY, posX);
                    break;
                case 's':
                    if (canMoveY(BigAlgorithm.getTabperm(),item,this.posY + 1))
                    {
                        posY = Actions.down(posY);
                    }
                    else if(thingIsPushingY(BigAlgorithm.getTabperm(),item,1))
                    {
                        posY = Actions.pushY(1, posX, posY, mapO);
                    }
                    actualiseInstance(Baba.class, posY, posX);
                    break;
                case 'q':
                    if (canMoveX(BigAlgorithm.getTabperm(),item,this.posX - 1))
                    {
                        posX = Actions.left(posX);
                    }
                    else if(thingIsPushingX(BigAlgorithm.getTabperm(),item,-1))
                    {
                        posX = Actions.pushX(-1, posX, posY, mapO);
                    }
                    actualiseInstance(Baba.class, posY, posX);
                    break;
                case 'd':
                    if (canMoveX(BigAlgorithm.getTabperm(),item, this.posX + 1))
                    {
                        posX = Actions.right(posX);
                    }
                    else if(thingIsPushingX(BigAlgorithm.getTabperm(),item,1))
                    {
                        posX = Actions.pushX(1, posX, posY, mapO);
                    }
                    actualiseInstance(Baba.class, posY, posX);
                    break;
            }
        }
    }


}
