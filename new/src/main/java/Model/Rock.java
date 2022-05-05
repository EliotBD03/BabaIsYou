package Model;

/**
 * voir doc sur la classe Baba
 */

public class Rock extends Item
{
    private final Rules object = Rules.ROCK;

    private final String skin = "#";

    @Override
    public Rules getObject(){return object;}

    @Override
    public String getSkin(){return skin;}
}
