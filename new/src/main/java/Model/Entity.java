package Model;

/**
 * Cette interface est la base de tous les objets du jeu. Tous les objets qui y sont ont des caractéristiques qui sont
 * données par ces méthodes.
 * A noter que la doc de ces méthodes est la même pour toutes les classes qui les overrident
 */
interface Entity
{
    boolean isItem();
    /**
     * accesseur de l'apparence du joueur
     * @return l'apparence de l'objet de type Entity sous forme d'un String
     */
    String getSkin();

    /**
     * methode permettant de savoir si l'objet peut etre poussé
     * @param tabperm le tableau contenant les permissions issu de la classe BigAlgorithm
     * @return vrai si l'objet peut etre poussé, faux sinon
     */
    boolean canBePushed(Enum[][] tabperm);

    /**
     * methode permettant de savoir si l'objet peut bouger dans une certaine direction
     * on préconise que le deplacement se fait en x
     * @param tabperm le tableau contenant les permissions issu de la classe BigAlgorithm
     * @param object l'objet de type Rules correspondant au type de l'instance qui utilise la méthode
     * @param posy la position de l'objet en y (en l'occurence apres mouvement)
     * @param posx la position de l'objet en x (en l'occurence apres mouvement)
     * @return vrai si l'objet peut bouger, faux sinon
     */
    boolean canMoveX(Enum[][] tabperm, Rules object,int posy, int posx);

    /**
     * methode permettant de savoir si l'objet peut bouger dans une certaine direction
     * on préconise que le deplacement se fait en y
     * @param tabperm le tableau contenant les permissions issu de la classe BigAlgorithm
     * @param object l'objet de type Rules correspondant au type de l'instance qui utilise la méthode
     * @param posy la position de l'objet en y (en l'occurence apres mouvement)
     * @param posy la position de l'objet en y (en l'occurence apres mouvement)
     * @return vrai si l'objet peut bouger, faux sinon
     */
    boolean canMoveY(Enum[][] tabperm,Rules object,int posy, int posx);

    /**
     * methode permettant de savoir si l'objet qui est instancié peut-être contrôlé
     * @return vrai si l'objet peut être contrôlé ,faux sinon
     */
    boolean thingIsYou();

    /**
     * methode permettant de savoir si l'objet qui est instancié va "pousser" pendant son mouvement
     * on préconise que le déplacement se fait en x
     * @param tabperm le tableau contenant les permissions issu de la classe BigAlgorithm
     * @param posy la position de l'objet en y (en l'occurence apres mouvement)
     * @param posy la position de l'objet en y (en l'occurence apres mouvement)
     * @return vrai si l'objet pousse quelque chose , faux sinon
     */
    boolean thingIsPushingX(Enum[][] tabperm, int posy, int posx);

    /**
     * methode permettant de savoir si l'objet qui est instancié va "pousser" pendant son mouvement
     * on préconise que le déplacement se fait en y
     * @param tabperm le tableau contenant les permissions issu de la classe BigAlgorithm
     * @param posy la position de l'objet en y (en l'occurence apres mouvement)
     * @param posy la position de l'objet en y (en l'occurence apres mouvement)
     * @return vrai si l'objet pousse quelque chose, faux sinon
     */
    boolean thingIsPushingY(Enum[][] tabperm, int posy, int posx);

    /**
     * methode permettant de savoir si l'objet qui est instancié a la caractéristique "stop"
     * ie : l'objet ne peut bouger de lui-même, ne peut pas être poussé et ne peut pas être traversé
     * @return vrai si l'objet est "stop", faux sinon
     */
    boolean thingIsStop();

    /**
     * methode permettant de savoir si un objet est un autre objet
     * @return vrai si c'est le cas , faux sinon
     */
    boolean thingIsAnotherThing(Enum[][] tabperm);

    /**
     * methode permettant de savoir si l'objet qui est instancié n'a aucune caractéristique
     * ie : concidéré comme absent. Autrement dit, il ne bouge pas et peut être traversé
     * @return vrai si l'objet est "no status" , faux sinon
     */
    boolean noStatus();

    /**
     * methode permettant de savoir si l'objet qui est instancié a la caractéristique "sink"
     * ie : l'objet noie les objets de type item
     * @return vrai si c'est le cas, faux sinon
     */
    boolean thingIsSink();
    /**
     * methode permettant de savoir si un objet a la caractéristique "kill"
     * ie: si le personnage qu'on controle touche cet objet, alors il disparait
     * @return vrai si c'est le cas, faux sinon
     */
     boolean thingIsKill();

    }
