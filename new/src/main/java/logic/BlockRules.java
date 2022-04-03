package logic;

/**
 * cette classe abstraite contient tous les blocs de règles du jeu, ils ont tous les memes
 * caracteristiques. Cela nous permet donc de creer cette classe pour plus de lisibilité
 * au niveau du code et moins de fichier class
 */
public abstract class BlockRules implements Entity
{

    public boolean canBePushed(Enum[][] tabperm){return true;}
    public boolean canMoveX(int posx){return false;}
    public boolean canMoveY(int posy){return false;}
    public boolean thingIsYou(Enum[][] tabperm, Entity thing){return false;}
    public boolean thingIsPushingX(int x){return false;}
    public boolean thingIsPushingY(int y){return false;}

    public static class TextFlag extends BlockRules
    {
        private static final String skin = "F";

        public String getSkin(){return skin;}
    }

    public static class TextRock extends BlockRules
    {
        private static final String skin = "R";

        public String getSkin(){return skin;}
    }

    public static class TextWall extends BlockRules
    {
        private static final String skin = "W";

        public String getSkin(){return skin;}
    }

    public static class TextBaba extends BlockRules
    {
        private static final String skin = "B";

        public String getSkin(){return skin;}
    }

    public static class Is extends  BlockRules
    {
        private static final String skin = "I";

        public String getSkin(){return skin;}
    }

    public static class Win extends BlockRules
    {
        //G pour Goal (pour ne pas avoir deux fois "W" ou "w")
        private static final String skin = "G";

        public String getSkin(){return skin;}
    }

    public static class You extends BlockRules
    {
        private static final String skin = "Y";

        public String getSkin(){return skin;}
    }

    public static class Stop extends BlockRules
    {
        private static final String skin = "S";

        public String getSkin(){return skin;}
    }

    public static class Push extends BlockRules
    {
        private static final String skin = "P";

        public String getSkin(){return skin;}
    }
}
