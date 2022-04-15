package Presenter;

import Model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.Map;

public class Save
{
    private String save_directory = getClass().getResource("/level/save").toString();
    public static final Map<String, String> dico = new HashMap<>();
    public Save()
    {
        dico.put("O","baba");dico.put("#", "rock");
        dico.put("@", "flag");dico.put("w","wall");
        dico.put("I", "is"); dico.put("Y", "you");
        dico.put("P", "push");dico.put("S", "stop");
        dico.put("G", "win");dico.put("B", "text_baba");
        dico.put("F", "text_flag");dico.put("R", "text_rock");
        dico.put("W", "text_wall");dico.put(" ", null); dico.put("X", null);

    }
    public void save(String levelName, String[][] tab)
    {
        try
        {
            PrintWriter file = new PrintWriter(levelName , "UTF-8");
            file.println(tab[0].length + " " + tab.length);
            for(int i = 0; i <= tab.length - 1; i++)
                for(int j= 0; j <= tab[i].length -1 ; j++)
                    if(dico.get(tab[i][j]) != null)
                        file.println(dico.get(tab[i][j]) + " " + i + " " + j);
            file.close();
        }
        catch (IOException e)
        {
        System.out.println("An error occurred");
        e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        Save save = new Save();

        Extract extract = new Extract("/home/julien/Bureau/BabaIsYou/new/src/main/resources/level/default/level1.txt");
        Environment map = new Environment();
        map.setMap(extract.getDataList());

        save.save("level1_save.txt", map.getMap());

    }
}
