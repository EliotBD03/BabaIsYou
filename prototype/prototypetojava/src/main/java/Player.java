/**
 * c'est ici qu'on va avoir toutes les connections entre les classes:
 * Player, Rock, Wall, Map.
 *
 * On a un heritage lineaire : Map => Wall => Rock => Player
 */


public class Player extends Rock
{
    private String playerskin = "O";
    private int playerposition;

    /**
     * accesseur:
     * @return l'apparence du joueur.
     */

    public String getPlayerskin(){return this.playerskin;}

    /**
     * accesseur:
     * @return la position du joueur.
     */

    public int getPlayerPosition(){return this.playerposition;}

    /**
     * constructeur:
     * @param playerposition la position du joueur.
     */

    public Player(int playerposition)
    {
        this.playerposition = playerposition;
    }

    /**
     * Cette methode permet en fonction de la position de deplacer le joueur
     * tout en tenant compte des obstacles : Rock et Wall.
     * @param letter l'entree du joueur
     */
    public void mouvement(String letter)
    {
        if (letter.equals("z")  && !(super.iswall(this.playerposition - 10)))
        {
            if (super.isrock(this.playerposition - 10))
            {
                super.pushed(- 10);
                if (super.getRockPosition() != this.playerposition - 10)
                    this.playerposition -= 10;
            }
            else
                this.playerposition -= 10;

        }
        else if (letter.equals("s") && !(super.iswall(this.playerposition + 10)))
        {
            if (super.isrock(this.playerposition + 10))
            {
                super.pushed(+ 10);
                if(super.getRockPosition() != this.playerposition + 10)
                    this.playerposition += 10;
            }
            else
                this.playerposition += 10;

        }
        else if (letter.equals("q") && !(super.iswall(this.playerposition - 1)))
        {
            if (super.isrock(this.playerposition - 1))
            {
                super.pushed(- 1);
                if(super.getRockPosition() != this.playerposition - 1)
                    this.playerposition -=1;
            }
            else
                this.playerposition -= 1;

        }
        else if (letter.equals("d") && !(super.iswall(this.playerposition + 1)))
        {
            if (super.isrock(this.playerposition + 1))
            {
                super.pushed(+ 1);
                if (super.getRockPosition() != this.playerposition + 1)
                    this.playerposition += 1;
            }
            else
                this.playerposition += 1;
        }
        else
            {
                System.out.println("you re stuck to a wall or invalid input");
            }
    }
}
