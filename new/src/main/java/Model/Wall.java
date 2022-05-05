package Model;

/**
 * voir doc sur la classe Baba
 */

public class Wall extends Item
{
    private final Rules object = Rules.WALL;

    private final String skin = "w";

    @Override
    public Rules getObject(){return object;}

    @Override
    public String getSkin(){return skin;}

}
