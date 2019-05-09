package program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static Controller cref;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("program.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Graph");
        cref = loader.getController();
        primaryStage.setScene(new Scene(root, 1264, 783));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}