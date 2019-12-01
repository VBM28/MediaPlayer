package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(root, 600, 400);


        primaryStage.setTitle("Player");

        //double click to open/close full screen mode
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    if(primaryStage.isFullScreen()){
                        primaryStage.setFullScreen(false);
                    } else if(!primaryStage.isFullScreen()){
                        primaryStage.setFullScreen(true);
                    }
                }
            }
        });
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
