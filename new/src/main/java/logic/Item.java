package logic;

public class Item extends Map implements Entity
{
    protected int posX;
    protected int posY;
    protected String skin = "E";

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

    private boolean canBePushed(Enum[][] tabperm, Rules object)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] == object && tabperm[i][1] == Rules.PUSH)
                return true;
        }
        return false;
    }

    private boolean thingIsYou(Enum[][] tabperm, Rules object)
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
        if(mapO[posY][posX + x] != null && mapO[posY][posX + x].canBePushed(tabperm, object) && posX + x + x < getWidth() - 1 && posX + x +x > 0)
            return true;
        return false;
    }

}
