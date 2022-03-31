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
        System.out.println(map);

        while(true)
        {
            String input = Input.get_Input();
            baba.move(input);
            map.actualiseMap();
            rules.actualise();
            System.out.println(map);
        }

    }
}
