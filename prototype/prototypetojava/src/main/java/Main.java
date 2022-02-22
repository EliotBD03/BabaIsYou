public class Main
{
    public static void main(String[] args)
    {
        Map envi = new Map();
        String[] map = envi.map;
        Player player = new Player();
        Input input = new Input();

        boolean flag = true;
        while(flag)
        {
            map[player.position] = player.skin_player;
            System.out.println(envi.getMap());
            String letter = input.get_Input();
            map[player.position] = " ";
            player.position = player.mouvement(letter);
        }
    }
}
