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
        int[] position = super.searchtype(Wall.class);
        super.posY = position[0];
        super.posX = position[1];
        super.pushStatus =  canBePushed(BigAlgorithm.getTabperm());
        super.stopStatus = thingIsStop();
        super.youStatus = thingIsYou();
        super.noStatus = noStatus();
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
    public boolean thingIsStop(){return super.thingisstop(BigAlgorithm.getTabperm(), Rules.WALL);}

    public boolean noStatus()
    {
        return super.nostatus(BigAlgorithm.getTabperm(), Rules.WALL);
    }

    public boolean thingIsYou(){return super.thingisyou(BigAlgorithm.getTabperm(), Rules.WALL);}



    @Override
    public void move(String input)
    {
        super.move(input, Rules.WALL);
    }
}
