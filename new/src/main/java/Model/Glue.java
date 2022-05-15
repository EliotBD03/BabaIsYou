package Model;

/**
 * voir doc sur la classe Baba
 */
public class Glue extends Item
{
    private final Rules object = Rules.GLUE;

    @Override
    public Rules getObject(){return object;}

    @Override
    public final String getSkin(){return "$";}
}
