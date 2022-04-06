package com.example.helloworld;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Controller {
    @FXML
    private ImageView monImageView;
    @FXML
    private ImageView monImageView2;
    private double x = 203.0;
    private double y = 277.0;
    private double x2 = 203.0;
    private double y2 = 277.0;


    public void up()
    {
        monImageView.setLayoutY(y=y-50);
        monImageView2.setLayoutY(y2=y2-50);
        System.out.println("y"+y);
        if (y<100){
            System.out.println("bord de map en y:"+y);
            monImageView.setLayoutY(y=50);
        }
        }
    public void down()
    {
        monImageView.setLayoutY(y=y+50);
        monImageView2.setLayoutY(y2=y2+50);
        System.out.println("y"+y);
        if (y>550){
            System.out.println("bord de map en y:"+y);
            monImageView.setLayoutY(y=550);
        }
    }
    public void left()
    {
        monImageView.setLayoutX(x=x-50);
        monImageView2.setLayoutX(x2=x2-50);
        monImageView2.setOpacity(1);

        System.out.println("x"+x);
        if (x<100){
            System.out.println("bord de map en x:"+x);
            monImageView.setLayoutX(x=50);
        }
    }
    public void right()
    {
        monImageView.setLayoutX(x=x+50);
        monImageView2.setLayoutX(x2=x2+50);
        monImageView2.setOpacity(0);
        System.out.println("x"+x);
        if (x>350){
            System.out.println("bord de map en x:"+x);
            monImageView.setLayoutX(x=350);
        }
    }
}
