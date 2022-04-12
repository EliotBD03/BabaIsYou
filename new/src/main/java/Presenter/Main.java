package Presenter;
import Model.*;

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

    public static void makeTheGame(String fileName)
    {
        dico.put(' ', null); dico.put('X', "/sprite/wall.gif");
        dico.put('O', "/sprite/baba.gif"); dico.put('w', "/sprite/wall.gif");
        dico.put('@', "/sprite/Flag.png"); dico.put('#', "/sprite/rock.png");
        dico.put('B', "/sprite/textbaba.png"); dico.put('I', "/sprite/textis.png");
        dico.put('Y', "/sprite/you.png"); dico.put('W', "/sprite/baba.gif");
        dico.put('S', "/sprite/baba.gif"); dico.put('R', "/sprite/textrock.png");
        dico.put('P', "/sprite/baba.gif"); dico.put('F', "/sprite/baba.gif");
        dico.put('G', "/sprite/win.png");


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
