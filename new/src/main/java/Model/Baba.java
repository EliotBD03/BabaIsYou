package Model;

/**
 * cette classe issue de Item est un des objets "jouables" par le joueur
 * à note que la documentation de cette classe est la même pour toutes les
 * classes enfants issues de Item sauf pour l'attribut skin
 */

public class Baba extends Item
{
    /**
     * ce à quoi va ressembler Baba dans un tableau de String
     */
    private String skin = "O";

    /**
     * accesseur à l'attribut skin
     * @return skin
     */
    public String getSkin(){return skin;}

    /**
     * ce constructeur s'applique dans la classe Main.Au moment de son initialisation, il va rechercher
     * dans mapO l'instance va prendre comme ses positions comme
     * caractéristiques. C'est une classe qui manipulable par l'utilsateur. Son pushstatus permet de savoir si via d'autres
     * de classes il sera "poussable".
     */
    public Baba()
    {
        super.object = Rules.BABA;
        int[] position = super.searchtype(Baba.class);
        super.posY = position[0];
        super.posX = position[1];
    }
    @Override
    public void move(String input)
    {
        super.move(input);
    }
}
