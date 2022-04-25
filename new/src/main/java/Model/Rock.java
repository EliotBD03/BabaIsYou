package Model;

/**
 * voir doc sur la classe Baba
 */

public class Rock extends Item
{

    private String skin = "#";

    public String getSkin(){return skin;}

    public Rock()
    {
        super.object = Rules.ROCK;
        int[] position = super.searchtype(Rock.class);
        super.posY = position[0];
        super.posX = position[1];
    }
}
