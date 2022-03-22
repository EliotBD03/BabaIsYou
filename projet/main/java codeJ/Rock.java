public class Rock implements Entity
{
    private String skin = "#";


    public String getSkin(){return skin;}

    public boolean canBePushed()
    {
        return true;
    }
}
