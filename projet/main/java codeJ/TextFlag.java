public class TextFlag implements Entity
{
    private String skin = "F";


    public String getSkin(){return skin;}

    public boolean canBePushed()
    {
        return true;
    }
}
