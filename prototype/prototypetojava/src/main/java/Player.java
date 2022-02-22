public class Player
{
    public String skin = "O";
    public int position = 55;

    public int mouvement(String letter)
    {
        Wall wall = new  Wall();
        Goal goal = new Goal();

        if (letter.equals("z")  && !(wall.iswall(this.position - 10)))
        {
            if (goal.isgoal(this.position - 10))
            {
                System.out.println("SUCCES !");
                return -1;
            }
            return this.position - 10;
        }
        if (letter.equals("s") && !(wall.iswall(this.position + 10)))
        {
            if (goal.isgoal(this.position + 10))
            {
                System.out.println("SUCCES !");
                return -1;
            }
            return this.position + 10;
        }
        if (letter.equals("q") && !(wall.iswall(this.position - 1)))
        {
            if (goal.isgoal(this.position - 1))
            {
                System.out.println("SUCCES !");
                return -1;
            }
            return this.position - 1;
        }
        if (letter.equals("d") && !(wall.iswall(this.position + 1)))
        {
            if (goal.isgoal(this.position + 1))
            {
                System.out.println("SUCCES !");
                return -1;
            }
            return this.position + 1;
        }
        else
            {
            System.out.println("you re stuck to a wall or invalid input");
            return position;
            }
    }
}
