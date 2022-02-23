public class Main
{
    public static void main(String[] args)
    {

        Goal goal = new Goal();
        Player player = new Player();

        player.map[goal.position] = goal.skin;

        Input input = new Input();


        boolean flag = true;
        while(flag) {
            player.map[player.getPlayerPosition()] = player.getPlayerskin();
            player.map[player.getRockPosition()] = player.getRockskin();

            System.out.println(player.getMap());

            player.map[player.getPlayerPosition()] = " ";

            String letter = input.get_Input();

            if (letter.equals("e"))
                break;
            else {
                player.mouvement(letter);
                if (goal.isgoal(player.getPlayerPosition())) {
                    flag = false;
                    System.out.println("SUCCES !");
                }

            }

        }
    }
}
