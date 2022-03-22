public class Push implements Entity
{
    private String skin = "P";


    public String getSkin(){return skin;}

    public boolean canBePushed()
    {
        return true;
    }
}
