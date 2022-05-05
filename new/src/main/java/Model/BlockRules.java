package Model;

/**
 * cette classe abstraite contient tous les blocs de règles du jeu, ils ont tous les memes
 * caracteristiques. Cela nous permet donc de creer cette classe pour plus de lisibilité
 * au niveau du code
 */
public class BlockRules extends Environment implements Entity
{

    @Override
    public boolean isItem() {return false;}

    @Override
    public String getSkin() {
        return null;
    }

    //Dans le jeu, les règles ne sont pas contrôlables. Cela permet d'implémenter toutes ces méthodes facilement à false
    @Override
    public boolean thingIsPush(Enum[][] tabperm){return true;}
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

    //ces classes internes représentent toutes les règles, elles ont toutes le même moyen d'implémentation sauf pour skin(ce qui permet
    //de les différencier dans un tableau de String
    public static class TextFlag extends BlockRules
    {
        private static final String skin = "F";

        @Override
        public String getSkin(){return skin;}

    }

    public static class TextRock extends BlockRules
    {
        private static final String skin = "R";

        @Override
        public String getSkin(){return skin;}

    }

    public static class TextWall extends BlockRules
    {
        private static final String skin = "W";

        @Override
        public String getSkin(){return skin;}

    }

    public static class TextBaba extends BlockRules
    {
        private static final String skin = "B";

        @Override
        public String getSkin(){return skin;}

    }
    public static class TextGoop extends BlockRules
    {
        private static final String skin = "M";

        @Override
        public String getSkin(){return skin;}

    }

    public static class Is extends  BlockRules
    {
        private static final String skin = "I";

        @Override
        public String getSkin(){return skin;}

    }

    public static class Win extends BlockRules
    {
        //G pour Goal (pour ne pas avoir deux fois "W" ou "w")
        private static final String skin = "G";

        @Override
        public String getSkin(){return skin;}

    }

    public static class You extends BlockRules
    {
        private static final String skin = "Y";

        @Override
        public String getSkin(){return skin;}

    }

    public static class Stop extends BlockRules
    {
        private static final String skin = "S";

        @Override
        public String getSkin(){return skin;}

    }

    public static class Push extends BlockRules
    {
        private static final String skin = "P";

        @Override
        public String getSkin(){return skin;}

    }

    public static class Sink extends BlockRules
    {
        private static final String skin = "D";

        @Override
        public String getSkin(){return skin;}

    }

    public static class TextLava extends BlockRules
    {
        private static final String skin = "L";

        @Override
        public String getSkin(){return skin;}

    }

    public static class Kill extends BlockRules
    {
        private static final String skin = "K";

        @Override
        public String getSkin(){return skin;}

    }
}
