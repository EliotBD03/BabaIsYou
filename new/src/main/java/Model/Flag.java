package Model;

/**
 * voir doc sur la classe Baba
 */
public class Flag extends Item
{
    private String skin = "@";

    public String getSkin(){return skin;}

    public Flag()
    {
        super.object = Rules.FLAG;
        int[] position = super.searchtype(Flag.class);
        super.posY = position[0];
        super.posX = position[1];
    }

}
