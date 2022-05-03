package Presenter;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * classe ayant pour but d'effectuer des sauvegardes et d'en charger
 */
public class Save
{
    //chemin du dossier des sauvegardes
    private final String save_directory = new File("src/main/resources/level/save").getAbsolutePath();
    //dictionnaire utilisé pour faire le lien entre la map de String et les mots dans un fichier level
    public static final Map<String, String> dico = new HashMap<>();
    //le format de la date à écrire
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm:ss");

    /**
     * constructeur qui "initialise" le dico
     */
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
        dico.put(" ", null); dico.put("X", null);dico.put("M", "text_goop");
        dico.put("D", "sink");

    }

    /**
     * méthode servant à créer une nouvelle sauvegarde
     * @param tab la map de String actuelle
     */

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

    /**
     * méthode servant à trouver le nom de la sauvegarde en fonction
     * du nombre qu'il y a dans le dossier
     * @return le nom de la sauvegarde
     */
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

    /**
     * méthode servant à écrire le nom de la sauvegarde + sa date de création
     * dans l'historique des sauvegardes (history.txt)
     * @param fileName le nom de la sauvegarde
     * @throws IOException si on ne trouve pas le fichier
     */
    private void writeDate(String fileName) throws IOException {
        File history = new File(save_directory + File.separator + "history.txt");
        if(history.exists())
        {
            FileWriter fw = new FileWriter(history, true);
            fw.write("\n"+ fileName + " "+ dtf.format(LocalDateTime.now()));
            fw.close();
        }
    }

    /**
     * méthode servant à récupérer la dernière sauvegarde
     * (à partir de l'historique)
     * @return l'url de la dernière sauvegarde
     */

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
