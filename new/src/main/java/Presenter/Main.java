package Presenter;
import Model.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Objects;

public class Main
{
    private static Environment map;
    private ArrayList<int[]> changeCoord;
    private static Baba baba;
    private static Flag flag;
    private static Rock rock;
    private static Wall wall;

    private static  String[][] temp;

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

    public void makeMove(String event)
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
    public ArrayList<int[]> getChanges()
    {
        ArrayList<int[]> changeCoord = new ArrayList<>();
        temp = Environment.getStringMap(map.getMap());
        map.actualiseMap();
        String[][] actual = map.getMap();
        for(int i = 0; i <= actual.length -1; i++)
            for(int j = 0; j < actual[j].length -1; j++)
                if(!(actual[i][j].equals(temp[i][j])))
                {
                    int[] pos = {i,j};
                    System.out.println("je passe");
                    changeCoord.add(pos);
                }
        return changeCoord;
    }
}
