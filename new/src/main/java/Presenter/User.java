package Presenter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * cette classe a pour but d'identifier le joueur
 */
public class User
{
    private static final String pathScoreboard = new File("src/main/resources/users/scoreboard.txt").getAbsolutePath();
    // le nom du joueur
    private static String id;
    // le chemin du registre des inscriptions
    private static final String pathRegister = new File("src/main/resources/users/register.txt").getAbsolutePath();
    // le format de la date
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm:ss");


    /**
     * accesseur
     * @return le nom du joueur
     */
    public String getId()
    {
        return id;
    }

    /**
     * constructeur pour les joueurs randoms
     */
    private User()
    {

    }

    /**
     * constructeur pour les joueurs inscrits
     * (ou pas encore !)
     * @param name le nom du joueur
     */
    private User(String name)
    {
        id = name;
        searchUser();
    }

    /**
     * méthode faisant office de "constructeur" double
     * @param name le nom du joueur
     * @return l'instance de User
     */
    public static User getUser(String name)
    {
        try
        {
            if(User.id != null)
                throw new Exception();
            if(name.length() == 0)
                return new User();
            else
                return new User(name);
        }
        catch (Exception e)
        {
            System.out.println("An user is already playing");
        }
        return new User();
    }

    /**
     * méthode servant à retrouver les informations d'un
     * joueur sur son inscription
     */
    private void searchUser()
    {
        Info info = new Info(pathRegister);
        String date = info.getUserInfo(id);
        if(date == null)
            info.writeInfo(id +" " + dtf.format(LocalDateTime.now()));
    }
    /*
        Même méthode mais dans score Board
     */
    static void searchUser2()
    {
        if(!(id == null))
        {
            Score info2 = new Score(pathScoreboard);
            String date2 = info2.getUserInfo(id);
            if(date2 == null)
                info2.writeInfo(id +" "+Score.globalScore);
        }
    }
}
