package logic;

public class You implements Entity
{
    private String skin = "Y";


    public String getSkin(){return skin;}

    public boolean canBePushed()
    {
        return true;
    }
}
