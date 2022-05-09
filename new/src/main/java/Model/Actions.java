package Model;

/**
 * cette classe statique contient les méthodes permettant d'effectuer une action dans le jeu
 */
public class Actions
{
    /**
     * cette methode permet d'incrémenter la position en y de l'instance qui l'utilise dans le tableau d'objet
     * @param mapObject le tableau d'objet de type Entity issu de la classe "Entity"
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'instance en x dans le tableau
     */
    public static void up(Entity[][] mapObject, int posY, int posX)
    {
         mapObject[posY - 1][posX] = mapObject[posY][posX];
         mapObject[posY][posX] = null;
         posY -= 1;
    }
    /**
     * cette methode permet décrémenter la position en y de l'instance qui l'utilise dans le tableau d'objet
     * @param mapObject le tableau d'objet de type Entity issu de la classe "Entity"
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'instance en x dans le tableau
     */
    public static void down(Entity[][] mapObject, int posY, int posX)
    {
        mapObject[posY  + 1][posX] = mapObject[posY][posX];
        mapObject[posY][posX] = null;
        posY += 1;
    }

    /**
     * cette methode permet décrémenter la position en x de l'instance qui l'utilise dans le tableau d'objet
     * @param mapObject le tableau d'objet de type Entity issu de la classe "Entity"
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'instance en x dans le tableau
     */
    public static void left(Entity[][] mapObject, int posY, int posX)
    {
        mapObject[posY][posX - 1] = mapObject[posY][posX];
        mapObject[posY][posX] = null;
        posX -= 1;
    }

    /**
     * cette methode permet incrémenter la position en x de l'instance qui l'utilise dans le tableau d'objet
     * @param mapObject le tableau d'objet de type Entity issu de la classe "Entity"
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'instance en x dans le tableau
     */

    public static void right(Entity[][] mapObject, int posY, int posX)
    {
        mapObject[posY][posX + 1] = mapObject[posY][posX];
        mapObject[posY][posX] = null;
        posX += 1;
    }

    /**
     * cette méthode permet de déplacer plusieurs éléments en x en fonction de la position de l'instance qu'il l'utilise
     * et de son "orientation"
     * @param x l'orientation de l'instance. Autrement dit, si l'instance compte aller à droite (+1) ou à gauche du tableau (-1)
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'instance en x dans le tableau
     * @param mapObject le tableau d'objet de type Entity issu de la classe "Entity"
     */

    public static void pushX(int x, int posY, int posX, Entity[][] mapObject)
    {
        //cette variable permettra de savoir jusqu'à quel élément on poussera(voir première boucle while)
        int temp = posX - 1;
        switch(x)
        {
            case -1:
                    //tant qu'on est encore sur un élément qui peut être poussé(on va vers la gauche)
                    while(mapObject[posY][temp] != null && mapObject[posY][temp].thingIsPush(BigAlgorithm.getTabperm()))
                         temp --;
                    int first_entity = temp ;
                    temp ++;
                    //tant qu'on n'atteint pas la position de l'élément qui pousse et que la première entité
                    //se situe dans les limites de la map
                    while(temp <= posX && first_entity - 1 >= 0)
                    {
                        //si l'objet sur lequel on tombe est un élément qui ne peut pas être poussé
                        //on arrête la boucle
                        if(mapObject[posY][first_entity] != null && mapObject[posY][first_entity].thingIsStop(BigAlgorithm.getTabperm()))
                            break;
                        //sinon, on déplace les éléments dans la direction voulue
                        mapObject[posY][temp - 1] = mapObject[posY][temp];
                        mapObject[posY][temp] = null;
                        temp ++;
                    }
                    temp --;
                    break;
            case 1:
                    temp = posX + 1;
                    while(mapObject[posY][temp] != null && mapObject[posY][temp].thingIsPush(BigAlgorithm.getTabperm()))
                       temp ++;
                    int last_entity = temp;
                    temp --;
                    while(temp >= posX  && last_entity + 1 <= mapObject[posY].length - 1)
                    {
                            if(mapObject[posY][last_entity] != null && mapObject[posY][last_entity].thingIsStop(BigAlgorithm.getTabperm()))
                                break;
                            mapObject[posY][temp + 1] = mapObject[posY][temp];
                            mapObject[posY][temp] = null;
                            temp--;
                    }
                    temp += 2;
                    break;
        }
    }

    /**
     * cette méthode permet de déplacer plusieurs éléments en y en fonction de la position de l'instance qu'il l'utilise
     * et de son "orientation"
     * @param y l'orientation de l'instance. Autrement dit, si l'instance compte aller vers le bas (+1) ou en haut du tableau (-1)
     * @param posY la position de l'instance en y dans le tableau
     * @param posX la position de l'instance en x dans le tableau
     * @param mapObject le tableau d'objet de type Entity issu de la classe "Entity"
     */

    public static void pushY(int y, int posY, int posX, Entity[][] mapObject)
    {
        int temp = posY - 1;
        switch (y)
        {
            case -1:
                while(mapObject[temp][posX] != null && mapObject[temp][posX].thingIsPush(BigAlgorithm.getTabperm()))
                {
                    temp --;
                }
                int first_entity = temp;
                temp ++;
                System.out.println("posy = " + posY +" temp = " + temp);
                while(temp <= posY  && first_entity - 1 >= 0)
                {
                    if(mapObject[first_entity][posX] != null && mapObject[first_entity][posX].thingIsStop(BigAlgorithm.getTabperm()))
                        break;
                    mapObject[temp - 1][posX] = mapObject[temp][posX];
                    mapObject[temp][posX] = null;
                    temp ++;
                }
                temp -= 2;
                break;
            case 1:
                temp = posY + 1;
                while(mapObject[temp][posX] != null && mapObject[temp][posX].thingIsPush(BigAlgorithm.getTabperm()))
                    temp ++;
                int last_entity = temp;
                temp --;
                while(temp >= posY && last_entity + 1 <= mapObject.length - 1)
                {
                    if(mapObject[last_entity][posX] != null && mapObject[last_entity][posX].thingIsStop(BigAlgorithm.getTabperm()))
                        break;
                    mapObject[temp + 1][posX] = mapObject[temp][posX];
                    mapObject[temp][posX] = null;
                    temp--;
                }
                temp += 2;
                break;
        }
    }
}
