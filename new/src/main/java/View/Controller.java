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
    private Stage stage1;
    private Scene scene1;
    private Parent root;
    private Pane[][] tabpane;

    //private VBox[] vbox;
    //private HBox[] hbox;
    //private AnchorPane pane;
    private static HBox[] tabhbox;
    @FXML
    private static VBox vbox1 = new VBox();

    public void settabhbox(){
        for (int i = 0; i <= tabhbox.length - 1; i++)
        {
            tabhbox[i] = new HBox();
            for(int j=0; j < tabpane[0].length  - 1; j++)
            {
                tabhbox[i].getChildren().add(tabpane[i][j]);
            }
        }
    }
    public void setvbox(){
        for(HBox hbox :tabhbox)
        {
            vbox1.getChildren().add(hbox);
        }
    }
    public void setTabpane()
    {
        int length = Main.getLength();
        int width = length;
        tabhbox = new HBox[length];
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
            view.setFitWidth(25);
            view.setFitHeight(25);
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

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root,950, 750 , Color.BLACK);
        stage1.setScene(scene1);
        stage1.show();

    }
    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Settings.fxml"));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root,950, 750 , Color.BLACK);
        stage1.setScene(scene1);
        stage1.show();
    }

    public void switchToGame(ActionEvent event) throws IOException {
            while(true)
            {
                try {
                    new Main().makeTheGame("/home/julien/Bureau/BabaIsYou/new/src/main/resources/level/level1.txt");
                    setTabpane();
                    initializeAll();
                    settabhbox();
                    setvbox();
                    stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene1 = new Scene(vbox1,950,750,Color.BLACK);
                    stage1.setScene(scene1);
                    stage1.show();

                scene1.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
        stage1 = (Stage)scenePane.getScene().getWindow();
        System.out.println("logout");
        stage1.close();
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
