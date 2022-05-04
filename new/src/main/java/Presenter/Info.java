package Presenter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * cette classe est faite pour pouvoir accéder(écrire) aux infos d'un fichier texte
 */
public class Info
{
    //le nom du fichier
    private String fileName;

    /**
     * constructeur
     * @param fileName le nom du fichier
     */
    public Info(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * méthode servant à récupérer la dernière information d'un fichier
     * @return l'information
     */
    public String getLastLine()
    {
        try {
            Scanner scanner = new Scanner(new File(fileName));
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
            return res;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(fileName + " not found");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * méthode servant à écrire une information à la fin d'un fichier
     * @param information l'information à mettre
     */

    public void writeInfo(String information)
    {
        try {

            File file = new File(fileName);
            if(file.exists())
            {
                FileWriter fw = new FileWriter(fileName, true);
                fw.write("\n" + information);
                fw.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("cannot write in " + fileName);
            e.printStackTrace();
        }
    }

    /**
     * méthode servant à écrire une information à partir d'un nom utilisateur
     * @param information l'information à écrire
     * @param userName le nom utilisateur
     */
    public void writeInfoUser(String information, String userName){
        try
        {
            //on cherche le chemin du fichier
            Path path = Paths.get(fileName);
            //on met tout le contenu d'une liste
            List<String> lines = Files.readAllLines(path);
            //la liste du fichier résultant
            List<String> res = new ArrayList<>();
            int i = 0;
            //tant qu'on n'a pas trouvé le nom donné en paramètre
            while(i <= lines.size() - 1)
            {
                System.out.println(lines.get(i));
                if(lines.get(i).length() > userName.length() && lines.get(i).substring(0,userName.length()).equals(userName))
                    res.add(userName + " " + information);
                else
                    res.add(lines.get(i));
                System.out.println("indice i : " + i + " + mot : " + lines.get(i));
                i++;
            }
            Files.write(path, res);

        }
        catch (IOException e)
        {
            System.out.println("cannot write in " + fileName);
            e.printStackTrace();
        }

    }

    /**
     * méthode servant à récupérer de l'information à partir
     * d'un nom utilisateur
     * @param userId nom utilisateur
     * @return l'information
     */

    public String getUserInfo(String userId)
    {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String line = null;
            String res = "";
            int tempLine = 0;
            while (scanner.hasNextLine())
            {
                tempLine += 1;
                line = scanner.nextLine();
                for(int i = 0; i <= line.length() - 1; i++)
                {
                    if(i <= userId.length() - 1 && line.charAt(i) != userId.charAt(i))
                        break;
                    else if(i > userId.length() - 1 && line.charAt(i) != ' ')
                    {
                        res += line.charAt(i);
                    }
                }
            }
            scanner.close();
            if(res.equals(""))
                return null;
            return res;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(fileName + " not found");
            e.printStackTrace();
        }
        return null;
    }
    
}

