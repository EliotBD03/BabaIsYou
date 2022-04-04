package logic;

public class Wall extends Item
{
    private String skin = "w";

    public String getSkin(){return skin;}

    public Wall(boolean flag){}

    public Wall()
    {
        int[] position = super.searchtype(Wall.class);
        super.posY = position[0];
        super.posX = position[1];
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

    private void actualiseInstance()
    {
        actualiseInstance(Wall.class, posY, posX);
    }

    @Override
    public void move(String input)
    {
        super.move(input, Rules.WALL);
        actualiseInstance();
    }
}
