package Model;

public class Main
{
    public static void main(String[] args)
    {
        Extract extract = new Extract("/home/julien/Bureau/BabaIsYou/new/src/main/java/Model/test.txt");
        Environment map = new Environment();
        map.setMap(extract.getDataList());

        Baba baba = new Baba();
        Flag flag = new Flag();
        Rock rock = new Rock();
        Wall wall = new Wall();
        Goop goop = new Goop();

        System.out.println(map);
        int i = 50;
        while(i > 0)
        {
            baba.move("z");
            baba.move("s");
            map.actualiseMap();
            i--;
        }
            System.out.println(map);
    }
}