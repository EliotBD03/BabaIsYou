package Model;

public class Main
{
    public static void main(String[] args)
    {
        Extract extract = new Extract("level3.txt");
        Environment map = new Environment();
        map.setMap(extract.getDataList());
        BigAlgorithm rules = new BigAlgorithm();

        Baba baba = new Baba();
        Flag flag = new Flag();
        Rock rock = new Rock();
        Wall wall = new Wall();

        System.out.println(map);
        while(true)
        {
            String input = Input.get_Input();

            baba.move(input);

            flag.move(input);

            rock.move(input);

            wall.move(input);

            if(Item.win())
            {
                System.out.println("GREAT JOB! YOU WON");
                break;
            }
            rules.actualise();
            map.actualiseMap();
            System.out.println(map);
        }

    }
}
