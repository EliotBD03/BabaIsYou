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
            boolean flag = true;
            //tant qu'on n'a pas trouvé le nom donné en paramètre
            while(i <= lines.size() - 1)
            {
                if(lines.get(i).length() > userName.length() && lines.get(i).substring(0,userName.length()).equals(userName))
                {
                    res.add(userName + " " + information);
                    flag = false;
                }
                else
                    res.add(lines.get(i));
                i++;
            }
            //dans le cas où on n'a pas trouvé le nom
            if(flag)
               res.add(userName + " " + information);
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
     * @param flag vrai si on veut avoir une limite de ligne d'un fichier, faux sinon
     * @return l'information
     */

    public String getUserInfo(String userId, boolean flag)
    {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String line;
            String res = "";
            //cette variable nous servira à compter le nombre de ligne et de vérifier si
            //le dépassement de capacité est en dessous. (voir classe User)
            int lineCount = 0;
            while (scanner.hasNextLine())
            {
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
                lineCount++;
                if(lineCount > 10 && flag)
                {
                    return "OvErFl@W";
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

