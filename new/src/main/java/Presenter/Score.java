package Presenter;

import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


/**
 * cette classe est faite pour pouvoir accéder aux infos d'un fichier texte
 */
public class Score {
    private static String id;
    private static final String pathScoreboard = new File("src/main/resources/users/scoreboard.txt").getAbsolutePath();
    private String fileName;
    private static int userLine = 0;
    static int secondsPassed = 0;
    static int globalScore;
    static int levelScore = 1500;
    static Timer myTimer = new Timer();
    TimerTask task = new TimerTask(){
        public void run(){

            secondsPassed++;
            System.out.println("Vous avez perdu: "+ secondsPassed+"0 points");
            Scanner sc = new Scanner(System.in);
            if(secondsPassed == 150){
                secondsPassed = 0;
            }
        }
    };
    public String getId()
    {
        return id;
    }


    public Score() {}


    public static void saveScore(){
        globalScore = globalScore + levelScore - secondsPassed*10;
        User.searchUser2();
    }
    public static void endScore(){
        globalScore = globalScore + levelScore - secondsPassed*10;
        myTimer.cancel();
    }
    public static void stop(){
        globalScore = globalScore + levelScore - secondsPassed*10;
        System.out.println("Votre score: "+ globalScore+" points");
        secondsPassed = 0;
    }

    public void start(){
        myTimer.scheduleAtFixedRate(task,1000,1000);

    }

    public Score(String fileName)
    {
        this.fileName = fileName;
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

}
