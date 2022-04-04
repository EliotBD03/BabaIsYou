package logic;

public class Rock extends Item
{
    private int posX = -1;
    private int posY = -1;
    private String skin = "#";

    public String getSkin(){return skin;}

    public Rock(boolean flag){}

    public Rock()
    {
        int[] position = super.searchtype(Rock.class);
        posY = position[0];
        posX = position[1];
    }

    @Override
    public void move(String input)
    {
        super.move(input, Rules.WALL);
    }
}
