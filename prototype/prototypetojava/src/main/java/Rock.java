public class Rock
{
    private final String skin = "#";
    private int position = 33;


    public int getPosition()
    {
        return this.position;
    }

    public String getskin()
    {
        return this.skin;
    }

    public boolean isrock(int position)
    {
        String[] map = new Map().map;
        if (map[position].equals(this.skin))
            return true;
        return false;
    }

    public void pushed(int move)
    {
        Wall wall = new Wall();
        if (!(wall.iswall(this.position + move)))
           this.position += move;
    }
}
