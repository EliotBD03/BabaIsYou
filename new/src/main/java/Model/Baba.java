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
     * constructeur servant à la mise en place d'instance dans "mapO" dans la classe "Map"
     * @param flag permet de savoir si on est instancié via "mapO"
     */
    public Baba(boolean flag)
    {

    }

    /**
     * ce constructeur s'applique dans la classe Main.Au moment de son initialisation, il va rechercher
     * dans mapO l'instance va prendre comme ses positions comme
     * caractéristiques. C'est une classe qui manipulable par l'utilsateur. Son pushstatus permet de savoir si via d'autres
     * de classes il sera "poussable".
     */
    public Baba()
    {
        super();
        int[] position = super.searchtype(Baba.class);
        super.posY = position[0];
        super.posX = position[1];
        super.pushStatus =  canBePushed(rules.getTabperm());
        super.stopStatus = thingIsStop();
        super.youStatus = thingIsYou();
        super.noStatus = noStatus();
    }

    /**
     * cette methode permet de savoir si l'instance de baba peut étre poussé
     * @param tabperm le tableau issu de BigAlgorithm contenan les permissions
     * @return vrai(poussable) ou faux(non poussable)
     */
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

    public boolean thingIsStop(){return super.thingisstop(rules.getTabperm(), Rules.BABA);}

    public boolean noStatus()
    {
        return super.nostatus(rules.getTabperm(), Rules.BABA);
    }

    public boolean thingIsYou(){return super.thingisyou(rules.getTabperm(), Rules.BABA);}

    public boolean thingIsAnotherThing(Enum[][] tabperm){return thingisanortherthing(tabperm, Rules.BABA);}

    protected Enum getThing(Enum[][] tabperm){return getthing(tabperm, Rules.BABA);}

    public boolean thingIsSink(){return thingisskink(rules.getTabperm(), Rules.BABA);}
    /**
     *cette methode est surchargé pour permettre la caractéristiques de type Rules par Rules.Baba isssu
     * de la classe Rules
     * @param input l'entrée conrespondante à l'utilisateur
     */
    @Override
    public void move(String input)
    {
        super.move(input, Rules.BABA);
    }
}
