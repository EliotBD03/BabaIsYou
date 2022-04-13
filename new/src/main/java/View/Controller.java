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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
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
    //private VBox[] vbox;
    //private HBox[] hbox;
    //private AnchorPane pane;

    public void setTabpane()
    {
        int length = Main.getLength();
        int width = length;
        //vbox = new VBox[length];
        //hbox = new HBox[length];
        tabpane = new Pane[length][width];
        for(int i = 0 ; i <= length - 1; i ++)
            for(int j = 0; j <= width - 1; j++)
                tabpane[i][j] = new Pane();
    }

    public void initialize(String url, Pane pane){
        {
            Image image = null;
            image = new Image(url);
            ImageView view = new ImageView();
            view.setFitWidth(50);
            view.setFitHeight(50);
            view.setImage(image);
            pane.getChildren().add(view);
        }
    }
    public String sprite(int i,int j)
    {
        return getSprite(i,j);
    }
    public void initializeAll() throws URISyntaxException {
        for(int i = 0; i <= tabpane.length - 1; i++)
            for(int j = 0; j < tabpane[i].length - 2; j++)
                initialize(sprite(i,j),tabpane[i][j]);
    }
    /*
    public void setStage()
    {
        int i = vbox.length;
        while(i >= 0)
        {
            pane.getChildren().add(vbox[i]);
        }
        for(int j  = 0 ;j <= vbox.length - 1)

    }
    public void setScene(HBox[] scene, Pane[][] pane)
    {
        for(int i = 0; i<= pane.length - 1; i++)
            for(int j = 0; j <= pane[i].length - 1; j++)
                scene[i].getChildren().add(pane[i][j]);
    }


     */
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,950, 750 , Color.BLACK);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Settings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,950, 750 , Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGame(ActionEvent event) throws IOException {
            while(true)
            {
                try {
                    new Main().makeTheGame("C:\\Users\\julie\\OneDrive\\Bureau\\newgradle\\src\\main\\resources\\level\\level1.txt");
                    setTabpane();
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
                                Main.makeMove("z");
                                break;
                            case DOWN:
                                Main.makeMove("s");
                                break;
                            case LEFT:
                                Main.makeMove("q");
                                break;
                            case RIGHT:
                                Main.makeMove("d");
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
        monImageView.setLayoutY(y = y - 50);
        monImageView2.setLayoutY(y2 = y2 - 50);
        System.out.println("y" + y);
        if (y < 100) {
            System.out.println("bord de map en y:" + y);
            monImageView.setLayoutY(y = 50);
        }
        if (y2 < 100) {
            monImageView2.setLayoutY(y2 = 50);
        }
    }

    public void down() {
        monImageView.setLayoutY(y = y + 50);
        monImageView2.setLayoutY(y2 = y2 + 50);
        System.out.println("y" + y);
        if (y > 550) {
            System.out.println("bord de map en y:" + y);
            monImageView.setLayoutY(y = 550);
        }
        if (y2 > 550) {
            monImageView2.setLayoutY(y2 = 550);
        }
    }

    public void left() {
        monImageView.setLayoutX(x = x - 50);
        monImageView2.setLayoutX(x2 = x2 - 50);
        monImageView2.setOpacity(1);

        System.out.println("x" + x);
        if (x < 100) {
            System.out.println("bord de map en x:" + x);
            monImageView.setLayoutX(x = 50);
        }
        if (x2 < 100) {
            monImageView2.setLayoutX(x2 = 50);
        }
    }

    public void right() {
        monImageView.setLayoutX(x = x + 50);
        monImageView2.setLayoutX(x2 = x2 + 50);
        monImageView2.setOpacity(0);
        System.out.println("x" + x);
        if (x > 350) {
            System.out.println("bord de map en x:" + x);
            monImageView.setLayoutX(x = 350);
        }
        if (x2 > 350) {
            monImageView2.setLayoutX(x2 = 350);
        }
    }
}