package logic;

public class Is implements Entity
{
    private String skin = "I";


    public String getSkin(){return skin;}

    public boolean canBePushed()
    {
        return true;
    }
}
