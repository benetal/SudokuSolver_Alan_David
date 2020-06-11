package businessLogic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {


    private static Stage primaryStage = null;


    private static BorderPane rootLayout;


    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Sudoku Solver");
        Main.primaryStage.getIcons().add(new Image("image/sudokupic.png"));
        initRootLayout();

    }

    public void initRootLayout() {
        try {
            // Load rootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../layouts/rootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the rootLayout.fxml
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}