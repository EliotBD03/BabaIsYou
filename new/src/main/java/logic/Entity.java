package logic;

interface Entity
{
    static String getSkin(){return " ";};

    static boolean canBePushed(Enum[][] tabperm){return true;}
    static boolean canMoveX(int posx){return true;}
    static boolean canMoveY(int posy){return true;}
    static boolean thingIsYou(Enum[][] tabperm, Entity thing){return true;}
    static boolean thingIsPushingX(int x){return true;}
    static boolean thingIsPushingY(int y){return true;}
    }
