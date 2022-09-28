package productOptimiser;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;


public class Main extends Application {

    public static Stage initialStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("opDash.fxml"));
        primaryStage.setTitle("Product Optimiser");
        primaryStage.setScene(new Scene(root, 1400, 750));
        primaryStage.setResizable(false);
        primaryStage.show();
        initialStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        Stage stage = initialStage;
        return stage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}