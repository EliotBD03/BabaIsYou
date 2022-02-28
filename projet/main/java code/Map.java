public class Map
{
    int mapHeight;
    int mapLength;
    public static void main(String[] args)
    {
        Map map = new Map();
        System.out.println(map.mapHeight);
        System.out.println(map.mapLength);
    }
    public Map()
    {
        this.mapHeight = 6;
        this.mapLength = 5;
        String[][] _map = new String[mapHeight][mapLength];
    }
    public Map(int mapHeight, int mapLength)
    {
        String[][] _map = new String[mapHeight][mapLength];
    }
    public void insertEntities(String[][] _map)
    {
        
    }
}