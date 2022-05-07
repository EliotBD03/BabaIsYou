package Model;

/**
 * voir doc sur la classe Baba
 */
public class Flag extends Item
{
    private final Rules object = Rules.FLAG;

    @Override
    public Rules getObject(){return object;}

    @Override
    public final String getSkin(){return "@";}
}
