package Model;

/**
 * voir doc sur la classe Baba
 */
public class Goop extends Item
{
    private final Rules object = Rules.GOOP;

    private String skin = "?";

    @Override
    public Rules getObject(){return object;}

    @Override
    public String getSkin(){return skin;}

    public Goop()
    {
        int[] position = super.searchtype(Goop.class);
        super.posY = position[0];
        super.posX = position[1];
    }
}
