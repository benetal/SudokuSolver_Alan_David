package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;


import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {

    private static Stage primaryStage = null;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        setPrimaryStage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return Main.primaryStage;
    }

    private void setPrimaryStage(Stage pStage) {
        Main.primaryStage = pStage;
    }
}


       /* VBox pane = new VBox();
        JSONObject json = new JSONObject(Files.readString(Paths.get(getClass().getResource("sudoku.json").toURI())));
            JSONArray sudokuMap = json.getJSONArray("sudokuMap");
            primaryStage.setTitle("Sudoku");

            for (int row = 0; row < sudokuMap.length(); row++) {
                JSONArray sudokuRow = sudokuMap.getJSONArray(row);
                HBox currentRow = new HBox();
                for (int cell = 0; cell < sudokuRow.length(); cell++) {
                    TextField textInput = new TextField();
                    textInput.setText(String.valueOf(sudokuRow.getInt(cell)));
                    HBox cellContainer = new HBox();
                    cellContainer.getChildren().add(textInput);
                    currentRow.setSpacing(10);
                    currentRow.getChildren().add(cellContainer);
                }
            pane.getChildren().add(currentRow);
        }*/
/*
        HBox buttonRow = new HBox();
        buttonRow.getChildren().add(new Button("Lösen"));
        pane.getChildren().add(buttonRow);*/

/*
    Scene scene = new Scene(scene);
        primaryStage.setScene(new Scene(scene*/
/*pane*//*
,600,450));

                primaryStage.show();

                }


                }*/
