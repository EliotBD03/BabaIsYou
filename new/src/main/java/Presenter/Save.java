package Presenter;

import Model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Save
{
    private final String save_directory = new File("src/main/resources/level/save").getAbsolutePath();
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
    public void newSave(String levelName, String[][] tab)
    {
        try
        {
            File file = new File (save_directory+ File.separator + levelName);
            System.out.println(save_directory + File.separator + levelName);
            if (!(file.exists()))
            {
                if (file.createNewFile())
                {
                    PrintWriter level = new PrintWriter(file);
                    level.println(tab[0].length + " " + tab.length);
                    for(int i = 0; i <= tab.length - 1; i++)
                        for(int j= 0; j <= tab[i].length -1 ; j++)
                            if(dico.get(tab[i][j]) != null)
                                level.println(dico.get(tab[i][j]) + " " + j + " " + i);
                    level.close();
                }
            }
            else
                System.out.println("je passes pas");
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
        System.out.println(save.save_directory);
        Extract extract = new Extract("/home/julien/Bureau/BabaIsYou/new/src/main/resources/level/default/level1.txt");
        Environment map = new Environment();
        map.setMap(extract.getDataList());

        save.newSave( "level1_save.txt", map.getMap());

    }
}
