package Model;

/**
 * Cette interface est la base de tous les objets du jeu. Tous les objets qui y sont ont des caractéristiques qui sont
 * données par ces méthodes.
 */
interface Entity
{
    String getSkin();

    boolean canBePushed(Enum[][] tabperm);
    boolean canMoveX(Enum[][] tabperm, Rules object,int posy, int posx);
    boolean canMoveY(Enum[][] tabperm,Rules object,int posy, int posx);
    boolean thingIsYou();
    boolean thingIsPushingX(Enum[][] tabperm, int posy, int posx);
    boolean thingIsPushingY(Enum[][] tabperm, int posy, int posx);
    boolean thingIsStop();
    boolean noStatus();
    }
