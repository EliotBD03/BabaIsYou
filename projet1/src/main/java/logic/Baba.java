package logic;

public class Baba extends  Map implements Entity
{
    //position de baba
    private int posX = 0;
    private int posY = 0;
    private String skin = "O";
    //accesseur

    /**
     * constructeur servant à la mise en place d'instance dans "mapO" dans la classe "Map"
     * @param flag permet de savoir si on est instancié via "mapO
     */
    public Baba(boolean flag)
    {

    }

    /**
     * constucteur ayant comme coordonnés x et y : la position de l'instance dans "mapO"
     */
    public Baba()
    {
        int[] position = super.searchtype(Baba.class);
        this.posY = position[0];
        this.posX = position[1];
    }

    /**
     * permet de reconnaitre baba dans "map" dans la class "Map"
     * @return un string permettant d'identifier baba
     */

    @Override
    public String getSkin(){return skin;}

    /**
     * change la position de baba (et des objets s'il pousse) en fonction des entrées de l'utilisateur
     * @param input entrée de l'utilisateur
     */

    public void move(String input)
    {
        if(babaYou(BigAlgorithm.getTabperm()))
        {
            switch (input.charAt(0))
            {
                case 'z':
                    if (canMoveY(this.posY - 1))
                    {
                        this.posY --;
                    }
                    else if(babaIsPushingY(-1))
                    {
                        severalPushY(-1);
                    }
                    super.actualiseInstance(Baba.class, posY, posX);
                    break;
                case 's':
                    if (canMoveY( this.posY + 1))
                    {
                        this.posY ++;
                    }
                    else if(babaIsPushingY(1))
                    {
                        severalPushY(1);
                    }
                    super.actualiseInstance(Baba.class, posY, posX);
                    break;
                case 'q':
                    if (canMoveX(this.posX - 1))
                    {
                        this.posX --;
                    }
                    else if(babaIsPushingX(-1))
                    {
                        severalPushX(-1);
                    }
                    super.actualiseInstance(Baba.class, posY, posX);
                    break;
                case 'd':
                    if (canMoveX( this.posX + 1))
                    {
                        this.posX ++;
                    }
                    else if(babaIsPushingX(1))
                    {
                        severalPushX(1);
                    }
                    super.actualiseInstance(Baba.class, posY, posX);
                    break;
            }
        }
    }

    /**
     * permet de savoir en fonction de la position et des regles de savoir si baba peut changer de position
     * @param posx compris en 1 et -1 et permettant de bouger en X
     * @return un booleen "true" si les condionts ne sont pas respectés "false" sinon
     */
    public boolean canMoveX(int posx)
    {
        //if((posX + x < getWidth() - 1) && (super.mapO[this.posY][this.posX + x] == null) && (posX + x > 0))
        if(posx >= getWidth() - 1 || posx <= 0 || !(babaYou(BigAlgorithm.getTabperm())) || mapO[posY][posx] != null)
        {
            return false;
        }
        return true;
    }

    /**
     * permet de savoir en fonction de la position et des regles de savoir si baba peut changer de position
     * @param posy compris en 1 et -1 et permettant de bouger en Y
     * @return un booleen "true" si les condionts ne sont pas respectés "false" sinon
     */

    public boolean canMoveY(int posy)
    {
        //if((posY + y < getLength() - 1) && (super.mapO[this.posY + y][this.posX] == null) && (posY + y > 0))
        if(posy >= getLength() - 1 || posy <= 0 || !(babaYou(BigAlgorithm.getTabperm())) || mapO[posy][posX] != null)
        {
            return false;
        }
        return true;
    }

    /**
     * permet de savoir si la règle "BabaIsYou" est respecté
     * @param tabperm le tableau des permissions issue de "BigAlgorithm.java"
     * @return un booleen "true" si on a "BABA,YOU" dans un des sous-tableaux de "tabperm", "false" sinon
     */

    public boolean babaYou(Enum[][] tabperm)
    {
        for(int i = 0; i <= tabperm.length - 1; i ++)
        {
            if(tabperm[i][0] == Rules.BABA && tabperm[i][1] == Rules.YOU)
                return true;
        }
        return false;
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
