package logic;

interface Entity
{
    String getSkin();

    boolean canBePushed(Enum[][] tabperm);
    int[] getPos();
    static boolean canMoveX(int posx){return false;}
    static boolean canMoveY(int posy){return false;}
    static boolean thingIsYou(Enum[][] tabperm, Entity thing){return false;}
    static boolean thingIsPushingX(int x){return false;}
    static boolean thingIsPushingY(int y){return false;}
    }
