package Presenter;

import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


/**
 * cette class est la pour calculer et ecrire le score dans un fichier txt
 */
public class Score {
    private static String id;
    private static final String pathScoreboard = new File("src/main/resources/users/scoreboard.txt").getAbsolutePath();
    private String fileName;
    private static int userLine = 0;
    static int secondsPassed = 0;
    static int globalScore;
    static final int levelScore = 1500;
    static final Timer myTimer = new Timer();
    final TimerTask task = new TimerTask(){
        public void run(){

            secondsPassed++;
            Scanner sc = new Scanner(System.in);
            if(secondsPassed == 150){
                secondsPassed = 0;
            }
        }
    };

    public Score() {}

    /*
        Va sauvergardé le score actuel pris après avoir réussi un niveau
     */
    public static void saveScore(){
        globalScore = globalScore + levelScore - secondsPassed*10;
        User.searchUser2();
    }
    /*
    Arrête définitivement le score après avoir fini tout les niveaux ou quit and save le jeu
     */
    public static void endScore(){
        globalScore = globalScore + levelScore - secondsPassed*10;
        myTimer.cancel();
    }
    /*
    Va relancé le chrono après avoir réussis un niveau
 */
    public static void stop(){
        globalScore = globalScore + levelScore - secondsPassed*10;
        System.out.println("Your score: "+ globalScore+" points");
        secondsPassed = 0;
    }
    /*
    lance le chrono avec un délai de une seconde
     */
    public void start(){
        myTimer.scheduleAtFixedRate(task,1000,1000);

    }

    public Score(String fileName)
    {
        this.fileName = fileName;
    }

    /*
        écrit les informations dans le fichier txt
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
    /*
        Récupère les informations écrite dans le textField du menu connection
    */
    public String getUserInfo(String userId)
    {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String line;
            StringBuilder res = new StringBuilder();
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
                        res.append(line.charAt(i));
                        userLine = tempLine - 1;
                    }
                }
            }
            scanner.close();
            if(res.toString().equals(""))
                return null;
            return res.toString();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(fileName + " not found");
            e.printStackTrace();
        }
        return null;
    }

}
