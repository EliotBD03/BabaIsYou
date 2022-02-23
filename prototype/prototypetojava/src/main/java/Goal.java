public class Goal extends Map
{
    public final String skin = "@";
    public final int position = 22;

    public boolean isgoal(int position)
    {
        if (position == this.position)
            return true;
        return false;
    }
}