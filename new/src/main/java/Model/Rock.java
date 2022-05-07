package Model;

/**
 * voir doc sur la classe Baba
 */

public class Rock extends Item
{
    private final Rules object = Rules.ROCK;

    @Override
    public Rules getObject(){return object;}

    @Override
    public final String getSkin(){return "#";}
}
