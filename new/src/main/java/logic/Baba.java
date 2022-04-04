package logic;

public class Baba extends Item
{
    //position de baba
    private String skin = "O";
    //accesseur
    public String getSkin(){return skin;}

    /**
     * constructeur servant à la mise en place d'instance dans "mapO" dans la classe "Map"
     * @param flag permet de savoir si on est instancié via "mapO
     */
    public Baba(boolean flag)
    {

    }

    public Baba()
    {
        int[] position = super.searchtype(Baba.class);
        super.posY = position[0];
        super.posX = position[1];
        System.out.println("posx = " + posX + " posy = " + posY);
    }

    @Override
    public void move(String input)
    {
        super.move(input, Rules.BABA);
    }
}
