package Model;

/**
 * cette classe statique contient méthodes permettant d'effectuer une action dans le jeu
 */
public class Actions
{
    /**
     * cette methode permet d'incrementer la position en y de l'instance qui l'utilise dans le tableau d'objet
     * @param map_object le tableau d'objet de type Entity issu de la classe "Entity"
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'istance en x dans le tableau
     * @return la position en y qui a été décrémenté de 1
     */
    public static  int up(Entity[][] map_object, int posY, int posX)
    {
         map_object[posY - 1][posX] = map_object[posY][posX];
         map_object[posY][posX] = null;
         posY -= 1;
         return  posY;
    }
    /**
     * cette methode permet décrémenter la position en y de l'instance qui l'utilise dans le tableau d'objet
     * @param map_object le tableau d'objet de type Entity issu de la classe "Entity"
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'istance en x dans le tableau
     * @return la position en y qui a été incrémenté de 1
     */
    public static int down(Entity[][] map_object, int posY, int posX)
    {
        map_object[posY  + 1][posX] = map_object[posY][posX];
        map_object[posY][posX] = null;
        posY += 1;
        return posY;
    }

    /**
     * cette methode permet décrémenter la position en x de l'instance qui l'utilise dans le tableau d'objet
     * @param map_object le tableau d'objet de type Entity issu de la classe "Entity"
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'istance en x dans le tableau
     * @return la position en x qui a été décrémenté de 1
     */
    public static int left(Entity[][] map_object, int posY, int posX)
    {
        map_object[posY][posX - 1] = map_object[posY][posX];
        map_object[posY][posX] = null;
        posX -= 1;
        return posX;
    }

    /**
     * cette methode permet incrémenter la position en x de l'instance qui l'utilise dans le tableau d'objet
     * @param map_object le tableau d'objet de type Entity issu de la classe "Entity"
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'istance en x dans le tableau
     * @return la position en x qui a été incrémenté de 1
     */

    public static  int right(Entity[][] map_object, int posY, int posX)
    {
        map_object[posY][posX + 1] = map_object[posY][posX];
        map_object[posY][posX] = null;
        posX += 1;
        return posX;
    }

    /**
     * cette méthode permet de déplacer plusieurs éléments en x en fonction de la position de l'instance qu'il l'utilise
     * et de son "orientation"
     * @param x l'orientation de l'instance. Autrement dit, si l'instance compte aller à droite (+1) ou à gauche du tableau (-1)
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'instance en x dans le tableau
     * @param map_object le tableau d'objet de type Entity issu de la classe "Entity"
     * @return
     */

    public static  int pushX(int x, int posY,int posX, Entity[][] map_object)
    {
        int temp = posX - 1;
        switch(x)
        {
            case -1:
                    while(map_object[posY][temp] != null && map_object[posY][temp].canBePushed(BigAlgorithm.getTabperm()))
                         temp --;
                    int first_entity = temp ;
                    temp ++;
                    while(temp <= posX && first_entity - 1 >= 0)
                    {
                        if(map_object[posY][first_entity] != null && map_object[posY][first_entity].thingIsStop())
                            break;
                        map_object[posY][temp - 1] = map_object[posY][temp];
                        map_object[posY][temp] = null;
                        temp ++;
                    }
                    temp --;
                    break;
            case 1:
                    temp = posX + 1;
                    while(map_object[posY][temp] != null && map_object[posY][temp].canBePushed(BigAlgorithm.getTabperm()))
                       temp ++;
                    int last_entity = temp;
                    temp --;
                    while(temp >= posX  && last_entity + 1 <= map_object[posY].length - 1)
                    {
                            if(map_object[posY][last_entity] != null && map_object[posY][last_entity].thingIsStop())
                                break;
                            map_object[posY][temp + 1] = map_object[posY][temp];
                            map_object[posY][temp] = null;
                            temp--;
                    }
                    temp += 2;
                    break;
        }
        return  temp;
    }

    /**
     * cette méthode permet de déplacer plusieurs éléments en y en fonction de la position de l'instance qu'il l'utilise
     * et de son "orientation"
     * @param y l'orientation de l'instance. Autrement dit, si l'instance compte aller vers le bas (+1) ou en haut du tableau (-1)
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'instance en x dans le tableau
     * @param map_object le tableau d'objet de type Entity issu de la classe "Entity"
     * @return
     */

    public static  int pushY(int y, int posY, int posX, Entity[][] map_object)
    {
        int temp = posY - 1;
        switch (y)
        {
            case -1:
                while(map_object[temp][posX] != null && map_object[temp][posX].canBePushed(BigAlgorithm.getTabperm()))
                {
                    temp --;
                }
                int first_entity = temp;
                temp ++;
                System.out.println("posy = " + posY +" temp = " + temp);
                while(temp <= posY  && first_entity - 1 >= 0)
                {
                    if(map_object[first_entity][posX] != null && map_object[first_entity][posX].thingIsStop())
                        break;
                    map_object[temp - 1][posX] = map_object[temp][posX];
                    map_object[temp][posX] = null;
                    temp ++;
                }
                temp -= 2;
                break;
            case 1:
                temp = posY + 1;
                while(map_object[temp][posX] != null && map_object[temp][posX].canBePushed(BigAlgorithm.getTabperm()))
                    temp ++;
                int last_entity = temp;
                temp --;
                while(temp >= posY && last_entity + 1 <= map_object.length - 1)
                {
                    if(map_object[last_entity][posX] != null && map_object[last_entity][posX].thingIsStop())
                        break;
                    map_object[temp + 1][posX] = map_object[temp][posX];
                    map_object[temp][posX] = null;
                    temp--;
                }
                temp += 2;
                break;
        }
        return temp;
    }
}
