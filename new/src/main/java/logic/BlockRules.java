package logic;

/**
 * cette classe abstraite contient tous les blocs de règles du jeu, ils ont tous les memes
 * caracteristiques. Cela nous permet donc de creer cette classe pour plus de lisibilité
 * au niveau du code et moins de fichier class
 */
public abstract class BlockRules extends Map implements Entity
{
    protected int posX;
    protected int posY;

    public int[] getPos()
    {
        int[] pos = {posY, posX};
        return pos;
    }


    public boolean canBePushed(Enum[][] tabperm){return true;}
    private boolean canMoveX(int posx){return false;}
    private boolean canMoveY(int posy){return false;}
    private boolean thingIsYou(Enum[][] tabperm, Entity thing){return false;}
    private boolean thingIsPushingX(int x){return false;}
    private boolean thingIsPushingY(int y){return false;}

    public static class TextFlag extends BlockRules
    {
        private static final String skin = "F";
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
        public String getSkin(){return skin;}

        public TextBaba()
        {
            int[] position = super.searchtype(TextBaba.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }

    public static class Is extends  BlockRules
    {
        private static final String skin = "I";
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
        public String getSkin(){return skin;}

        public Push()
        {
            int[] position = super.searchtype(Push.class);
            super.posY = position[0];
            super.posX = position[1];
        }
    }
}
