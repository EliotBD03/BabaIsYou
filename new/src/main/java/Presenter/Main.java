package Presenter;
import Model.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Main
{
    private static Environment map;
    private static Baba baba;
    private static Flag flag;
    private static Rock rock;
    private static Wall wall;

    private static final Map<Character, String> dico = new HashMap<Character, String>();

    public static int getLength(){return map.getMap().length;}

    public void makeTheGame(String fileName) throws URISyntaxException {
        dico.put(' ',getClass().getResource("/sprite/fonf.png").toString()); dico.put('X',getClass().getResource( "/sprite/wall.png").toURI().toString());
        dico.put('O', getClass().getResource( "/sprite/wall.png").toURI().toString()); dico.put('w', getClass().getResource( "/sprite/wall.png").toURI().toString());
        dico.put('@', getClass().getResource( "/sprite/wall.png").toURI().toString()); dico.put('#', getClass().getResource( "/sprite/wall.png").toURI().toString());
        dico.put('B', getClass().getResource( "/sprite/wall.png").toURI().toString()); dico.put('I', getClass().getResource( "/sprite/wall.png").toURI().toString());
        dico.put('Y', getClass().getResource( "/sprite/wall.png").toURI().toString()); dico.put('W', getClass().getResource( "/sprite/wall.png").toURI().toString());
        dico.put('S', getClass().getResource( "/sprite/wall.png").toURI().toString()); dico.put('R', getClass().getResource( "/sprite/wall.png").toURI().toString());
        dico.put('P', getClass().getResource( "/sprite/wall.png").toURI().toString()); dico.put('F', getClass().getResource( "/sprite/wall.png").toURI().toString());
        dico.put('G', getClass().getResource( "/sprite/wall.png").toURI().toString());


        Extract extract = new Extract(fileName);
        map = new Environment();
        map.setMap(extract.getDataList());
        BigAlgorithm rules = new BigAlgorithm();

        baba = new Baba();
        flag = new Flag();
        rock = new Rock();
        wall = new Wall();
    }

    public static void makeMove(String event)
    {
        baba.move(event);
        flag.move(event);
        rock.move(event);
        wall.move(event);
    }

    public static String getSprite(int i , int j)
    {
        return dico.get(map.getMap()[i][j].charAt(0));
    }
}