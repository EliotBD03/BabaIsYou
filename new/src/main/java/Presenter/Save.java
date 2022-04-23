package Presenter;

import Model.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Save
{
    private final String save_directory = new File("src/main/resources/level/save").getAbsolutePath();
    public static final Map<String, String> dico = new HashMap<>();

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm:ss");
    public Save()
    {
        dico.put("O","baba");dico.put("#", "rock");
        dico.put("@", "flag");dico.put("w","wall");
        dico.put("I", "is"); dico.put("Y", "you");
        dico.put("P", "push");dico.put("S", "stop");
        dico.put("G", "win");dico.put("B", "text_baba");
        dico.put("F", "text_flag");dico.put("R", "text_rock");
        dico.put("W", "text_wall");dico.put("?", "goop");
        dico.put("*", "lava");dico.put("K", "kill");dico.put("L", "text_lava");
        dico.put(" ", null); dico.put("X", null);dico.put("D", "text_goop");

    }
    public void newSave(String[][] tab)
    {
        try
        {
            String file_name = getFileName();

            File file = new File (save_directory+ File.separator + file_name);
                if (file.createNewFile())
                {
                    PrintWriter level = new PrintWriter(file);
                    level.println((tab[0].length - 1 )+ " " + (tab.length - 1));
                    for(int i = 0; i <= tab.length - 1; i++)
                        for(int j= 0; j <= tab[i].length -1 ; j++)
                            if(dico.get(tab[i][j]) != null)
                                level.println(dico.get(tab[i][j]) + " " + (j - 1) + " " + (i - 1));
                    level.close();
                    writeDate(file_name);
                }
        }
        catch (IOException e)
        {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private String getFileName()
    {
        String name = "save";
        int i = 0;
        File file = new File(save_directory + File.separator + name + i + ".txt");
        while(file.exists())
        {
            i++;
            file = new File(save_directory + File.separator + name + i + ".txt");
        }
        return name + i + ".txt";
    }

    private void writeDate(String fileName) throws IOException {
        File history = new File(save_directory + File.separator + "history.txt");
        if(history.exists())
        {
            FileWriter fw = new FileWriter(history, true);
            fw.write("\n"+ fileName + " " + dtf.format(LocalDateTime.now()));
            fw.close();

        }

    }

    public String getLastSave(){
        try {
                Scanner scanner = new Scanner(new File(save_directory + File.separator + "history.txt"));
                String line = null;
                while (scanner.hasNextLine())
                {
                    line = scanner.nextLine();
                }
                scanner.close();
                String res = "";
                int i  = 0;
                while(line.charAt(i) != ' ')
                {
                    res += line.charAt(i);
                    i ++;
                }
                return save_directory + File.separator + res;
            }
        catch (FileNotFoundException e)
        {
            System.out.println(save_directory + File.separator + "history.txt" + " not found");
            e.printStackTrace();
        }
        return null;

    }

}
