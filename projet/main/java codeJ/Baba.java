public class Baba extends  Map implements Entity
{
    //position de baba
    private int posX = 0;
    private int posY = 0;
    private String skin = "O";
    //accesseur

    public Baba(boolean flag)
    {

    }
    public Baba()
    {
        int[] position = super.searchtype(Baba.class);
        this.posY = position[0];
        this.posX = position[1];
    }

    @Override
    public String getSkin(){return skin;}

    // bouger
    public void move(String input)
    {
        if(babaYou(BigAlgorithm.getTabperm()))
        {
            switch (input.charAt(0))
            {
                case 'z':
                    if (canMoveY(this.posY - 1))
                    {
                        System.out.println("je bouge");
                        this.posY --;
                    }
                    else if(babaIsPushingY(-1))
                    {
                        severalPushY(-1);
                        System.out.println("je pousse");
                    }
                    super.actualiseInstance(Baba.class, posY, posX);
                    break;
                case 's':
                    if (canMoveY( this.posY + 1))
                    {
                        System.out.println("je bouge");
                        this.posY ++;
                    }
                    else if(babaIsPushingY(1))
                    {
                        severalPushY(1);
                        System.out.println("je pousse");
                    }
                    super.actualiseInstance(Baba.class, posY, posX);
                    break;
                case 'q':
                    if (canMoveX(this.posX - 1))
                    {
                        System.out.println("je bouge");
                        this.posX --;
                    }
                    else if(babaIsPushingX(-1))
                    {
                        severalPushX(-1);
                        System.out.println("je pousse");
                    }
                    super.actualiseInstance(Baba.class, posY, posX);
                    break;
                case 'd':
                    if (canMoveX( this.posX + 1))
                    {
                        System.out.println("je bouge");
                        this.posX ++;
                    }
                    else if(babaIsPushingX(1))
                    {
                        severalPushX(1);
                        System.out.println("je pousse");
                    }
                    super.actualiseInstance(Baba.class, posY, posX);
                    break;
            }
        }
        //System.out.println(posX + " " + posY);
    }

    public boolean canMoveX(int posx)
    {
        //if((posX + x < getWidth() - 1) && (super.mapO[this.posY][this.posX + x] == null) && (posX + x > 0))
        if(posx >= getWidth() - 1 || posx <= 0 || !(babaYou(BigAlgorithm.getTabperm())) || mapO[posY][posx] != null)
        {
            System.out.println("peux pas bouger en x");
            return false;
        }
        return true;
    }

    public boolean canMoveY(int posy)
    {
        //if((posY + y < getLength() - 1) && (super.mapO[this.posY + y][this.posX] == null) && (posY + y > 0))
        if(posy >= getLength() - 1 || posy <= 0 || !(babaYou(BigAlgorithm.getTabperm())) || mapO[posy][posX] != null)
        {
            System.out.println("peux pas bouger en y");
            return false;
        }
        return true;
    }

    public boolean babaYou(Enum[][] tabperm)
    {
        for(int i = 0; i <= tabperm.length - 1; i ++)
        {
            if(tabperm[i][0] == Rules.BABA && tabperm[i][1] == Rules.YOU)
                return true;
        }
        System.out.println("BabaIsYou = false, peux plus bouger");
        return false;
    }

    public void babaPushX(int x)
    {
        Entity temp = mapO[posY][posX + x];
        mapO[posY][posX + x] = mapO[posY][posX];
        mapO[posY][posX] = null;
        mapO[posY][posX + x + x] = temp;
    }
    public boolean babaIsPushingX(int x)
    {
        if(mapO[posY][posX + x] != null && mapO[posY][posX + x].canBePushed() && posX + x + x < getWidth() - 1 && posX + x +x > 0)
            return true;
        return false;
    }

    public boolean babaIsPushingY(int y)
    {
        if(mapO[posY + y][posX] != null && mapO[posY + y][posX].canBePushed() && posY + y + y < getLength() - 1 && posY + y + y > 0)
            return true;
        return false;
    }

    public void babaPushY(int y)
    {
        Entity temp = mapO[posY + y][posX];
        mapO[posY + y][posX] = mapO[posY][posX];
        mapO[posY][posX] = null;
        mapO[posY + y + y][posX] = temp;
    }

    public boolean canBePushed()
    {
        return false;
    }

    //travaille dessus...

    public void severalPushY(int y)
    {
        switch (y)
        {
            case -1:
                int i = posY;
                while(mapO[i][posX] != null)
                    i--;
                int first_entity = i;
                while(i < posY  && first_entity - 1 >= 0)
                {
                    mapO[i - 1][posX] = mapO[i][posX];
                    mapO[i][posX] = null;
                    i++;
                }
                if(mapO[posY - 1][posX] == null)
                    posY --;
                break;
            case 1:
                int u = posY;
                while(mapO[u][posX] != null)
                    u++;
                int last_entity = u;
                    while(u > posY && last_entity + 1 <= super.getLength() - 1)
                    {
                        mapO[u + 1][posX] = mapO[u][posX];
                        mapO[u][posX] = null;
                        u--;
                    }
                    if(mapO[posY + 1][posX] == null)
                        posY ++;
                break;

        }

    }
    public void severalPushX(int x)
    {
        switch (x)
        {
            case -1:
                int i = posX;
                while(mapO[posY][i] != null)
                    i--;
                int first_entity = i;
                while(i < posX  && first_entity - 1 >= 0)
                {
                    mapO[posY][i - 1] = mapO[posY][i];
                    mapO[posY][i] = null;
                    i++;
                }
                if(mapO[posY][posX - 1] == null)
                    posX --;
                break;
            case 1:
                int u = posX;
                while(mapO[posY][u] != null)
                    u++;
                int last_entity = u;
                while(u > posX  && last_entity + 1 <= super.getWidth() - 1)
                {
                    mapO[posY][u + 1] = mapO[posY][u];
                    mapO[posY][u] = null;
                    u--;
                }
                if(mapO[posY][posX + 1] == null)
                    posX ++;
                break;
        }
    }

}
