package Presenter;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * cette classe a pour but d'identifier le joueur
 */
public class User
{
    private static String id;
    private final int[] levelAccess = new  int[5];
    private static final String random = "player";
    private static final String pathRegister = new File("src/main/resources/users/register.txt").getAbsolutePath();
    private static final String pathAccessLevel = new File("src/main/resources/users/access_level.txt").getAbsolutePath();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm:ss");

    public int[] getLevelAccess(){return levelAccess;}
    private User()
    {
        id = random;
        addLevel(1);
        addLevel(2);
        addLevel(3);
        addLevel(4);
    }
    private User(String name)
    {
        id = name;
        searchUser();
    }

    public static User getUser(String input)
    {
        try
        {
            if(User.id != null)
                throw new Exception();
            if(input.length() == 0)
                return new User();
            else
                return new User(input);
        }
        catch (Exception e)
        {
            System.out.println("An user is already defined");
        }
        return new User();
    }

    private void searchUser()
    {
        Info info = new Info(pathRegister);
        String date = info.getUserInfo(id);
        if(date == null)
            info.writeInfo(id +" " + dtf.format(LocalDateTime.now()));
    }
    private void searchAccess()
    {
        if(id.equals(random))
            return;
        Info info = new Info(pathAccessLevel);
        String access = info.getUserInfo(id);
        if(access == null)
            info.writeInfo(id + " " + 0);
        else
            for(int i = 0; i <= access.length() - 1; i++)
            {
                if(i <= levelAccess.length -1)
                {
                    String nb = String.valueOf(access.charAt(i));
                    levelAccess[i] = Integer.parseInt(nb);
                }
            }
    }

    private void addLevel(int level)
    {
        if(id.equals(random))
            return;
        searchAccess();
        if(levelAccess[level] == 0)
            levelAccess[level] = level;
    }

    private void writeLevelAccess()
    {
        if(id.equals(random))
            return;
        Info info = new Info(pathAccessLevel);
        String res = "";
        for(int i = 0; i <= levelAccess.length - 1; i ++)
            res += levelAccess[i];
        info.writeInfoUser(res, id);
    }

    public static void main(String[] args)
    {
        User user = getUser("pipi");
        user.addLevel(3);
        user.writeLevelAccess();
    }
}
