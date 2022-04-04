package com.example.helloworld;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Controller {
    @FXML
    private ImageView monImageView;
    private double x = 250.0;
    private double y = 150.0;


    public void up()
    {
        monImageView.setLayoutY(y=y-50);
        System.out.println("y"+y);
        if (y<100){
            System.out.println("bord de map en y:"+y);
            monImageView.setLayoutY(y=50);
        }
        }
    public void down()
    {
        monImageView.setLayoutY(y=y+50);
        System.out.println("y"+y);
        if (y>250){
            System.out.println("bord de map en y:"+y);
            monImageView.setLayoutY(y=300);
        }
    }
    public void left()
    {
        monImageView.setLayoutX(x=x-50);
        System.out.println("x"+x);
        if (x<100){
            System.out.println("bord de map en x:"+x);
            monImageView.setLayoutX(x=50);
        }
    }
    public void right()
    {
        monImageView.setLayoutX(x=x+50);
        System.out.println("x"+x);
        if (x>450){
            System.out.println("bord de map en x:"+x);
            monImageView.setLayoutX(x=450);
        }
    }
}
