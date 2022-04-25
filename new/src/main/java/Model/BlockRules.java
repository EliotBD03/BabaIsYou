package Model;

/**
 * cette classe abstraite contient tous les blocs de règles du jeu, ils ont tous les memes
 * caracteristiques. Cela nous permet donc de creer cette classe pour plus de lisibilité
 * au niveau du code
 */
public class BlockRules extends Environment implements Entity
{

    //la position en x d'un objet qui est en relation is-a avec Blockrules
    protected int posX;
    //la position en y d'un objet qui est en relation is-a avec Blockrules
    protected int posY;

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

        public TextFlag()
        {
            int[] position = super.searchtype(TextFlag.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }

    public static class TextRock extends BlockRules
    {
        private static final String skin = "R";
        @Override
        public String getSkin(){return skin;}

        public TextRock()
        {
            int[] position = super.searchtype(TextRock.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }

    public static class TextWall extends BlockRules
    {
        private static final String skin = "W";
        @Override
        public String getSkin(){return skin;}

        public TextWall()
        {
            int[] position = super.searchtype(TextWall.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }

    public static class TextBaba extends BlockRules
    {
        private static final String skin = "B";
        @Override
        public String getSkin(){return skin;}

        public TextBaba()
        {
            int[] position = super.searchtype(TextBaba.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }
    public static class TextGoop extends BlockRules
    {
        private static final String skin = "M";
        @Override
        public String getSkin(){return skin;}

        public TextGoop()
        {
            int[] position = super.searchtype(TextGoop.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }

    public static class Is extends  BlockRules
    {
        private static final String skin = "I";
        @Override
        public String getSkin(){return skin;}

        public Is()
        {
            int[] position = super.searchtype(Is.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }

    public static class Win extends BlockRules
    {
        //G pour Goal (pour ne pas avoir deux fois "W" ou "w")
        private static final String skin = "G";
        @Override
        public String getSkin(){return skin;}

        public Win()
        {
            int[] position = super.searchtype(Win.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }

    public static class You extends BlockRules
    {
        private static final String skin = "Y";
        @Override
        public String getSkin(){return skin;}

        public You()
        {
            int[] position = super.searchtype(You.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }

    public static class Stop extends BlockRules
    {
        private static final String skin = "S";
        @Override
        public String getSkin(){return skin;}

        public Stop()
        {
            int[] position = super.searchtype(Stop.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }

    public static class Push extends BlockRules
    {
        private static final String skin = "P";
        @Override
        public String getSkin(){return skin;}

        public Push()
        {
            int[] position = super.searchtype(Push.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }

    public static class Sink extends BlockRules
    {
        private static final String skin = "D";
        @Override
        public String getSkin(){return skin;}

        public Sink()
        {
            int[] position = super.searchtype(Sink.class);
            super.posY = position[0];
            super.posX = position[1];
        }

    }

    public static class TextLava extends BlockRules
    {
        private static final String skin = "L";

        @Override
        public String getSkin(){return skin;}

        public TextLava()
        {
            int[] position = super.searchtype(TextLava.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }

    public static class Kill extends BlockRules
    {
        private static final String skin = "K";

        @Override
        public String getSkin(){return skin;}

        public Kill()
        {
            int[] position = super.searchtype(TextLava.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }
}
