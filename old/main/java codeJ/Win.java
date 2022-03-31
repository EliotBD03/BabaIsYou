public class Win implements Entity
{
    private String skin = "W";


    public String getSkin(){return skin;}

    public boolean canBePushed()
    {
        return true;
    }
}
