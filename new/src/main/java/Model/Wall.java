package Model;

/**
 * voir doc sur la classe Baba
 */

public class Wall extends Item
{
    private final Rules object = Rules.WALL;

    private String skin = "w";

    @Override
    public Rules getObject(){return object;}

    @Override
    public String getSkin(){return skin;}

    public Wall()
    {
        int[] position = super.searchtype(Wall.class);
        super.posY = position[0];
        super.posX = position[1];
    }
}
