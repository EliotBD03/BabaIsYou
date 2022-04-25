package Model;

public class Goop extends Item
{
    private String skin = "?";

    public String getSkin(){return skin;}

    public Goop(boolean flag)
    {

    }

    public Goop()
    {
        super.object = Rules.GOOP;
        int[] position = super.searchtype(Goop.class);
        super.posY = position[0];
        super.posX = position[1];
    }
}
