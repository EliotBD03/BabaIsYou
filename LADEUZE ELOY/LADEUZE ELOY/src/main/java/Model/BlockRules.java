package Model;

/**
 * cette classe abstraite contient tous les blocs de règles du jeu, ils ont tous les memes
 * caracteristiques. Cela nous permet donc de creer cette classe pour plus de lisibilité
 * au niveau du code
 */
public class BlockRules extends Environment implements Entity
{
    //vrai si l'objet est collé, faux sinon
    private boolean stickyStatus = false;
    @Override
    public String getSkin() {
        return null;
    }

    //Dans le jeu, les règles ne sont pas contrôlables. Cela permet d'implémenter toutes ces méthodes facilement à false
    @Override
    public void thingIsGlued()
    {
        stickyStatus = true;
    }
    @Override
    public boolean canMoveX(Enum[][] tabperm,int posy, int posx){return false;}
    @Override
    public boolean canMoveY(Enum[][] tabperm,int posy, int posx){return false;}
    @Override
    public boolean thingIsYou(Enum[][] tabperm){return false;}
    @Override
    public boolean thingIsPushingX(Enum[][] tabperm, int posy, int posx){return false;}
    @Override
    public boolean thingIsPushingY(Enum[][] tabperm, int posy, int posx){return false;}
    @Override
    public boolean thingIsStop(Enum[][] tabperm){return false;}
    @Override
    public boolean thingIsAnotherThing(Enum[][] tabperm) {
        return false;
    }
    @Override
    public boolean noStatus(Enum[][] tabperm){return false;}
    @Override
    public boolean thingIsSink(Enum[][] tabperm) {return false;}

    @Override
    public boolean thingIsKill(Enum[][] tabperm) {
        return false;
    }

    @Override
    public boolean thingIsWin(Enum[][] tabperm) {
        return false;
    }

    @Override
    public boolean thingIsSticky(Enum[][] tabperm)
    {
        return false;
    }

    @Override
    public boolean isItem() {return false;}
    //seul cette méthode déroge à la règle
    @Override
    public boolean thingIsPush(Enum[][] tabperm){
        return !stickyStatus;
    }

    //ces classes internes représentent toutes les règles, elles ont toutes le même moyen d'implémentation sauf pour skin(ce qui permet
    //de les différencier dans un tableau de String
    public static class TextFlag extends BlockRules
    {
        @Override
        public final String getSkin(){return "F";}

    }

    public static class TextRock extends BlockRules
    {
        @Override
        public final String getSkin(){return "R";}

    }

    public static class TextWall extends BlockRules
    {

        @Override
        public final String getSkin(){return "W";}

    }

    public static class TextBaba extends BlockRules
    {
        @Override
        public final String getSkin(){return "B";}

    }
    public static class TextGoop extends BlockRules
    {

        @Override
        public final String getSkin(){return "M";}

    }

    public static class Is extends  BlockRules
    {
        @Override
        public final String getSkin(){return "I";}

    }

    public static class Win extends BlockRules
    {
        @Override
        public final String getSkin(){return "G";}

    }

    public static class You extends BlockRules
    {

        @Override
        public final String getSkin(){return "Y";}

    }

    public static class Stop extends BlockRules
    {

        @Override
        public final String getSkin(){return "S";}

    }

    public static class Push extends BlockRules
    {

        @Override
        public final String getSkin(){return "P";}

    }

    public static class Sink extends BlockRules
    {

        @Override
        public final String getSkin(){return "D";}

    }

    public static class TextLava extends BlockRules
    {

        @Override
        public final String getSkin(){return "L";}

    }

    public static class Kill extends BlockRules
    {

        @Override
        public final String getSkin(){return "K";}

    }

    public static class TextGlue extends BlockRules
    {
        @Override
        public final String getSkin(){return "C";}
    }

    public static class Sticky extends BlockRules
    {
        @Override
        public final String getSkin(){return "T";}
    }
}
