public class Stop implements Entity
{
    private String skin = "S";


    public String getSkin(){return skin;}

    public boolean canBePushed()
    {
        return true;
    }
}
