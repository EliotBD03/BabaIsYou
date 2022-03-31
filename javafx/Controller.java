package com.example.helloworld;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Controller {
    @FXML
    private ImageView monImageView;
    private double x = 278.0;
    private double y = 165.0;


    public void up()
    {
        System.out.println("UP");
        monImageView.setLayoutY(y=y-10);
    }
    public void down()
    {
        System.out.println("DOWN");
        monImageView.setLayoutY(y=y+10);
    }
    public void left()
    {
        System.out.println("LEFT");
        monImageView.setLayoutX(x=x-10);
    }
    public void right()
    {
        System.out.println("RIGHT");
        monImageView.setLayoutX(x=x+10);
    }
}
