package com.example.helloworld;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Controller {
    @FXML
    private Circle myCircle;
    private double x;
    private double y;

    public void up()
    {
        System.out.println("UP");
        myCircle.setCenterY(y=y-10);
    }
    public void down()
    {
        System.out.println("DOWN");
        myCircle.setCenterY(y=y+10);
    }
    public void left()
    {
        System.out.println("LEFT");
        myCircle.setCenterX(x=x-10);
    }
    public void right()
    {
        System.out.println("RIGHT");
        myCircle.setCenterX(x=x+10);
    }
}
