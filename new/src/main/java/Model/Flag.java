package Model;

public class Flag extends Item
{
    private String skin = "@";

    public String getSkin(){return skin;}

    public Flag(boolean flag)
    {

    }

    public Flag()
    {
        int[] position = super.searchtype(Flag.class);
        super.posY = position[0];
        super.posX = position[1];
        super.pushstatus = canBePushed(BigAlgorithm.getTabperm());
        super.stopstatus = thingIsStop();
        super.youstatus = thingIsYou();
        super.nostatus = noStatus();
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
    public boolean thingIsStop(){return super.thingisstop(BigAlgorithm.getTabperm(), Rules.FLAG);}

    public boolean noStatus()
    {
        return super.nostatus(BigAlgorithm.getTabperm(), Rules.FLAG);
    }

    public boolean thingIsYou(){return super.thingisyou(BigAlgorithm.getTabperm(), Rules.FLAG);}



    @Override
    public void move(String input)
    {
        super.move(input, Rules.FLAG);
    }
}
