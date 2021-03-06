package Presenter;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        dico.put("D", "sink"); dico.put("$", "glue"); dico.put("T", "sticky");
        dico.put("C", "text_glue");

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
                if (file.createNewFile() || file.exists())
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
     * du nom du joueur
     * @return le nom de la sauvegarde
     */
    private String getFileName()
    {
        Info info = new Info(save_directory + File.separator + "history.txt");
        String res = info.getUserInfo(User.getId(), false);
        if(res == null)
        {
            res = "save" + User.getId() +".txt";
        }
        else
            res = res.substring(0, 8 + User.getId().length());
        return res;
    }

    /**
     * méthode servant à écrire le nom de la sauvegarde + sa date de création
     * dans l'historique des sauvegardes (history.txt)
     * @param fileName le nom de la sauvegarde
     */
    private void writeDate(String fileName) {
        Info info = new Info(save_directory + File.separator + "history.txt");
        info.writeInfoUser(fileName + " "+ dtf.format(LocalDateTime.now()), User.getId());
    }

    /**
     * méthode servant à récupérer la dernière sauvegarde
     * (à partir de l'historique)
     * @return l'url de la dernière sauvegarde
     */

    public String getLastSave(){
        Info info = new Info(save_directory + File.separator + "history.txt");
        String res = null;
        if(User.getId() != null)
            res = info.getUserInfo(User.getId(), false);
        if(res == null)
        {
            System.out.println("You haven't saved yet");
            return null;
        }
        res = res.substring(0,8 + User.getId().length());
        return save_directory + File.separator + res;

    }
}
