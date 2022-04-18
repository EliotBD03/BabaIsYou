package Model;

import java.util.ArrayList;
import java.util.Queue;

/**
 *cette classe représente tous les objets contrôlables par le joueur
 */
public class Item extends Environment implements Entity
{
    protected static BigAlgorithm rules = new BigAlgorithm();
    //représente la position en x d'un objet
    protected int posX;
    //représente la position en x d'un objet
    protected int posY;
    //représente l'apparence d'un objet
    protected String skin;
    //représente la caractéristique d'être contrôlé
    protected boolean youStatus = false;
    //représente la caractéristique d'un objet à être "poussée"
    protected boolean pushStatus = false;
    //représente la caractéristique d'un objet à ne pas pouvoir être bougé
    protected boolean stopStatus = false;
    //représente si l'objet a "gagné"
    private static boolean winStatus = false;
    //représente l'objet qui est sink
    protected boolean sinkStatus = false;
    //représente l'objet qui a aucun status
    protected boolean noStatus = true;
    //représente les coordonnés des objets qui sont win
    private ArrayList<int[]> coordonates_win = new ArrayList<int[]>();
    //la map temporaire contenant tous les objets qui n'ont pas de status
    private static Entity[][] temp_object_map  = new Entity[mapO.length][mapO[0].length];


    /**
     * methode permettant d'être propre aux classes enfantes de ITEM
     * @return but : la methode thingisyou()
     */
    @Override
    public boolean thingIsYou(){return false;};

