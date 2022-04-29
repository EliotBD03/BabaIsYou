package Model;

/**
 * voir doc sur la classe Baba
 */
public class Flag extends Item
{
    private final Rules object = Rules.FLAG;

    private String skin = "@";

    @Override
    public Rules getObject(){return object;}

    @Override
    public String getSkin(){return skin;}

    public Flag()
    {
        int[] position = super.searchtype(Flag.class);
        super.posY = position[0];
        super.posX = position[1];
    }

}
