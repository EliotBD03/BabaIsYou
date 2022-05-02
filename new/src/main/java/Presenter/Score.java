package Presenter;
import java.util.Timer;
import java.util.TimerTask;
/**
 * cette classe à pour but d'établir un tableau des scores
 */

public class Score
{
int secondsPassed = 0;
int globalScore = 1500;
Timer myTimer = new Timer();
TimerTask task = new TimerTask(){
    public void run(){
        secondsPassed++;
        System.out.println("Vous avez perdu: "+ secondsPassed+"0 points");
        if(secondsPassed == 5){
            stop();
            cancel();
        }
    }
};
public void stop(){
globalScore = globalScore - secondsPassed*10;
    System.out.println("Votre score: "+ globalScore+" points");
}

public void start(){
    myTimer.scheduleAtFixedRate(task,1000,1000);

}
public static void main(String[] args){
    Score score = new Score();
    score.start();
}
}
