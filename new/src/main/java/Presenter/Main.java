package Presenter;
import Model.*;
import View.Controller;
import javafx.scene.image.Image;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Objects;

public class Main
{
    private static User player;
    private static Environment map;
    private ArrayList<int[]> changeCoord;
    private static Baba baba;
    private static Flag flag;
    private static Rock rock;
    private static Wall wall;

    private static Lava lava;

    private static  String[][] temp;

    private static final Map<Character, Image> dico = new HashMap<Character, Image>();

    private static int rollback = 5;
    private static Main[] tabmap = new Main[rollback];

    private static int count = 5;

    public static int getLength(){return map.getMap().length;}

    public void makeTheGame(String fileName) throws URISyntaxException {
        Controller controller = new Controller();

        dico.put(' ',controller.fonfImage); dico.put('X',controller.borderImage);
        dico.put('O', controller.babaImage); dico.put('w', controller.wallImage);
        dico.put('@', controller.flagImage); dico.put('#', controller.rockImage);
        dico.put('B', controller.textBabaImage); dico.put('I', controller.isImage);
        dico.put('Y', controller.youImage); dico.put('W', controller.textWallImage);
        dico.put('S', controller.stopImage); dico.put('R', controller.textRockImage);
        dico.put('P', controller.pushImage); dico.put('F', controller.textFlagImage);
        dico.put('G', controller.winImage); dico.put('?', controller.goopImage);
        dico.put('M', controller.textGoopImage); dico.put('D', controller.textSinkImage);
        dico.put('L', controller.textLavaImage);dico.put('K', controller.killImage);
        dico.put('*', controller.lavaImage);


        Extract extract = new Extract(fileName);
        map = new Environment();
        map.setMap(extract.getDataList());

        baba = new Baba();
        flag = new Flag();
        rock = new Rock();
        wall = new Wall();
        lava = new Lava();

    }

    public boolean makeMove(String event)
    {
        System.out.println(map);
        addmap();
        baba.move(event);
        flag.move(event);
        rock.move(event);
        wall.move(event);
        lava.move(event);
        return Item.win();
    }


    private void addmap()
    {
        int i = rollback -1;
        System.out.println(i);
        if(tabmap[i] == null)
        {
            while(tabmap[i] == null && i > 0)
                i--;
            tabmap[i + 1] = this;
        }
        else
        {
            for(int j = 1; j >= tabmap.length - 1; j++)
               tabmap[j] = tabmap[j - 1];
        }
    }
    public Environment getMap(){return map;}

    public Main goBack()
    {
        System.out.println("jpadq");
        int i = rollback - 1;
        while(tabmap[i] == null)
        {
            i--;
            System.out.println(i + " " + tabmap[i]);
        }
        System.out.println(map+ " "+ tabmap[i]);
        count = i;
        return tabmap[i];
    }


    public static Image getSprite(int i , int j)
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
                    changeCoord.add(pos);
                }
        return changeCoord;
    }

    public void nextLevel(String fileName)
    {
        Extract extract = new Extract(fileName);
        map.setMap(extract.getDataList());
    }

    public void getSave()
    {
        Save save = new Save();
        save.newSave(map.getMap());
    }

    public static String getLastSave()
    {
        Save save = new Save();
        return save.getLastSave();
    }

    public static void askUser(String input)
    {
        player = User.getUser(input);
    }
}
