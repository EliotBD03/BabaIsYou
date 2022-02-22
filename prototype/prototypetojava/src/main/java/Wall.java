public class Wall
{
    private String wall = "X";

    public boolean iswall(int position)
    {
        String[] map = new Map().map;
        if(map[position].equals(wall))
            return true;
        return false;
    }
}
