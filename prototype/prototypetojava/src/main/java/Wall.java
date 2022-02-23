public class Wall extends Map
{
    private String wall = "X";

    public boolean iswall(int position)
    {
        if(super.map[position].equals(this.wall))
            return true;
        return false;
    }
}
