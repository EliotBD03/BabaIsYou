package Presenter;
import Model.*;
import View.Controller;
import javafx.scene.image.Image;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Game
{
    private static User player;
    private static Level level;
    private static Environment map;

    private static Controller controller;

    private static Baba baba;
    private static Flag flag;
    private static Rock rock;
    private static Wall wall;

    private static Lava lava;

    private static  String[][] temp;

    private static final Map<Character, Image> dico = new HashMap<Character, Image>();

    public static int getLength(){return map.getMap().length;}

    public Game(String userName) throws URISyntaxException
    {
        player =  User.getUser(userName);
        level = new Level(player.getId());
        controller = new Controller();

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
    }

    public void makeTheGame(int index){

        level.setCurrentIndex(index);
        Extract extract = new Extract(level.getCurrentLevel());
        map = new Environment();
        map.setMap(extract.getDataList());

        baba = new Baba();
        flag = new Flag();
        rock = new Rock();
        wall = new Wall();
        lava = new Lava();

    }

    public void makeTheGame(String fileName)
    {
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
        baba.move(event);
        flag.move(event);
        rock.move(event);
        wall.move(event);
        lava.move(event);
        return Item.win();
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

    public boolean nextLevel()
    {
        Score.stop();
        boolean continu = level.goNext();
        if(continu)
        {
            Extract extract = new Extract(level.getCurrentLevel());
            map.setMap(extract.getDataList());
        }
        return continu;
    }

    public void getSave()
    {
        if(player.getId() == null)
            return;
        level.writeLevelAccess(player.getId());
        Save save = new Save();
        save.newSave(map.getMap());
    }

    public static String getLastSave()
    {
        level.fromSave();
        Save save = new Save();
        return save.getLastSave();
    }

    public boolean setLevel(int index)
    {
        return level.setCurrentIndex(index);
    }

    public String getLevel()
    {
        return level.getCurrentLevel();
    }

}
