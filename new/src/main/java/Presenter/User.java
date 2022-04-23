package Presenter;

import java.io.File;
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
    private final int[] levelAccess = new int[5];
    private static final String random = "player";
    private static final String pathRegister = new File("src/main/resources/users/register.txt").getAbsolutePath();
    private static final String pathAccessLevel = new File("src/main/resources/users/access_level.txt").getAbsolutePath();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm:ss");

    public int[] getLevelAccess(){return levelAccess;}
    public User()
    {
        id = random;
    }
    public User(String name)
    {
        id = name;
    }

    private void searchUser()
    {
        Info info = new Info(pathRegister);
        String date = info.getUserInfo(id);
        if(date == null)
            info.writeInfo(id + " " + dtf.format(LocalDateTime.now()));
    }
    private void searchAccess()
    {
        String res = "";
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
        for(int i = 0; i <= levelAccess.length -1; i ++)
            res += levelAccess[i] + " ";
    }

    private void addLevel(int level)
    {
        searchAccess();
        if(levelAccess[level] == 0)
            levelAccess[level] = level;
    }

    private void writeLevelAccess()
    {
        Info info = new Info(pathAccessLevel);
        String res = "";
        for(int i = 0; i <= levelAccess.length - 1; i ++)
            res += levelAccess[i];
        info.writeInfoUser(id + " " + res);
    }

    public static void main(String[] args)
    {
        User user = new User("juju");
        user.searchAccess();
        user.addLevel(3);
        user.writeLevelAccess();
    }
}
