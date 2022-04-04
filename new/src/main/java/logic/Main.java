package logic;

public class Main
{
    public static void main(String[] args)
    {
        Extract extract = new Extract("test.txt");
        Map map = new Map();
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

            map.actualiseMap();
            rules.actualise();
            System.out.println(map);
        }

    }
}
