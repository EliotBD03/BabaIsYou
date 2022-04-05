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
        posY = position[0];
        posX = position[1];
        super.pushstatus =  canBePushed(BigAlgorithm.getTabperm());
    }


    public boolean canBePushed(Enum[][] tabperm)
    {
        for(int i = 0; i <= tabperm.length - 1; i++)
        {
            if(tabperm[i][0] ==  Rules.BABA && tabperm[i][1] == Rules.PUSH)
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public void move(String input)
    {
        System.out.println("avant posx = " + posX + " posy = " + posY);
        super.move(input, Rules.BABA);
        actualiseInstance(Baba.class, posX, posY);
        System.out.println("apres posx = " + posX + " posy = " + posY);
    }
}
