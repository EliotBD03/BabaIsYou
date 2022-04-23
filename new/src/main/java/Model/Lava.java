package Model;

public class Lava extends Item
{
    private String skin = "*";

    public String getSkin(){return skin;}

    public Lava(boolean flag)
    {

    }

    public Lava()
    {
        int[] position = super.searchtype(Lava.class);
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
            if(tabperm[i][0] ==  Rules.LAVA && tabperm[i][1] == Rules.PUSH)
            {
                return true;
            }
        }
        return false;
    }
    public boolean thingIsStop(){return super.thingisstop(BigAlgorithm.getTabperm(), Rules.LAVA);}

    public boolean noStatus()
    {
        return super.nostatus(BigAlgorithm.getTabperm(), Rules.LAVA);
    }

    public boolean thingIsYou(){return super.thingisyou(BigAlgorithm.getTabperm(), Rules.LAVA);}

    public boolean thingIsAnotherThing(Enum[][] tabperm){return thingisanortherthing(tabperm,Rules.LAVA);}

    protected Enum getThing(Enum[][] tabperm){return getthing(tabperm, Rules.LAVA);}

    public boolean thingIsSink(){return thingissink(BigAlgorithm.getTabperm(), Rules.LAVA);}

    public boolean thingIsKill(){return thingiskill(BigAlgorithm.getTabperm(), Rules.LAVA);}



    @Override
    public void move(String input)
    {
        super.move(input, Rules.LAVA);
    }
}
