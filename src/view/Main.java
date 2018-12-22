package view;/**
 * Created by lidor.rosencovich on 10/12/2018.
 */

import client.MyClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import server.MyServer;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            MyServer s = new MyServer(8000,10);
            s.start(new MyClient());

            BorderPane root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            Scene scene = new Scene(root, 400, 400);

            scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> System.out.println("Width: " + newSceneWidth));
            scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> System.out.println("Height: " + newSceneHeight));

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}
