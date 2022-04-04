package logic;

public class Actions
{
    /**
     * decremente de 1 la position d'un objet en y
     * @param posY la position en y d'un objet
     */
    public static  int up(int posY)
    {
        return --posY;
    }
    /**
     * incremente de 1 la position d'un objet en y
     * @param posY la position en y d'un objet
     */
    public static int down(int posY)
    {
       return ++posY ;
    }

    /**
     * decremente de 1 la position d'un objet en x
     * @param posX la position en x d'un objet
     */
    public static int left(int posX)
    {
        return --posX;
    }

    /**
     * incremente de 1 la position d'un objet en x
     * @param posX la position en x d'un objet
     */
    public static  int right(int posX)
    {
        return ++posX ;
    }

    /**
     *  la methode permet de pousser un ou plusieurs elements en x
     * @param map_object  le tableau à deux dimensions représentant la position des objets de type Entity
     * @param posX la position en x de l'objet de type entity qui pousse les autres objets de type Entity
     * @param posY la position en y de l'objet de type entity qui pousse les autres objets de type Entity
     * @param x entier compris entre 1 et -1 correspondant à l'incrementation(1)/decrementation(-1) de la position x des objets se situant dans l'alignement et proche de l'objet en position (posX,posY)
     */
    public static final  int pushX(int x, int posX,int posY, Entity[][] map_object)
    {
        int temp = posX;
        switch(x)
        {
            case -1:
                    while(map_object[posY][temp] != null)
                        temp--;
                    int first_entity = temp + 1;
                    while(temp < posX && first_entity - 1 >= 0)
                    {
                        map_object[posY][temp - 1] = map_object[posY][temp];
                        map_object[posY][temp] = null;
                        temp ++;
                    }
                    if(map_object[posY][posX - 1] == null)
                         return posX --;
                    break;
            case 1:
                    while(map_object[posY][temp] != null)
                        temp ++;
                    int last_entity = temp - 1;
                    while(temp > posX  && last_entity + 1 <= map_object[posY].length - 1)
                    {
                            map_object[posY][temp + 1] = map_object[posY][temp];
                            map_object[posY][temp] = null;
                            temp--;
                    }
                    if(map_object[posY][posX + 1] == null)
                        return posX ++;
                    break;
        }
        return  posX;
    }

    /**
     *  la methode permet de pousser un ou plusieurs elements en y
     * @param map_object  le tableau à deux dimensions représentant la position des objets de type Entity
     * @param posX la position en x de l'objet de type entity qui pousse les autres objets de type Entity
     * @param posY la position en y de l'objet de type entity qui pousse les autres objets de type Entity
     * @param y entier compris entre 1 et -1 correspondant à l'incrementation(1)/decrementation(-1) de la position y des objets se situant dans l'alignement et proche de l'objet en position (posX,posY)
     */

    public static final int pushY(int y, int posX, int posY, Entity[][] map_object)
    {
        int temp = posY;
        switch (y)
        {
            case -1:
                while(map_object[temp][posX] != null)
                    temp--;
                int first_entity = temp++;
                while(temp < posY  && first_entity - 1 >= 0)
                {
                    map_object[temp - 1][posX] = map_object[temp][posX];
                    map_object[temp][posX] = null;
                    temp ++;
                }
                if(map_object[posY - 1][posX] == null)
                    return posY --;
                break;
            case 1:
                while(map_object[temp][posX] != null)
                    temp++;
                int last_entity = temp -- ;
                while(temp > posY && last_entity + 1 <= map_object.length - 1)
                {
                    map_object[temp + 1][posX] = map_object[temp][posX];
                    map_object[temp][posX] = null;
                    temp--;
                }
                if(map_object[posY + 1][posX] == null)
                    return posY ++;
                break;
        }
        return posY;
    }
}
