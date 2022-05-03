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
    private static final String pathScoreboard = new File("src/main/resources/users/scoreboard.txt").getAbsolutePath();
    private static String id;
    private static Level availablelevel;
    private static final String pathRegister = new File("src/main/resources/users/register.txt").getAbsolutePath();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm:ss");

    public String getId()
    {
        return id;
    }

    User()
    {

    }
    User(String name)
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
            System.out.println("An user is already playing");
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
    static void searchUser2()
    {
        Score info2 = new Score(pathScoreboard);
        String date2 = info2.getUserInfo(id);
        if(date2 == null)
            info2.writeInfo(id +" "+Score.globalScore);
    }

}