    protected boolean thingisyou(Enum[][] tabperm, Rules object)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] == object  && tabperm[i][1] == Rules.YOU)
                return true;
        }
        return false;
    }
    /**
     * methode permettant d'être propre aux classes enfantes de ITEM
     * @return but : la methode thingisstop()
     */
    @Override
    public  boolean thingIsStop(){return false;}

    /**
     * methode permettant d'être propre aux classes enfantes de ITEM
     * @return but : la methode thingisanother()
     */
    @Override
    public boolean thingIsAnotherThing(Enum[][] tabperm) {
        return false;
    }

    protected boolean thingisanortherthing(Enum[][] tabperm, Rules object)
    {
        Enum[] thing = {Rules.BABA,Rules.FLAG,Rules.ROCK,Rules.WALL};

        for(int i = 0; i <= tabperm.length - 1; i++)
            for(int j = 0; j <= thing.length - 1; j++)
                if(tabperm[i][0] == object  && tabperm[i][1] == thing[j])
                    return true;
        return false;
    }

    protected Enum getThing(Enum[][] tabperm){return null;}
    /**
     * cette méthode permet de récupérer l'élément qui compte remplacer object
     * @param tabperm tableau des permissions
     * @param object objet qui compte être remplacé
     * @return l'élément qui remplace object
     */
    protected Enum getthing(Enum[][] tabperm, Rules object)
    {
        Enum[] thing = {Rules.BABA,Rules.FLAG,Rules.ROCK,Rules.WALL};

        for(int i = 0; i <= tabperm.length - 1; i++)
            for(int j = 0; j <= thing.length - 1; j++)
                if(tabperm[i][0] == object  && tabperm[i][1] == thing[j])
                    return thing[j];
        return null;
    }

    private void swithObject(Enum[][] tabperm)
    {
        if (thingIsAnotherThing(tabperm))
        {
            Entity thing = BigAlgorithm.dico.get(getThing(tabperm));
            for(int i = 0; i <= mapO.length - 1; i++)
                for(int j = 0; j <= mapO[i].length - 1; j++)
                    if(this.getClass().isInstance(mapO[i][j]))
                        mapO[i][j] = thing;

        }

    }

    protected boolean thingisstop(Enum[][] tabperm, Rules object)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] == object  && tabperm[i][1] == Rules.STOP)
                return true;
        }
        return false;
    }

    @Override
    public boolean isItem() {
        return true;
    }

    /**
     * methode qui sera implémenté dans les classes enfants
     * @return null par défaut
     */
    @Override
    public String getSkin() {
        return null;
    }
    /**
     * methode qui sera implémenté dans les classes enfants
     * @return false par défaut
     */
    @Override
    public boolean canBePushed(Enum[][] tabperm) {
        return false;
    }

    @Override
    public boolean canMoveX(Enum[][] tabperm, Rules object,int posy, int posx)
    {
        //vrai si : -posx ne situe pas en dehors des limites de la map
        //          -l'élément se situant à l'endroit indiqué par les coordonnés
        //           est null
        if(posx < getWidth() - 1 && posx > 0 && mapO[posy][posx] == null)
            return true;
        //vrai si : pareil que celle du dessus sauf que l'élément doit avoir "nostatus"
        if(posx < getWidth() - 1 && posx > 0 && (mapO[posy][posx].noStatus() || mapO[posy][posx].thingIsSink()))
            return true;
        //faux si : aucune des conditions n'est respecté
        return false;
    }

    @Override
    public boolean canMoveY(Enum[][] tabperm,Rules object,int posy, int posx)
    {
        //vrai si : -posy ne situe pas en dehors des limites de la map
        //          -l'élément se situant à l'endroit indiqué par les coordonnés
        //           est null
        if(posy < getLength() - 1 && posy > 0 && mapO[posy][posx] == null)
            return true;
        //vrai si : pareil que celle du dessus sauf que l'élément doit avoir "nostatus"
        if(posy < getLength() - 1 && posy > 0 && (mapO[posy][posx].noStatus() || mapO[posy][posx].thingIsSink()))
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
        if(posx+ 1 < getWidth() - 1 && posX - 1 > 0 && mapO[posy][posx] != null && mapO[posy][posx].canBePushed(tabperm))
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
        if( posy + 1 < getLength() - 1 && posy  - 1> 0 && mapO[posy][posx] != null && mapO[posy][posx].canBePushed(tabperm))
        {
            return true;
        }
        //faux si : la condition n'est pas respectée
        return false;
    }

    /**
     * methode permettant d'être propre aux classes enfantes de ITEM
     * @param input : entrée utilisateur
     */

    protected void move(String input){};

    /**
     * la méthode move consiste à effectuer des changements dans la map de type Entity
     * par l'intermédiaire d'une entrée utilisateur et de l'objet issu de Rules correspondant
     * à l'instance de la méthode
     * @param input entrée utilisateur
     * @param item but : correspond à l'objet qui l'instancie (voir dans les classes enfants)
     */

    protected void move(String input, Rules item)
    {
        //on va d'office regarder si des objets sont inter-changeables
        swithObject(rules.getTabperm());
        //on cherche les coordonnés des objets qui ont la caractéristique win
        searchWin();
        //on initialise la map temporaire de type Entity
        //où se retrouve tous les objets qui n'ont aucun status ("noStatus()")
        setTempObjectMap();
        //on vérifie si l'instance qui emploie la méthode peut bien être contrôlé
        if(thingIsYou())
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
                            if (this.getClass().isInstance(mapO[i][j]))
                            {
                                //on va vérifier 4 cas: -est ce que l'objet va disparaitre(ie: l'objet devant lui est sink)
                                //                      -est ce que l'objet bouge ?
                                //                      -est ce que l'objet a gagné ?
                                //                      -est ce que l'objet pousse ?
                                //note : on ne peut réaliser qu'une action à la fois(else if)
                                if (canMoveY(rules.getTabperm(),item,i - 1, j))
                                {
                                    posY = Actions.up(mapO, i, j);
                                }
                                else if(thingHasWin(i - 1, j))
                                    winStatus = true;

                                else if(thingIsPushingY(rules.getTabperm(),i - 1, j))
                                    posY = Actions.pushY(-1, i, j, mapO, rules.getTabperm());
                            }
                    break;
                //si c'est 's', on compte aller vers le bas
                case 's':
                    for(int i = mapO.length - 1; i >= 0; i--)
                        for(int j = mapO[i].length - 1; j >= 0; j--)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {
                                //on regarde si on a le droit d'avancer
                                if (canMoveY(rules.getTabperm(),item,i+ 1,j))
                                    posY = Actions.down(mapO, i, j);
                                //on regarde si on a atteint un objet qui est win
                                else if(thingHasWin( i+ 1, j))
                                    winStatus = true;
                                //on regarde si on pousse quelque chose
                                else if(thingIsPushingY(rules.getTabperm(),i + 1, j))
                                    posY = Actions.pushY(1, i, j, mapO, rules.getTabperm());
                            }
                    break;
                //si c'est 'q', on compte aller vers la gauche
                case 'q':
                    for(int i = 0; i <= mapO.length - 1; i++)
                        for(int j = 0; j <= mapO[i].length - 1; j++)
                            if(this.getClass().isInstance(mapO[i][j]))
                            {
                                if (canMoveX(rules.getTabperm(),item,i,j - 1))
                                    posX = Actions.left(mapO, i, j);

                                else if(thingHasWin( i, j - 1))
                                    winStatus = true;

                                else if(thingIsPushingX(rules.getTabperm(),i, j - 1))
                                    posX = Actions.pushX(-1, i, j, mapO, rules.getTabperm());
                            }
                    break;
                //si c'est 'd', on compte aller vers la droite
                case 'd':
                    for(int i = mapO.length - 1; i >= 0; i--)
                        for(int j = mapO[i].length - 1; j >= 0; j--)
                            if (this.getClass().isInstance(mapO[i][j]))
                            {
                                if (canMoveX(rules.getTabperm(),item, i, j + 1))
                                    posX = Actions.right(mapO, i, j);

                                else if(thingHasWin( i, j + 1))
                                    winStatus = true;

                                else if(thingIsPushingX(rules.getTabperm(),i, j + 1))
                                    posX = Actions.pushX(1, i, j, mapO, rules.getTabperm());
                            }
                    break;
            }
        }
        rules.actualise();
        //on va fusionner la map actuel avec la map temporaire
        actualiseObjectMap();
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
            if(i == coordonates_win.get(k)[0] && j == coordonates_win.get(k)[1])
                return true;
        return false;
    }

    /**
     * cette méthode va chercher dans le tableau d'objet toutes les coordonnés des éléments qui sont win
     * et va les transférer dans l'arraylist "coordonates_win"
     */
    private void searchWin()
    {
        //on cherche type de l'objet qui est win
        Entity win_object = BigAlgorithm.dico.get(whichItem(Rules.WIN));
        //on va cherher dans tout le tableau (les deux boucles)
        for(int i = 0; i <= mapO.length - 1; i++)
            for(int j = 0; j <= mapO[i].length -1; j++)
            {
                //si on a aucun objet win, on réinitialise l'arraylist
                if(win_object == null)
                    coordonates_win = new ArrayList<int[]>();
                //sinon si on vérifie si l'objet est du type qu'on cherche
                    // si oui, alors on l'ajoute dans l'arraylist
                else if(mapO[i][j] != null && mapO[i][j].getClass() == win_object.getClass())
                {
                    temp_object_map[i][j] = mapO[i][j];
                    int[] pos = {i,j};
                    coordonates_win.add(pos);
                }
            }
    }

    /**
     * cette méthode permet de retrouver quel objet de type Rules possède une règle mise en param
     * @param rUles la règle de l'objet qu'on veut retrouver
     * @return l'élément possédant la règle mise en param
     */

    private Enum whichItem(Rules rUles)
    {
        //on cherche dans le tableau des permissions
        for(int i = 0; i <= rules.getTabperm().length -1; i++)
            //si on a trouvé la règle que l'on cherchait
            //alors on retourne l'objet qui la possède
            if(rules.getTabperm()[i][1] == rUles && rules.getTabperm()[i][0] != null)
                return rules.getTabperm()[i][0];
        //sinon on return null
        return null;
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
            return true;
        }
        return false;
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
                        //si un objet qui se trouve sur un element de la map tempk, on break
                        //car on pourrait écrase l'objet de temp par l'objet de mapO
                        if(temp_object_map[i][j] != null && mapO[i][j] != temp_object_map[i][j])
                        {
                            return;
                        }
                        //si l'objet est bien noStatus, on ajoute l'élément à notre map temp
                        else if (mapO[i][j].noStatus() || mapO[i][j].thingIsSink())
                        {
                            flag = true;
                            res[i][j] = mapO[i][j];
                            mapO[i][j] = null;
                        }
                    }catch (NullPointerException e){}
                }
        if(flag)
        {
            temp_object_map = res;
        }
    }


    /**
     * cette méthode sert à fusionner la map visible (mapO) avec la map temporaire(temp_object_map)
     */
     private void actualiseObjectMap()
     {
        for(int i = 0; i<= mapO.length - 1; i++)
            for(int j = 0; j <= mapO[i].length - 1; j++)
                //si on a rien sur la map visible et qu'il y a un element sur temp
                //alors on le rajoute à la map visible
            {
                if(mapO[i][j] != null && temp_object_map[i][j] != null && mapO[i][j].isItem() && temp_object_map[i][j].thingIsSink() && mapO[i][j] != temp_object_map[i][j])
                {
                    temp_object_map[i][j] = null;
                    mapO[i][j] = temp_object_map[i][j];
                }
                else if(mapO[i][j] == null && temp_object_map[i][j] != null)
                {
                   // System.out.println("je passe");
                    mapO[i][j] = temp_object_map[i][j];
                }
            }
     }
    /**
     * methode qui sera implémenté dans les classes enfants
     * @return false par défaut, but: retourner nostatus()
     */
     @Override
     public boolean noStatus(){return false;}

     protected boolean nostatus(Enum[][] tabperm, Rules object)
     {
         //si dans tous les éléments de tabperm, on y trouve que notre objet(implicitement il a une règle)
         //alors on return false
        for(int i = 0; i <= tabperm.length - 1; i++)
            {
                if (tabperm[i][0] == object)
                    return false;
            }
        //vrai sinon
        return true;
     }

    /**
     * methode qui sera implémenté dans les classes enfants
     * @return false par défaut, but: retourner thingissink()
     */
    @Override
    public boolean thingIsSink() {
        return false;
    }

    protected boolean thingisskink(Enum[][] tabperm, Rules object)
    {
        for(int i = 0; i<= tabperm.length - 1; i ++)
            if(tabperm[i][0] == object && tabperm[i][1] == Rules.SINK)
                return true;
        return false;
    }

}

