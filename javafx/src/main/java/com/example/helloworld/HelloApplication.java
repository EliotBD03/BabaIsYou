package com.example.helloworld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, Color.BLACK);
            Controller controller = loader.getController();
            stage.setScene(scene);
            stage.show();
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch(event.getCode()){
                        case UP:controller.up();
                        break;
                        case DOWN:controller.down();
                        break;
                        case LEFT:controller.left();
                            break;
                        case RIGHT:controller.right();
                            break;


                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}

