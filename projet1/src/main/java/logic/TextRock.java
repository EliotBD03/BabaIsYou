package logic;

public class TextRock implements Entity
{
    private String skin = "R";


    public String getSkin(){return skin;}

    public boolean canBePushed()
    {
        return true;
    }
}
