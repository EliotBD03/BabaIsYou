package Model;

public class Glue extends Item
{
    private final Rules object = Rules.GLUE;

    @Override
    public Rules getObject(){return object;}

    @Override
    public final String getSkin(){return "$";}
}
