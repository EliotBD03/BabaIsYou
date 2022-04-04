package logic;

public class Flag extends Item
{
    private int posX = -1;
    private int posy = -1;

    private String skin = "@";

    public String getSkin(){return skin;}

    public Flag(boolean flag)
    {

    }

    public Flag()
    {
        int[] position = super.searchtype(Flag.class);
        this.posY = position[0];
        this.posX = position[1];
    }

    @Override
    public void move(String input)
    {
        super.move(input, Rules.FLAG);
    }

}
