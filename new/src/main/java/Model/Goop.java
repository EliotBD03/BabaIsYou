package Model;

/**
 * voir doc sur la classe Baba
 */
public class Goop extends Item
{
    private final Rules object = Rules.GOOP;

    @Override
    public Rules getObject(){return object;}

    @Override
    public final String getSkin(){return "?";}
}
