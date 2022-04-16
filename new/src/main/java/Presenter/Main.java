package Presenter;
import Model.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Main
{
    private static ArrayList<int[]> changeCoord = new ArrayList<>();
    
    private static Environment map;
    private static Baba baba;
    private static Flag flag;
    private static Rock rock;
    private static Wall wall;

    private static final Map<Character, String> dico = new HashMap<Character, String>();

    public static int getLength(){return map.getMap().length;}

    public void makeTheGame(String fileName) throws URISyntaxException {
        dico.put(' ',getClass().getResource("/sprite/fonf.png").toString()); dico.put('X',getClass().getResource( "/sprite/border.png").toURI().toString());
        dico.put('O', getClass().getResource( "/sprite/baba.gif").toURI().toString()); dico.put('w', getClass().getResource( "/sprite/wall.png").toURI().toString());
        dico.put('@', getClass().getResource( "/sprite/Flag.png").toURI().toString()); dico.put('#', getClass().getResource( "/sprite/rock.png").toURI().toString());
        dico.put('B', getClass().getResource( "/sprite/textbaba.png").toURI().toString()); dico.put('I', getClass().getResource( "/sprite/textis.png").toURI().toString());
        dico.put('Y', getClass().getResource( "/sprite/you.png").toURI().toString()); dico.put('W', getClass().getResource( "/sprite/walltext.png").toURI().toString());
        dico.put('S', getClass().getResource( "/sprite/stop.png").toURI().toString()); dico.put('R', getClass().getResource( "/sprite/textrock.png").toURI().toString());
        dico.put('P', getClass().getResource( "/sprite/push.png").toURI().toString()); dico.put('F', getClass().getResource( "/sprite/textflag.png").toURI().toString());
        dico.put('G', getClass().getResource( "/sprite/win.png").toURI().toString());


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
    
    private static void makeChange()
    {
        temp = map.getMap();
        System.out.println(map.toString(temp));
        map.actualiseMap();
        String[][] actual = map.getMap();
        System.out.println(map.toString(temp));
        for(int i = 0; i <= actual.length -1; i++)
            for(int j = 0; j < actual[j].length -1; j++)
                if(!(actual[i][j].equals(temp[i][j])))
                {
                    int[] pos = {i,j};
                    System.out.println(i + " " +j);
                    changeCoord.add(pos);
                }
    }

    public static ArrayList<int[]> getChanges(){return changeCoord;}
}
