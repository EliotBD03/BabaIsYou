package Model;

public class Lava extends Item
{
    private String skin = "*";

    public String getSkin(){return skin;}

    public Lava(boolean flag)
    {

    }

    public Lava()
    {
        super.object = Rules.LAVA;
        int[] position = super.searchtype(Lava.class);
        super.posY = position[0];
        super.posX = position[1];
    }
}
