package View;

        import Presenter.Game;
        import java.io.*;
        import java.util.ArrayList;
        import java.util.Scanner;

        import Presenter.Score;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.Pane;
        import javafx.scene.layout.VBox;
        import javafx.scene.media.Media;
        import javafx.scene.media.MediaPlayer;
        import javafx.scene.media.MediaView;
        import javafx.scene.paint.Color;
        import javafx.stage.Stage;

        import java.io.File;
        import java.io.IOException;
        import java.net.URISyntaxException;


        import static Presenter.Game.getSprite;


public class Controller {
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Button playButton;
    private Stage stage;
    private static Scene scene;
    //pour romain, c'est pour permettre de logout dans le menu pause
    private static Scene pauseScene;
    private static  Pane[][] tabpane;
    private static int count_move = 0;
    @FXML
    private TextField MyTextField;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private TextArea myLabel;
    private String playerscore;

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
    public final Image textLavaImage = new Image(getClass().getResource("/sprite/lavatext.png").toURI().toString());
    public final Image killImage= new Image(getClass().getResource("/sprite/textkill.png").toURI().toString());
    public final Image lavaImage = new Image(getClass().getResource("/sprite/lava.gif").toURI().toString());
    private String fileName;

    private static int userLine = 0;

    private static Game game;

    //private Main tempGame;

    private static HBox[] tabhbox;
    @FXML
    private static VBox vbox = new VBox();

