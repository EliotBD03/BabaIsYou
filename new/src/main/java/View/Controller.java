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
    @FXML
    private Button playButton;
    private Stage stage;
    private static Scene scene;
    private Parent root;
    private static  Pane[][] tabpane;
    private static int count_move = 0;

    public final Image babaImage = new Image(getClass().getResource( "/sprite/baba.gif").toURI().toString());
    public final Image flagImage = new Image(getClass().getResource( "/sprite/Flag.png").toURI().toString());
    public final Image rockImage = new Image(getClass().getResource( "/sprite/rock.png").toURI().toString());
    public final Image wallImage = new Image(getClass().getResource( "/sprite/wall.png").toURI().toString());
    public final Image goopImage = new Image(getClass().getResource("/sprite/water.gif").toURI().toString());
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
    public final Image textGoopImage = new Image(getClass().getResource("/sprite/gooptext.png").toURI().toString());
    public final Image textSinkImage = new Image(getClass().getResource("/sprite/sinktext.png").toURI().toString());

    private final String[] levelList = {"level0.txt", "level1.txt", "level2.txt", "level3.txt", "level4.txt"};
    private static String level;

    private static int indexLevel = 0;

    private static Main game;

    private Main tempGame;

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
    public void switchToMapChoice(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mapChoice.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLetsGame(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/letsgame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToPause(ActionEvent event)throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Pause.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, 450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();

        /*
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Pause.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        System.out.println(stage);
        scene = new Scene(root,450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();
         */
    }
    public void switchToGame(ActionEvent event){
        try {
            initializeGame("src/main/resources/level/default/" + levelList[indexLevel]);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(vbox,500,500,Color.BLACK);
            stage.setScene(scene);
            keyInput(event);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void keyInput(ActionEvent event)
    {
        scene.setOnKeyPressed(event1 -> {
            switch (event1.getCode()) {
                case ESCAPE:
                    try {
                        switchToPause(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case R:
                    game.goBack();
                    break;
                case UP:
                    if(game.makeMove("z"))
                    {

                        if(indexLevel < levelList.length - 1)
                        {
                            indexLevel++;
                            nextLevel(event);
                        }
                        else
                            stage.close();

                    }
                    System.out.println("up");
                    count_move ++;
                    break;
                case DOWN:
                    if(game.makeMove("s"))
                    {

                        if(indexLevel < levelList.length - 1)
                        {
                            indexLevel++;
                            nextLevel(event);
                        }
                        else
                            stage.close();

                    }
                    System.out.println("down");
                    count_move ++;
                    break;
                case LEFT:
                    if(game.makeMove("q"))
                    {

                        if(indexLevel < levelList.length - 1)
                        {
                            indexLevel++;
                            nextLevel(event);
                        }
                        else
                            stage.close();

                    }
                    System.out.println("left");
                    count_move ++;
                    break;
                case RIGHT:
                    if(game.makeMove("d"))
                    {

                        if(indexLevel < levelList.length - 1)
                        {
                            indexLevel++;
                            nextLevel(event);
                        }
                        else
                            stage.close();

                    }
                    System.out.println("right");
                    count_move ++;
                    break;

            }
            actualise(game.getChanges());
        });
    }

    private void initializeGame(String levelPath) throws URISyntaxException {
        game = new Main();
        File file = new File(levelPath);
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
    }
    @FXML
    private void resume(ActionEvent event)
    {
        try {
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            keyInput(event);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextLevel(ActionEvent event) {
            vbox.getChildren().clear();
            tabhbox = null;
            tabpane = null;
            game = new Main();
            File file = new File("src/main/resources/level/default/" + levelList[indexLevel]);
            game.nextLevel(file.getAbsolutePath());
            setTabpane();
            initializeAll();
            settabhbox();
            setvbox();
    }
public void switchToLevel(ActionEvent event) throws IOException, URISyntaxException {
            initializeGame(levelList[indexLevel]);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(vbox,500,500,Color.BLACK);
            stage.setScene(scene);
            keyInput(event);
            stage.show();
}
public void setLevelOne(ActionEvent event){level = "level0.txt";}
public void setLevelTwo(ActionEvent event){level = "level1.txt";}
public void setLevelThree(ActionEvent event){level = "level2.txt";}
public void setLevelFour(ActionEvent event){level = "level3.txt";}

public void playLevel(ActionEvent event)
{
    try {
        initializeGame(level);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(vbox,500,500,Color.BLACK);
        stage.setScene(scene);
        keyInput(event);
        stage.show();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void switchToLevelTwo(ActionEvent event)throws IOException{
        try {
            game = new Main();
            File file = new File("src/main/resources/level/default/" + levelList[indexLevel = 1]);
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
            scene = new Scene(vbox,500,500,Color.BLACK);
            stage.setScene(scene);
            //System.out.println("6");
            scene.setOnKeyPressed(event1 -> {
                switch (event1.getCode()) {
                    case ESCAPE:
                        try {
                            switchToPause(event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case UP:
                        if(game.makeMove("z"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }

                        }
                        System.out.println("up");
                        count_move ++;
                        break;
                    case DOWN:
                        if(game.makeMove("s"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }
                        }
                        System.out.println("down");
                        count_move ++;
                        break;
                    case LEFT:
                        if(game.makeMove("q"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }
                        }
                        System.out.println("left");
                        count_move ++;
                        break;
                    case RIGHT:
                        if(game.makeMove("d"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }

                        }
                        System.out.println("right");
                        count_move ++;
                        break;

                }
                actualise(game.getChanges());
            });
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToLevelThree(ActionEvent event)throws IOException{
        try {
            game = new Main();
            File file = new File("src/main/resources/level/default/" + levelList[indexLevel = 2]);
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
            scene = new Scene(vbox,500,500,Color.BLACK);
            stage.setScene(scene);
            //System.out.println("6");
            scene.setOnKeyPressed(event1 -> {
                switch (event1.getCode()) {
                    case ESCAPE:
                        try {
                            switchToPause(event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case UP:
                        if(game.makeMove("z"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }

                        }
                        System.out.println("up");
                        count_move ++;
                        break;
                    case DOWN:
                        if(game.makeMove("s"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }
                        }
                        System.out.println("down");
                        count_move ++;
                        break;
                    case LEFT:
                        if(game.makeMove("q"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }
                        }
                        System.out.println("left");
                        count_move ++;
                        break;
                    case RIGHT:
                        if(game.makeMove("d"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }

                        }
                        System.out.println("right");
                        count_move ++;
                        break;

                }
                actualise(game.getChanges());
            });
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToLevelFour(ActionEvent event)throws IOException{
        try {
            game = new Main();
            File file = new File("src/main/resources/level/default/" + levelList[indexLevel = 3]);
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
            scene = new Scene(vbox,500,500,Color.BLACK);
            stage.setScene(scene);
            //System.out.println("6");
            scene.setOnKeyPressed(event1 -> {
                switch (event1.getCode()) {
                    case ESCAPE:
                        try {
                            switchToPause(event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case UP:
                        if(game.makeMove("z"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }

                        }
                        System.out.println("up");
                        count_move ++;
                        break;
                    case DOWN:
                        if(game.makeMove("s"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }
                        }
                        System.out.println("down");
                        count_move ++;
                        break;
                    case LEFT:
                        if(game.makeMove("q"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }
                        }
                        System.out.println("left");
                        count_move ++;
                        break;
                    case RIGHT:
                        if(game.makeMove("d"))
                        {
                            System.out.println("1");
                            if(indexLevel < levelList.length - 1)
                            {
                                indexLevel++;
                                nextLevel(event);
                            }

                        }
                        System.out.println("right");
                        count_move ++;
                        break;

                }
                actualise(game.getChanges());
            });
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void logout(ActionEvent event){
        stage = (Stage)scene.getWindow();
        System.out.println("logout");
        stage.close();
    }

    private void saveLevel()
    {
        game.getSave();
    }

    @FXML
    private void quitAndSave(ActionEvent event) throws IOException {
        saveLevel();
        logout(event);
    }
    @FXML
    private void conTinu(ActionEvent event)
    {
        if(stage == null)
        {
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(vbox,500,500,Color.BLACK);
        }
        try {

            initializeGame(Main.getLastSave());
            stage.setScene(scene);
            keyInput(event);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

