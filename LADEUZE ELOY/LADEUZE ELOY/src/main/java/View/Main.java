package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Objects;


public class Main extends Application {

   /**
 * Méthode start qui va lancer le jeu dont la 1er scene qui permet de ce connecter avec un pseudo
 */
    
    
    @Override
    public void start(Stage stage) {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/connection.fxml")));
            Scene scene = new Scene(root,450, 500 , Color.BLACK);
            stage.setTitle("Baba Is You");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
