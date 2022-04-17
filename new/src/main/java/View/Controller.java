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

        import java.io.File;
        import java.io.IOException;
        import java.net.URISyntaxException;
        import java.util.ArrayList;


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
    private static int count_move = 0;

    public final Image babaImage = new Image(getClass().getResource( "/sprite/baba.gif").toURI().toString());
    public final Image flagImage = new Image(getClass().getResource( "/sprite/Flag.png").toURI().toString());
    public final Image rockImage = new Image(getClass().getResource( "/sprite/rock.png").toURI().toString());
    public final Image wallImage = new Image(getClass().getResource( "/sprite/wall.png").toURI().toString());
    public final Image borderImage = new Image(getClass().getResource( "/sprite/border.png").toURI().toString());
    public final Image fonfImage = new Image(getClass().getResource( "/sprite/fonf.png").toURI().toString());
    public final Image pushImage = new Image(getClass().getResource( "/sprite/push.png").toURI().toString());
    public final Image isImage = new Image(getClass().getResource( "/sprite/textis.png").toURI().toString());
    public final Image stopImage = new Image(getClass().getResource( "/sprite/stop.png").toURI().toString());
    public final Image winImage = new Image(getClass().getResource( "/sprite/win.png").toURI().toString());
    public final Image youImage = new Image(getClass().getResource( "/sprite/you.png").toURI().toString());
    public final Image textBabaImage =new Image(getClass().getResource( "/sprite/textbaba.png").toURI().toString());
    public final Image textRockImage = new Image(getClass().getResource( "/sprite/textrock.png").toURI().toString());
    public final Image textFlagImage = new Image(getClass().getResource( "/sprite/textflag.png").toURI().toString());
    public final Image textWallImage = new Image(getClass().getResource( "/sprite/walltext.png").toURI().toString());

    //private VBox[] vbox;
    //private HBox[] hbox;
    //private AnchorPane pane;
    private static HBox[] tabhbox;
    @FXML
    private static VBox vbox = new VBox();

    public Controller() throws URISyntaxException {
    }

    public void settabhbox(){
        for (int i = 0; i <= tabhbox.length - 1; i++)
        {
            tabhbox[i] = new HBox();
            for(int j=0; j <= tabpane[0].length  - 1; j++)
            {
                tabhbox[i].getChildren().add(tabpane[i][j]);
            }
        }
    }
    public void setvbox(){
        for(HBox hbox :tabhbox)
        {
            vbox.getChildren().add(hbox);
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


    public void initialize(Image image, Pane pane){
        {
            ImageView view = new ImageView();
            view.setFitWidth(25);
            view.setFitHeight(25);
            view.setImage(image);
            pane.getChildren().add(view);

        }
    }
    public Image sprite(int i,int j)
    {
        return getSprite(i,j);
    }
    public void initializeAll(){
        for(int i = 0; i <= tabpane.length - 1; i++)
            for(int j = 0; j <= tabpane[i].length - 1; j++)
            {
                tabpane[i][j] = new Pane();
                initialize(sprite(i,j),tabpane[i][j]);
            }
    }
    public void actualise(ArrayList<int[]> change)
    {
        if(change.size() > 0)
        {
            for(int i = 0; i <= change.size() - 1; i++)
            {
                initialize(sprite(change.get(i)[0], change.get(i)[1]),tabpane[change.get(i)[0]][change.get(i)[1]]);
            }
        }
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Settings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGame(ActionEvent event) throws IOException {
        try {
            Main game = new Main();
            File file = new File("src/main/resources/level/default/level1.txt");
            System.out.println(file.getAbsolutePath());
            game.makeTheGame(file.getAbsolutePath());
            //System.out.println("1");
            setTabpane();
            //System.out.println("2");
            initializeAll();
            //System.out.println("3");
            settabhbox();
            //System.out.println("4");
            setvbox();
            //System.out.println("5");
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(vbox,450,500,Color.BLACK);
            stage.setScene(scene);
            //System.out.println("6");
            {
                scene.setOnKeyPressed(event1 -> {
                    switch (event1.getCode()) {
                        case UP:
                            game.makeMove("z");
                            System.out.println("up");
                            count_move ++;
                            break;
                        case DOWN:
                            game.makeMove("s");
                            System.out.println("down");
                            count_move ++;
                            break;
                        case LEFT:
                            game.makeMove("q");
                            System.out.println("left");
                            count_move ++;
                            break;
                        case RIGHT:
                            game.makeMove("d");
                            System.out.println("right");
                            count_move ++;
                            break;
                    }
                    actualise(game.getChanges());
                    System.out.println(count_move);
                });
            }
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
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
