public class Main
{
    /**
    * C'est ici qu'on va retrouver "l'interface graphique".
     * on initialise toutes les classes necessaires au bon fonctionnement du programme
     * la boucle permet de reactualiser les positions des notre instance: player
     * ce bloc contient le cas ou le joueur atteint l'objectif
     * si l'entree utilisateur est egal à "e" => escape ,alors on ferme le programme
     */

    public static void main(String[] args)
    {

        Goal goal = new Goal();
        Player player = new Player(55);

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
                flag = false;
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
