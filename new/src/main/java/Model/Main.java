package Model;

public class Main
{
        public static void main(String[] args)
        {
            Extract extract = new Extract("/home/julien/Bureau/BabaIsYou/BabaIsYou/new/src/main/resources/level/default/level4.txt");
            Environment map = new Environment();
            map.setMap(extract.getDataList());

            Flag flag = new Flag();
            Rock rock = new Rock();
            Wall wall = new Wall();
            Goop goop = new Goop();
            Lava lava = new Lava();
            Baba baba = new Baba();

            System.out.println(map);
            while(true)
            {
                String input = Input.get_Input();

                flag.move(input);
                rock.move(input);
                wall.move(input);
                goop.move(input);
                lava.move(input);
                baba.move(input);

                if(Item.win())
                {
                    System.out.println("GREAT JOB! YOU WON");
                    break;
                }
                map.actualiseMap();
            }

        }
}