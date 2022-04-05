package logic;

public class Wall extends Item
{

    private int posX;
    private int posY;

    private String skin = "w";

    public String getSkin(){return skin;}

    public Wall(boolean flag){}

    public Wall()
    {
        int[] position = super.searchtype(Wall.class);
        posY = position[0];
        posX = position[1];
        super.pushstatus =  canBePushed(BigAlgorithm.getTabperm());
    }

    public boolean canBePushed(Enum[][] tabperm)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] ==  Rules.WALL && tabperm[i][1] == Rules.PUSH)
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
        actualiseInstance(Wall.class, posX, posY);
    }
}
