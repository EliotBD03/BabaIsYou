package Model;

public class Goop extends Item
{
    private String skin = "?";

    public String getSkin(){return skin;}

    public Goop(boolean flag)
    {

    }

    public Goop()
    {
        int[] position = super.searchtype(Goop.class);
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
            if(tabperm[i][0] ==  Rules.GOOP && tabperm[i][1] == Rules.PUSH)
            {
                return true;
            }
        }
        return false;
    }

    public boolean thingIsStop(){return super.thingisstop(BigAlgorithm.getTabperm(), Rules.GOOP);}

    public boolean noStatus()
    {
        return super.nostatus(BigAlgorithm.getTabperm(), Rules.GOOP);
    }

    public boolean thingIsYou(){return super.thingisyou(BigAlgorithm.getTabperm(), Rules.GOOP);}

    public boolean thingIsAnotherThing(Enum[][] tabperm){return thingisanortherthing(tabperm, Rules.GOOP);}

    protected Enum getThing(Enum[][] tabperm){return getthing(tabperm, Rules.GOOP);}

    public boolean thingIsSink(){return thingissink(BigAlgorithm.getTabperm(), Rules.GOOP);}

    public boolean thingIsKill(){return thingiskill(BigAlgorithm.getTabperm(), Rules.GOOP);}

    @Override
    public void move(String input)
    {
        super.move(input, Rules.GOOP);
    }
}
