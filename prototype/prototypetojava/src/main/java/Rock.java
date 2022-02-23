public class Rock extends  Wall
{
    private final String rockskin = "#";
    private int position = 33;


    public int getRockPosition(){return this.position;}
    public String getRockskin()
    {
        return this.rockskin;
    }

    public boolean isrock(int position)
    {
        if (this.position == position)
            return true;
        return false;
    }

    public void pushed(int move)
    {
        if (!(super.iswall(this.position + move)))
           this.position += move;
    }
}
