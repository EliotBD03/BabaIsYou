package Presenter;
import java.util.Timer;
import java.util.TimerTask;
/**
 * cette classe à pour but d'établir un tableau des scores
 */

public class Score
{
int secondsPassed=0;
Timer myTimer = new Timer();
TimerTask task = new TimerTask(){
    public void run(){
        secondsPassed++;
        System.out.println("Vous avez perdu: "+ secondsPassed+"0 points");
    }
};
public void start(){
    myTimer.scheduleAtFixedRate(task,1000,1000);

}
public static void main(String[] args){
    Score score = new Score();
    score.start();
}
}
