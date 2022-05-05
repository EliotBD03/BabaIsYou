package Model;

/**
 * voir doc sur la classe Baba
 */
public class Lava extends Item
{
    private final Rules object = Rules.LAVA;

    private final String skin = "*";

    @Override
    public Rules getObject(){return object;}

    @Override
    public String getSkin(){return skin;}

}
