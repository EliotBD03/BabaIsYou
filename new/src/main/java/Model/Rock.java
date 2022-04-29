package Model;

/**
 * voir doc sur la classe Baba
 */

public class Rock extends Item
{
    private final Rules object = Rules.ROCK;

    private String skin = "#";

    @Override
    public Rules getObject(){return object;}

    @Override
    public String getSkin(){return skin;}

    public Rock()
    {
        int[] position = super.searchtype(Rock.class);
        super.posY = position[0];
        super.posX = position[1];
    }
}
