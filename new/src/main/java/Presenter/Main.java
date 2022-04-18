package Presenter;
import Model.*;
import View.Controller;
import javafx.scene.image.Image;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Main
{
    private static Environment map;
    private static ArrayList<int[]> changeCoord;
    private static Baba baba;
    private static Flag flag;
    private static Rock rock;
    private static Wall wall;

    private static Goop goop;

    private static  String[][] temp;

    private static final Map<Character, Image> dico = new HashMap<Character, Image>();

    public static int getLength(){return map.getMap().length;}

    public Main() throws URISyntaxException {
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


    }

    public void makeTheGame(String fileName) {
        changeCoord = null;

        Extract extract = new Extract(fileName);
        map = new Environment();
        map.setMap(extract.getDataList());
        map.actualiseMap();

        baba = new Baba();
        flag = new Flag();
        rock = new Rock();
        wall = new Wall();
        goop = new Goop();
    }

    public void makeMove(String event)
    {
        baba.move(event);
        flag.move(event);
        rock.move(event);
        wall.move(event);
        goop.move(event);
    }

    public static Image getSprite(int i , int j)
    {
        return dico.get(map.getMap()[i][j].charAt(0));
    }
    public ArrayList<int[]> getChanges()
    {
        changeCoord = new ArrayList<>();
        temp = Environment.getStringMap(map.getMap());
        map.actualiseMap();
        String[][] actual = map.getMap();
        for(int i = 0; i <= actual.length -1; i++)
        {
            for(int j = 0; j < actual[j].length -1; j++)
                System.out.print(temp[i][j]);
            System.out.print("\n");
        }
        System.out.println(map);
        for(int i = 0; i <= actual.length -1; i++)
            for(int j = 0; j <= actual[i].length -1; j++)
                if(!(actual[i][j].equals(temp[i][j])))
                {
                    int[] pos = {i,j};
                    System.out.println(i + " " + j + temp[i][j]);
                    changeCoord.add(pos);
                }
        return changeCoord;
    }

    public boolean stop()
    {
        return Item.win();
    }
}