    @FXML
       /**
 * Méthode pour rejoindre le Menu après s'être connecter avec son pseudo
 */
    private void inputUser(ActionEvent event) throws IOException, URISyntaxException {
        String name = MyTextField.getText();
        game = new Game(name);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
    public void setMusic() throws URISyntaxException {
        Media media = new Media(getClass().getResource("/music/AdhesiveWombat - Night Shade.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView view = new MediaView(mediaPlayer);
    }
    public void cutMusic() {
        mediaPlayer.pause();
    }
    public Controller() throws URISyntaxException {
    }
        /**
 * Cette méthode va charger les Hbox en fonction des tableaux
 */
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
        
       /**
 * setTabpane va ajouter des Pane dans les hbox pour pouvoir placer nos objets de dans
 */
    public void setTabpane()
    {
        int length = Game.getLength();
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
        /**
 * permet de retourner au menu
 */

   public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
        /**
 * permet d'aller au settings
 */
    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Settings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
        /**
 * permet d'aller au menu de choix de niveau
 */
    public void switchToMapChoice(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mapChoice.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
    public void goToScore(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Score.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
    public void Info(String fileName)
    {
        this.fileName = fileName;
    }

    public void loadScore(ActionEvent event) {
        File file = new File("/Users/romaineloy/new/src/main/resources/users/scoreboard.txt");

        try (FileInputStream fis = new FileInputStream(file)) {
            Scanner sc = new Scanner(fis);
            while(sc.hasNextLine()) {
             playerscore = ""+ sc.nextLine()+"\n";
                myLabel.appendText(playerscore);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        /**
 * permet d'aller au menu pour choisir entre aller à la campagne ou de reprendre une partie ou de choisir son niveeau
 */
    public void switchToLetsGame(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/letsgame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,450, 500, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
        /**
 * permet de faire pause en partie
 */
    public void switchToPause(ActionEvent event)throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Pause.fxml"));
        Parent root = loader.load();
        pauseScene = new Scene(root, 450, 500, Color.BLACK);
        stage.setScene(pauseScene);
        stage.show();
    }
        /**
 * lance la partie
 */
    public void switchToGame(ActionEvent event){
        try {
            Score score = new Score();
            score.start();
            initializeGame(game.getLevel());
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
                case UP:
                    if(game.makeMove("z"))
                    {
                        Score.stop();
                        System.out.println("gagné");
                        if(!(game.nextLevel()))
                            stage.close();
                        else
                            refresh(event);
                    }
                    System.out.println("up");
                    count_move ++;
                    break;
                case DOWN:
                    if(game.makeMove("s"))
                    {
                        Score.stop();
                        System.out.println("gagné");
                        if(!(game.nextLevel()))
                            stage.close();
                        else
                            refresh(event);
                    }
                    System.out.println("down");
                    count_move ++;
                    break;
                case LEFT:
                    if(game.makeMove("q"))
                    {
                        Score.stop();
                        System.out.println("gagné");
                        if(!(game.nextLevel()))
                            stage.close();
                        else
                            refresh(event);
                    }
                    System.out.println("left");
                    count_move ++;
                    break;
                case RIGHT:
                    if(game.makeMove("d"))
                    {
                        Score.stop();
                        System.out.println("gagné");
                        if(!(game.nextLevel()))
                            stage.close();
                        else
                            refresh(event);
                    }
                    System.out.println("right");
                    count_move ++;
                    break;
            }
            actualise(game.getChanges());
        });
    }


    private void initializeGame(String fileName) throws URISyntaxException {
        game.makeTheGame(fileName);
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
        /**
 * permet de retourner en partie après avoir fait pause
 */
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

    public void refresh(ActionEvent event) {
            vbox.getChildren().clear();
            tabhbox = null;
            tabpane = null;
            setTabpane();
            initializeAll();
            settabhbox();
            setvbox();
    }
public void switchToLevel(ActionEvent event) throws IOException, URISyntaxException {
            initializeGame(game.getLevel());
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(vbox,500,500,Color.BLACK);
            stage.setScene(scene);
            keyInput(event);
            stage.show();
}
    public void setLevelOne(ActionEvent event)
    {
        game.setLevel(0);
    }
    public void setLevelTwo(ActionEvent event) throws IOException {
        if(!game.setLevel( 1))
        {
            switchToMapChoice(event);
            System.out.println("you haven't finished the level one yet");
        }
    }
    public void setLevelThree(ActionEvent event) throws IOException {
    if(!game.setLevel(2))
    {
        switchToMapChoice(event);
        System.out.println("you haven't finished the level two yet");
    }
}
    public void setLevelFour(ActionEvent event) throws IOException {
    if(!game.setLevel( 3))
    {
        switchToMapChoice(event);
        System.out.println("you haven't finished the level three yet");
    }
    }
    public void setLevelFive(ActionEvent event) throws IOException {
        if(!game.setLevel(4))
        {
            switchToMapChoice(event);
            System.out.println("you haven't finished the level four yet");
        }
    }
    public void setLevelSix(ActionEvent event) throws IOException {
        if(!game.setLevel(5))
        {
            switchToMapChoice(event);
            System.out.println("you haven't finished the level five yet");
        }
    }
    public void setLevelSeven(ActionEvent event) throws IOException {
        if(!game.setLevel(6))
        {
            switchToMapChoice(event);
            System.out.println("you haven't finished the level six yet");
        }
    }
    public void setLevelHeight(ActionEvent event) throws IOException {
        if(!game.setLevel(7))
        {
            switchToMapChoice(event);
            System.out.println("you haven't finished the level seven yet");
        }
    }

public void playLevel(ActionEvent event)
{
    try {
        initializeGame(game.getLevel());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(vbox,500,500,Color.BLACK);
        stage.setScene(scene);
        keyInput(event);
        stage.show();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
        /**
 * permet de quitter 
 */
    public void logout(ActionEvent event){
        stage = (Stage)scene.getWindow();
        System.out.println("logout");
        stage.close();
    }

    private void saveLevel()
    {
        game.getSave();
    }
/**
 * Sauvegarde le niveau et quitte le jeu
 */
    @FXML
    private void quitAndSave(ActionEvent event) throws IOException {
        Score.saveScore();
        Score.endScore();
        saveLevel();
        stage = (Stage)pauseScene.getWindow();
        System.out.println("logout");
        stage.close();
    }
        /**
 * permet de reprendre là ou on avait quitté et sauvergarder
 */
    @FXML
    private void continu(ActionEvent event)
    {
        if(stage == null)
        {
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(vbox,500,500,Color.BLACK);
        }
        try {

            initializeGame(Game.getLastSave());
            stage.setScene(scene);
            keyInput(event);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



