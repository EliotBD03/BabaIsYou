package Model;

/**
 * voir doc sur la classe Baba
 */

public class Rock extends Item
{

    private String skin = "#";

    public String getSkin(){return skin;}

    public Rock(boolean flag){}

    public Rock()
    {
        int[] position = super.searchtype(Rock.class);
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
            if(tabperm[i][0] ==  Rules.ROCK && tabperm[i][1] == Rules.PUSH)
            {
                return true;
            }
        }
        return false;
    }
    public boolean thingIsStop(){return super.thingisstop(BigAlgorithm.getTabperm(), Rules.ROCK);}

    public boolean noStatus()
    {
        return super.nostatus(BigAlgorithm.getTabperm(), Rules.ROCK);
    }

    public boolean thingIsYou(){return super.thingisyou(BigAlgorithm.getTabperm(), Rules.ROCK);}

    public boolean thingIsAnotherThing(Enum[][] tabperm){return thingisanortherthing(tabperm,Rules.ROCK);}

    protected Enum getThing(Enum[][] tabperm){return getthing(tabperm, Rules.ROCK);}

    public boolean thingIsSink(){return thingisskink(BigAlgorithm.getTabperm(), Rules.ROCK);}

    @Override
    public void move(String input)
    {
        super.move(input, Rules.WALL);

    }
}
