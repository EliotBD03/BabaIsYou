public class Player
{
    public String skin_player = "O";
    public int position = 55;

    public int mouvement(String letter)
    {
        Input input = new Input();
        String response = input.get_Input();
        Wall wall = new  Wall();
        if (response.equals("z")  && !(wall.iswall(this.position - 10)))
            return this.position - 10;
        if (response.equals("s") && !(wall.iswall(this.position + 10)))
            return this.position + 10;
        if (response.equals("q") && !(wall.iswall(this.position - 1)))
            return this.position - 1;
        if (response.equals("d") && !(wall.iswall(this.position + 1)))
            return this.position + 1;
        else
            System.out.println("you re stuck to a wall or invalid input");
            return position;
    }
}
