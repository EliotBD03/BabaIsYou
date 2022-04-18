package Model;

public class Main
{
    public static void main(String[] args)
    {
        Extract extract = new Extract("/home/julien/Bureau/BabaIsYou/new/src/main/resources/level/default/level1.txt");
        Environment map = new Environment();
        map.setMap(extract.getDataList());

        Baba baba = new Baba();
        Flag flag = new Flag();
        Rock rock = new Rock();
        Wall wall = new Wall();
        Goop goop = new Goop();

        System.out.println(map);
        while(true)
        {
            baba.move(Input.get_Input());
            map.actualiseMap();
            System.out.println(map);
            if(Item.win())
                break;
        }
        extract = new Extract("/home/julien/Bureau/BabaIsYou/new/src/main/resources/level/default/level2.txt");
        map.setMap(extract.getDataList());
        while(true)
        {
            baba.move(Input.get_Input());
            map.actualiseMap();
            System.out.println(map);
            if(Item.win())
                break;
        }
        extract = new Extract("/home/julien/Bureau/BabaIsYou/new/src/main/resources/level/default/level3.txt");
        map.setMap(extract.getDataList());
        while(true)
        {
            baba.move(Input.get_Input());
            map.actualiseMap();
            System.out.println(map);
            if(Item.win())
                break;
        }
        extract = new Extract("/home/julien/Bureau/BabaIsYou/new/src/main/resources/level/default/level4.txt");
        map.setMap(extract.getDataList());
        while(true)
        {
            baba.move(Input.get_Input());
            map.actualiseMap();
            System.out.println(map);
            if(Item.win())
                break;
        }
    }
}