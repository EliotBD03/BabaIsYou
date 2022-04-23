package Presenter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * cette classe est faite pour pouvoir accéder aux infos d'un fichier texte
 */
public class Info
{
    private String fileName;

    private static int userLine = 0;

    public Info(String fileName)
    {
        this.fileName = fileName;
    }

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

    public void writeInfoUser(String information){
        try
        {
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path);
            System.out.println(lines.get(userLine));
            lines.add(userLine, information);
            lines.remove(userLine + 1);
            lines.remove(userLine - 1);
            Files.write(path, lines);
        }
        catch (IOException e)
        {
            System.out.println("cannot write in " + fileName);
            e.printStackTrace();
        }

    }

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
                        userLine = tempLine - 1;
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

    public static void main(String[] args)
    {
        Info info = new Info("/home/julien/Bureau/BabaIsYou/BabaIsYou/new/src/main/resources/level/save/history.txt");
        System.out.println(info.getUserInfo("juju"));
    }

}
