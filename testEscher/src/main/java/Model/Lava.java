package Model;

/**
 * voir doc sur la classe Baba
 */
public class Lava extends Item
{
    private final Rules object = Rules.LAVA;

    @Override
    public Rules getObject(){return object;}

    @Override
    public final String getSkin(){return "*";}

}
