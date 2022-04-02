package logic;

interface Entity
{
    String getSkin();

    boolean canBePushed();
    boolean canMoveX(int posx);
    boolean canMoveY(int posy);
    boolean thingIsYou(Enum[][] tabperm, Entity thing);
    boolean thingIsPushing(byte x);
    boolean babaIsPushingY(byte y);
    void PushX(byte x);
    void PushY(byte y);
    }
