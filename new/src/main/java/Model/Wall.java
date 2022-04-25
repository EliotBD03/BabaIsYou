package Model;

/**
 * voir doc sur la classe Baba
 */

public class Wall extends Item
{
    private String skin = "w";

    public String getSkin(){return skin;}

    public Wall(boolean flag){}

    public Wall()
    {
        super.object = Rules.WALL;
        int[] position = super.searchtype(Wall.class);
        super.posY = position[0];
        super.posX = position[1];
    }
}
