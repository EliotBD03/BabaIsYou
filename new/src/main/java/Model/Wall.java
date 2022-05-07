package Model;

/**
 * voir doc sur la classe Baba
 */

public class Wall extends Item
{
    private final Rules object = Rules.WALL;

    @Override
    public Rules getObject(){return object;}

    @Override
    public final String getSkin(){return "w";}

}
