package Model;

/**
 * voir doc sur la classe Baba
 */
public class Lava extends Item
{
    private final Rules object = Rules.LAVA;

    private String skin = "*";

    @Override
    public Rules getObject(){return object;}

    @Override
    public String getSkin(){return skin;}

    public Lava()
    {
        int[] position = super.searchtype(Lava.class);
        super.posY = position[0];
        super.posX = position[1];
    }
}
