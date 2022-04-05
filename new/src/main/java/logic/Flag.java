package logic;

public class Flag extends Item
{

    private int posX;
    private int posY;

    private String skin = "@";

    public String getSkin(){return skin;}

    public Flag(boolean flag)
    {

    }

    public Flag()
    {
        int[] position = super.searchtype(Flag.class);
        posY = position[0];
        posX = position[1];
        super.pushstatus = canBePushed(BigAlgorithm.getTabperm());
    }


    public boolean canBePushed(Enum[][] tabperm)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] ==  Rules.FLAG && tabperm[i][1] == Rules.PUSH)
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public void move(String input)
    {
        super.move(input, Rules.FLAG);
        actualiseInstance(Flag.class, posX, posY);
    }
}
