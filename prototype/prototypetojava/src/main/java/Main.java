public class Main
{
    public static void main(String[] args)
    {
        Map envi = new Map();
        String[] map = envi.map;

        Goal goal = new Goal();
        map[goal.position] = goal.skin;

        Rock rock = new Rock();
        int rockposition = rock.getPosition();
        String rockskin = rock.getskin();

        Player player = new Player();
        Input input = new Input();

        boolean flag = true;
        while(flag)
        {
            map[rockposition] = rockskin;
            map[player.position] = player.skin;
            System.out.println(envi.getMap());
            String letter = input.get_Input();
            map[player.position] = " ";
            if (letter.equals("e"))
                break;
            else
            {
                int move = player.mouvement(letter);
                if (move == -1)
                    flag = false;
                else
                    player.position = move;
            }
        }
    }
}
