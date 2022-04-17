package Model;

/**
 * voir doc sur la classe Baba
 */
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
        super.pushStatus = canBePushed(BigAlgorithm.getTabperm());
        super.stopStatus = thingIsStop();
        super.youStatus = thingIsYou();
        super.noStatus = noStatus();
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

    public boolean thingIsAnotherThing(Enum[][] tabperm){return thingisanortherthing(tabperm,Rules.FLAG);}

    protected Enum getThing(Enum[][] tabperm){return getthing(tabperm, Rules.FLAG);}

    public boolean thingIsSink(){return thingisskink(BigAlgorithm.getTabperm(), Rules.FLAG);}



    @Override
    public void move(String input)
    {
        super.move(input, Rules.FLAG);
    }
}
