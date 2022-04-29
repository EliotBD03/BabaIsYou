package Model;

import java.util.ArrayList;

/**
 *cette classe représente tous les objets contrôlables par le joueur
 */
public class Item extends Environment implements Entity
{
    protected Rules object = Rules.NONE;
    //représente la position en x d'un objet
    protected int posX;
    //représente la position en x d'un objet
    protected int posY;
    //représente le status du joueur s'il a atteint l'objectif
    private static boolean winStatus = false;
    //représente les coordonnés de tous les objets qui sont win
    private static ArrayList<int[]> coordonates_win = new ArrayList<int[]>();
    //la map temporaire contenant tous les objets qui n'ont pas de status
    private static Entity[][] tempMapO = new Entity[mapO.length][mapO[0].length];

    /**
     * accesseur pour object de Item
     * @return object
     */

    protected Rules getObject(){return object;}

    /**
     * methode qui sera implémenté dans les classes enfants
     * @return null par défaut
     */
    @Override
    public String getSkin() {
        return null;
    }

    public boolean thingIsYou(Enum[][] tabperm)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] == getObject()  && tabperm[i][1] == Rules.YOU)
                return true;
        }
        return false;
    }

    public boolean thingIsStop(Enum[][] tabperm)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] == getObject()  && tabperm[i][1] == Rules.STOP)
                return true;
        }
        return false;
    }

    /**
     * methode qui sera implémenté dans les classes enfants
     * @return false par défaut, but: retourner nostatus()
     */

    public boolean noStatus(Enum[][] tabperm)
    {
        //si dans tous les éléments de tabperm, on y trouve que notre objet(implicitement il a une règle)
        //alors on return false
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if (tabperm[i][0] == getObject())
                return false;
        }
        //vrai sinon
        return true;
    }

    /**
     * methode qui sera implémenté dans les classes enfants
     * @return false par défaut, but: retourner thingissink()
     */
    public boolean thingIsSink(Enum[][] tabperm)
    {
        for(int i = 0; i<= tabperm.length - 1; i ++)
            if(tabperm[i][0] == getObject() && tabperm[i][1] == Rules.SINK)
                return true;
        return false;
    }


    public boolean thingIsKill(Enum[][] tabperm)
    {
        for(int i = 0; i<= tabperm.length - 1; i ++)
            if(tabperm[i][0] == getObject() && tabperm[i][1] == Rules.KILL)
            {
                return true;
            }
        return false;
    }

    @Override
    public boolean thingIsWin(Enum[][] tabperm) {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] ==  getObject() && tabperm[i][1] == Rules.WIN)
            {
                return true;
            }
        }
        return false;
    }

    public boolean thingIsPush(Enum[][] tabperm)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] ==  getObject() && tabperm[i][1] == Rules.PUSH)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * methode permettant d'être propre aux classes enfantes de ITEM
     * @return but : la methode thingisanother()
     */
    public boolean thingIsAnotherThing(Enum[][] tabperm)
    {
        Enum[] thing = {Rules.BABA,Rules.FLAG,Rules.ROCK,Rules.WALL};

        for(int i = 0; i <= tabperm.length - 1; i++)
            for(int j = 0; j <= thing.length - 1; j++)
                if(tabperm[i][0] == getObject()  && tabperm[i][1] == thing[j])
                    return true;
        return false;
    }

    @Override
    public boolean isItem() {
        return true;
    }

    /**
     * cette méthode permet de récupérer l'élément qui compte remplacer object
     * @param tabperm tableau des permissions
     * @return l'élément qui remplace object
     */
    private Enum getThing(Enum[][] tabperm)
    {
        Enum[] thing = {Rules.BABA,Rules.FLAG,Rules.ROCK,Rules.WALL};

        for(int i = 0; i <= tabperm.length - 1; i++)
            for(int j = 0; j <= thing.length - 1; j++)
                if(tabperm[i][0] == getObject()  && tabperm[i][1] == thing[j])
                    return thing[j];
        return null;
    }

    private void switchObject(Enum[][] tabperm)
    {
        if (thingIsAnotherThing(tabperm))
        {
            Entity thing = BigAlgorithm.dico.get(getThing(tabperm));
            for(int i = 0; i <= mapO.length - 1; i++)
                for(int j = 0; j <= mapO[i].length - 1; j++)
                    if(this.getClass().isInstance(mapO[i][j]))
                    {
                        if(mapO[i][j] == tempMapO
                                [i][j])
                        {

                            tempMapO
                                    [i][j] = null;
                        }
                        mapO[i][j] = thing;
                    }

        }

    }

    /**
     * cette méthode va créer une map temporaire regroupant tous les objets noStatus
     */
    private void setTempObjectMap()
    {
        //le flag nous permet de savoir quand on a le droit de transformer la map temporaire
        //qui en principe est déjà faite
        boolean flag = false;
        Entity[][] res = new Entity[mapO.length][mapO[0].length];
        //on passe sur chaque objet de la map
        for(int i = 0; i <= mapO.length - 1; i++)
            for(int j = 0; j <= mapO[i].length - 1; j++)
            {
                //le try catch sert au cas où map[i][j] est null et donc la méthode noStatus
                //n'est pas applicable
                try
                {
                    boolean mapOcondition  = !mapO[i][j].thingIsYou(BigAlgorithm.getTabperm()) && !mapO[i][j].thingIsStop(BigAlgorithm.getTabperm()) && !mapO[i][j].thingIsPush(BigAlgorithm.getTabperm());
                    //si un objet qui se trouve sur un element de la map tempk, on break
                    //car on pourrait écrase l'objet de temp par l'objet de mapO
                    if(tempMapO[i][j] != null && mapO[i][j] != null && mapO[i][j] != tempMapO[i][j])
                    {
                        res[i][j] = tempMapO[i][j];
                    }
                    //si l'objet est bien noStatus, on ajoute l'élément à notre map temp
                    else if (mapOcondition)
                    {
                        flag = true;
                        res[i][j] = mapO[i][j];
                        mapO[i][j] = null;
                    }
                }catch (NullPointerException e){}
            }
        if(flag)
        {
            tempMapO = res;
        }
    }


    /**
     * cette méthode sert à fusionner la map visible (mapO) avec la map temporaire(tempMapO
     * )
     */
    private void actualiseObjectMap()
    {
        for(int i = 0; i<= mapO.length - 1; i++)
            for(int j = 0; j <= mapO[i].length - 1; j++)
            //si on a rien sur la map visible et qu'il y a un element sur temp
            //alors on le rajoute à la map visible
            {
                if(mapO[i][j] != null && tempMapO[i][j] != null && mapO[i][j].isItem() && tempMapO[i][j].thingIsSink(BigAlgorithm.getTabperm()) && mapO[i][j] != tempMapO[i][j])
                {

                    tempMapO[i][j] = null;
                    mapO[i][j] = tempMapO[i][j];
                }
                else if(mapO[i][j] != null && tempMapO[i][j] != null && mapO[i][j].thingIsYou(BigAlgorithm.getTabperm()) && tempMapO[i][j].thingIsKill(BigAlgorithm.getTabperm()) && mapO[i][j] != tempMapO[i][j])
                {

                    mapO[i][j] = tempMapO[i][j];
                }
                else if(mapO[i][j] != null && tempMapO[i][j] != null && tempMapO[i][j].thingIsWin(BigAlgorithm.getTabperm()) && mapO[i][j].thingIsYou(BigAlgorithm.getTabperm()))
                    winStatus = true;
                else if(mapO[i][j] == null && tempMapO[i][j] != null)
                {
                    mapO[i][j] = tempMapO[i][j];
                    tempMapO[i][j] = null;
                }
            }
    }


    /**
     * cette méthode permet de savoir si à l'élément en positon (j,i) a la caractéristique d'être win
     * @param i la position en y
     * @param j la postion en x
     * @return vrai si l'élément en position (j,i) est win, faux sinon
     */

    private boolean thingHasWin(int i, int j)
    {
        //on cherche dans notre arraylist si on a les coordonnés donnés en paramètres
        //si c'est le cas -> true sinon -> false
        for(int k = 0; k <= coordonates_win.size() - 1; k++)
        {
            System.out.println(i + " " + j + "win : " + coordonates_win.get(k)[0] + " " + coordonates_win.get(k)[1]);
            if(i == coordonates_win.get(k)[0] && j == coordonates_win.get(k)[1])
                return true;
        }
        return false;
    }

    /**
     * cette méthode va chercher dans le tableau d'objet toutes les coordonnés des éléments qui sont win
     * et va les transférer dans l'arraylist "coordonates_win"
     */
    private boolean searchWin()
    {
        //on va cherher dans tout le tableau (les deux boucles)
        for(int i = 0; i <= mapO.length - 1; i++)
            for(int j = 0; j <= mapO[i].length -1; j++)
            {
                //sinon si on vérifie si l'objet est du type qu'on cherche
                // si oui, alors on l'ajoute dans l'arraylist
                if(tempMapO[i][j] != null && tempMapO[i][j].thingIsWin(BigAlgorithm.getTabperm()) || mapO[i][j] != null && mapO[i][j].thingIsWin(BigAlgorithm.getTabperm()))
                {
                    System.out.println("je suis win");
                    int[] pos = {i,j};
                    coordonates_win.add(pos);
                    return true;
                }
            }
        coordonates_win = new ArrayList<int[]>();
        return false;
    }


    /**
     * cette méthode retourne le status win des objets de type Item
     * ie : permet de savoir si on a gagné
     * @return l'attribut statique des objets de type Item
     */
    public static boolean win()
    {
        if(winStatus)
        {
            winStatus = false;
            tempMapO
                    = new Entity[mapO.length][mapO[0].length];
            coordonates_win = new ArrayList<>();
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveX(Enum[][] tabperm,int posy, int posx)
    {
        //vrai si : -posx ne situe pas en dehors des limites de la map
        //          -l'élément se situant à l'endroit indiqué par les coordonnés
        //           est null
        if(posx < getWidth() - 1 && posx > 0 && mapO[posy][posx] == null)
            return true;
        //vrai si : pareil que celle du dessus sauf que l'élément doit avoir "nostatus"
        if(posx < getWidth() - 1 && posx > 0 && (mapO[posy][posx].noStatus(BigAlgorithm.getTabperm()) || mapO[posy][posx].thingIsSink(BigAlgorithm.getTabperm())))
            return true;
        //faux si : aucune des conditions n'est respecté
        return false;
    }

    @Override
    public boolean canMoveY(Enum[][] tabperm,int posy, int posx)
    {
        //vrai si : -posy ne situe pas en dehors des limites de la map
        //          -l'élément se situant à l'endroit indiqué par les coordonnés
        //           est null
        if(posy < getLength() - 1 && posy > 0 && mapO[posy][posx] == null)
            return true;
        //vrai si : pareil que celle du dessus sauf que l'élément doit avoir "nostatus"
        if(posy < getLength() - 1 && posy > 0 && (mapO[posy][posx].noStatus(BigAlgorithm.getTabperm()) || mapO[posy][posx].thingIsSink(BigAlgorithm.getTabperm())))
            return true;
        //faux si : aucune des conditions n'est respecté
        return false;
    }

    @Override
    public boolean thingIsPushingX(Enum[][] tabperm, int posy, int posx)
    {
        //vrai si : -posx se situe dans les limites de la map
        //          -l'élément se situant à l'endroit indiqué par les coordonnés
        //           peut être poussé
        if(posx+ 1 < getWidth() - 1 && posX - 1 > 0 && mapO[posy][posx] != null && mapO[posy][posx].thingIsPush(tabperm))
            return true;
        //faux si : la condition n'est pas respectée
        return false;
    }

    @Override
    public boolean thingIsPushingY(Enum[][] tabperm, int posy, int posx)
    {
        //vrai si : -posy se situe dans les limites de la map
        //          -l'élément se situant à l'endroit indiqué par les coordonnés
        //           peut être poussé
        if( posy + 1 < getLength() - 1 && posy  - 1> 0 && mapO[posy][posx] != null && mapO[posy][posx].thingIsPush(tabperm))
        {
            return true;
        }
        //faux si : la condition n'est pas respectée
        return false;
    }

    /**
     * la méthode move consiste à effectuer des changements dans la map de type Entity
     * par l'intermédiaire d'une entrée utilisateur et de l'objet issu de Rules correspondant
     * à l'instance de la méthode
     * @param input entrée utilisateur
     */

    public void move(String input)
    {
        //on va d'office regarder si des objets sont inter-changeables
        switchObject(BigAlgorithm.getTabperm());
        //on cherche les coordonnés des objets qui ont la caractéristique win
        //on initialise la map temporaire de type Entity
        //où se retrouve tous les objets qui n'ont aucun status ("noStatus()")
        setTempObjectMap();
        searchWin();

        //on vérifie si l'instance qui emploie la méthode peut bien être contrôlé
        if(thingIsYou(BigAlgorithm.getTabperm()))
        {

            //on va chercher les 4 cas possibles d'entrée
            //car la différence entre les entrées influera sur le comportement des méthodes
            switch (input.charAt(0))
            {
                //si c'est 'z', on compte aller vers le haut
                case 'z':

                    //les deux boucles for vont nous permettre de parcourir tout le tableau d'objet
                    //pour vérifier à chaque fois si l'objet en position (j,i) est du même type
                    //que l'instance qui utilise cette méthode (voir la première condition)
                    for(int i = 0; i <= mapO.length - 1; i++)
                        for(int j = 0; j <= mapO[i].length - 1; j++)
                        {
                            if (this.getClass().isInstance(mapO[i][j]))
                            {

                                //on va vérifier 4 cas: -est ce que l'objet va disparaitre(ie: l'objet devant lui est sink)
                                //                      -est ce que l'objet bouge ?
                                //                      -est ce que l'objet a gagné ?
                                //                      -est ce que l'objet pousse ?
                                //note : on ne peut réaliser qu'une action à la fois(else if)
                                //on regarde si on a atteint un objet qui est win
                                if (canMoveY(BigAlgorithm.getTabperm(),i - 1, j))
                                {
                                    posY = Actions.up(mapO, i, j);
                                }
                                else if(thingHasWin( i+ 1, j))
                                    winStatus = true;
                                else if(thingIsPushingY(BigAlgorithm.getTabperm(),i - 1, j))
                                    posY = Actions.pushY(-1, i, j, mapO);
                            }
                        }
                    break;
                //si c'est 's', on compte aller vers le bas
                case 's':
                    for(int i = mapO.length - 1; i >= 0; i--)
                        for(int j = mapO[i].length - 1; j >= 0; j--)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {
                                //on regarde si on a le droit d'avancer
                                if (canMoveY(BigAlgorithm.getTabperm(),i+ 1,j))
                                    posY = Actions.down(mapO, i, j);
                                //on regarde si on a atteint un objet qui est win
                                else if(thingHasWin( i+ 1, j))
                                    winStatus = true;
                                //on regarde si on pousse quelque chose
                                else if(thingIsPushingY(BigAlgorithm.getTabperm(),i + 1, j))
                                    posY = Actions.pushY(1, i, j, mapO);
                            }
                    break;
                //si c'est 'q', on compte aller vers la gauche
                case 'q':
                    for(int i = 0; i <= mapO.length - 1; i++)
                        for(int j = 0; j <= mapO[i].length - 1; j++)
                            if(this.getClass().isInstance(mapO[i][j]))
                            {
                                if (canMoveX(BigAlgorithm.getTabperm(),i,j - 1))
                                    posX = Actions.left(mapO, i, j);

                               else if(thingHasWin( i, j - 1))
                                   winStatus = true;

                                else if(thingIsPushingX(BigAlgorithm.getTabperm(),i, j - 1))
                                    posX = Actions.pushX(-1, i, j, mapO);
                            }
                    break;
                //si c'est 'd', on compte aller vers la droite
                case 'd':
                    for(int i = mapO.length - 1; i >= 0; i--)
                        for(int j = mapO[i].length - 1; j >= 0; j--)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {
                                if (canMoveX(BigAlgorithm.getTabperm(), i, j + 1))
                                    posX = Actions.right(mapO, i, j);

                                else if(thingHasWin( i, j + 1))
                                    winStatus = true;

                                else if(thingIsPushingX(BigAlgorithm.getTabperm(),i, j + 1))
                                    posX = Actions.pushX(1, i, j, mapO);
                            }
                    break;
            }
        }
        BigAlgorithm.actualise();
        //on va fusionner la map actuel avec la map temporaire
        actualiseObjectMap();
    }
}

