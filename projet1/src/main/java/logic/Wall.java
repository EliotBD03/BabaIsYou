package logic;

public class Wall implements Entity
{
    private String skin = "w";


    public String getSkin(){return skin;}

    public boolean canBePushed()
    {
        return true;
    }
}
