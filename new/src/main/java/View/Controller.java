package View;

import Presenter.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static Presenter.Main.getSprite;


public class Controller {
    private ImageView monImageView;
    private ImageView monImageView2;
    private double x = 200.0;
    private double y = 250.0;
    private double x2 = 200.0;
    private double y2 = 250.0;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane scenePane;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Pane[][] tabpane;

    public void setTabpane(int x)
    {
        int length = x;
        int width = x;
        tabpane = new Pane[length][width];
        for(int i = 0 ; i < length - 1; i ++)
            for(int j = 0; j < width - 1; j++)
            {
                tabpane[i][j] = new Pane();
            }
    }

    public void initialize(String url, Pane pane) throws URISyntaxException {
            Image image = null;
            if(getClass().getResource(url) != null )
                image = new Image(getClass().getResource(url).toURI().toString());
            ImageView view = new ImageView();
            view.setFitWidth(25);
            view.setFitHeight(25);
            view.setImage(image);
            if(image != null )
                pane.getChildren().add(view);
    }
    public String sprite(int i,int j)
    {
        return getSprite(i,j);
    }
    public void initializeAll() throws URISyntaxException {
        for(int i = 0; i <= tabpane.length - 1; i++)
            for(int j = 0; j < tabpane[i].length - 1; j++)
            {
                initialize(sprite(i,j),tabpane[i][j]);
            }
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,950, 750 , Color.BLACK);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,950, 750 , Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToGame(ActionEvent event) throws IOException {
            while(true)
            {
                try {
                Main.makeTheGame("/home/julien/Bureau/BabaIsYou/new/src/main/resources/level/level1.txt");
                setTabpane(Main.getLength());
                initializeAll();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hello-view.fxml"));
                Parent root = loader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Controller controller = loader.getController();
                scene = new Scene(root,950,750,Color.BLACK);
                stage.setScene(scene);
                stage.show();

                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        switch (event.getCode()) {
                            case UP:
                                controller.up();
                                break;
                            case DOWN:
                                controller.down();
                                break;
                            case LEFT:
                                controller.left();
                                break;
                            case RIGHT:
                                controller.right();
                                break;


                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            }
    }
    public void logout(ActionEvent event){
        stage = (Stage)scenePane.getScene().getWindow();
        System.out.println("logout");
        stage.close();
    }
    public void up() {
        Main.makeMove("z");
    }

    public void down() {
        Main.makeMove("s");
    }

    public void left() {
        Main.makeMove("q");
    }

    public void right() {
        Main.makeMove("d");
    }
}