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
            String input = Input.get_Input();

            baba.move(input);

            flag.move(input);

            rock.move(input);

            wall.move(input);

            goop.move(input);
            if(Item.win())
            {
                System.out.println("GREAT JOB! YOU WON");
                break;
            }
            map.actualiseMap();
            System.out.println(map);
        }

        extract = new Extract("/home/julien/Bureau/BabaIsYou/new/src/main/resources/level/default/level2.txt");
        map = new Environment();
        map.setMap(extract.getDataList());

        baba = new Baba();
        flag = new Flag();
        rock = new Rock();
        wall = new  Wall();
        goop = new Goop();

        System.out.println(map);
        while(true)
        {
            String input = Input.get_Input();

            baba.move(input);

            flag.move(input);

            rock.move(input);

            wall.move(input);

            goop.move(input);
            if(Item.win())
            {
                System.out.println("GREAT JOB! YOU WON");
                break;
            }
            map.actualiseMap();
            System.out.println(map);
        }
    }
}