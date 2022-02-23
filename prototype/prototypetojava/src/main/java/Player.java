public class Player extends Rock
{
    private String playerskin = "O";
    private int playerposition = 55;

    public String getPlayerskin(){return this.playerskin;}
    public int getPlayerPosition(){return this.playerposition;}


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
