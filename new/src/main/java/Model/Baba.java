package Model;

/**
 * Cette classe issue de Item est un des objets "jouables" par le joueur.
 * A noter que la documentation de cette classe est la même pour toutes les
 * classes enfants issues de Item sauf pour l'attribut skin et object
 */

public class Baba extends Item
{
    /**
     * ce à quoi correspond Baba dans Rules
     */
    private final Rules object = Rules.BABA;

    /**
     * ce à quoi va ressembler Baba dans un tableau de String
     */
    private final String skin = "O";

    /**
     * accesseur à l'attribut object
     * @return object
     */
    @Override
    public Rules getObject(){return object;}

    /**
     * accesseur à l'attribut skin
     * @return skin
     */
    @Override
    public String getSkin(){return skin;}
}
