package logic;

public class Wall extends Item
{
    private int posX = -1;
    private int posY = -1;
    private String skin = "w";

    public String getSkin(){return skin;}

    public Wall(boolean flag){}

    public Wall()
    {
        int[] position = super.searchtype(Wall.class);
        this.posY = position[0];
        this.posX = position[1];
    }

    @Override
    public void move(String input)
    {
        super.move(input, Rules.WALL);
    }
}
