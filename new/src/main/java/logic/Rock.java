package logic;

public class Rock extends Item
{

    private int posX;
    private int posY;

    private String skin = "#";

    public String getSkin(){return skin;}

    public Rock(boolean flag){}

    public Rock()
    {
        int[] position = super.searchtype(Rock.class);
        posY = position[0];
        posX = position[1];
        super.pushstatus =  canBePushed(BigAlgorithm.getTabperm());
    }

    public boolean canBePushed(Enum[][] tabperm)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] ==  Rules.ROCK && tabperm[i][1] == Rules.PUSH)
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public void move(String input)
    {
        super.move(input, Rules.WALL);
        actualiseInstance(Rock.class, posX, posY);
    }
}
