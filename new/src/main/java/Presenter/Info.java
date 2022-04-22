package Presenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * cette classe est faite pour pouvoir accéder aux infos d'un fichier texte
 */
public class Info
{
    private String fileName;

    public Info(String file_name)
    {
        this.fileName = file_name;
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

    public String getUserInfo(String userId)
    {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String line = null;
            String res = "";
            while (scanner.hasNextLine())
            {
                line = scanner.nextLine();
                for(int i = 0; i <= line.length() - 1; i++)
                {
                    if(i <= userId.length() - 1 && line.charAt(i) != userId.charAt(i))
                        break;
                    else if(i > userId.length() - 1 && line.charAt(i) != ' ')
                        res += line.charAt(i);
                }
            }
            scanner.close();
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
        Info info = new Info("C:\\Users\\julie\\OneDrive\\Bureau\\BabaIsYou\\new\\src\\main\\resources\\level\\save\\history.txt");
        System.out.println(info.getUserInfo("juju"));
    }

}
