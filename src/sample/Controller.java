package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.scene.control.Button;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Controller {


    @FXML
    private BorderPane borderpan;

    @FXML
    private Button solveButton;



    @FXML
    public void handleLoadAction() throws IOException, URISyntaxException {

       /* FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        fileChooser.setTitle("Open Sudoku File");
        File selectFile = fileChooser.showOpenDialog(Main.getPrimaryStage());
        String filePath = selectFile.getPath();
        *//*sudoku.setFilePath(filePath);*//*
        sudoku.setFilePath(filePath);*/


        SwitchScene("9x9.fxml");


        JSONObject json = new JSONObject(Files.readString(Paths.get(getClass().getResource("sudoku.json").toURI())));
        JSONArray sudokuMap = json.getJSONArray("sudokuMap");


        System.out.println(sudokuMap.toString());


        Pane pane = getPane();

        // fill grid
        int row = 0, column = 0;
        for (Node node3 : pane.getChildren()) {
            if (node3 instanceof TextField) {
                ((TextField) node3).setText(String.valueOf(sudokuMap.getJSONArray(row).getInt(column)));
                column++;
                if (column == 9) {
                    row++;
                    column = 0;
                }

            }


        }


        solveButton.setDisable(false);

    }


    private Pane getPane() {
        AnchorPane anchorpane = null;
        for (Node node : borderpan.getChildren()) {
            if (node instanceof AnchorPane) {
                anchorpane = ((AnchorPane) node);
            }
        }
        // get Pane from AnchorPane
        Pane p = null;
        for (Node node2 : anchorpane.getChildren()) {
            if (node2 instanceof Pane) {
                p = ((Pane) node2);
            }

        }
        return p;
    }

    private void SwitchScene(String additinalScene) throws IOException {
        AnchorPane anchorpane = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(additinalScene));
        anchorpane = (AnchorPane) loader.load();
        borderpan.setCenter(anchorpane);
        Main.getPrimaryStage().setHeight(717);
        Main.getPrimaryStage().setWidth(768);
    }


    @FXML
    public void handleSolveAction() throws Exception {

        Pane pane = getPane();
        int[][] currentSudoku = new int[9][9];

        int row = 0, column = 0;
        for (Node node3 : pane.getChildren()) {
            if (node3 instanceof TextField) {
                currentSudoku[row][column] = Integer.parseInt(((TextField) node3).getText());
                column++;
                if (column == 9) {
                    row++;
                    column = 0;
                }
            }

        }


        Solver sudokuSolver = new Solver(currentSudoku);
        int[][] solvedSudoku;
        solvedSudoku = sudokuSolver.returnSolvedSudoku();


        row = 0;
        column = 0;

        for (Node node3 : pane.getChildren()) {
            if (node3 instanceof TextField) {
                ((TextField) node3).setText(String.valueOf(solvedSudoku[row][column]));
                column++;
                if (column == 9) {
                    row++;
                    column = 0;
                }
            }
        }
    }


    public void handleOpenAction() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        fileChooser.setTitle("Open Sudoku File");
        File selectFile = fileChooser.showOpenDialog(Main.getPrimaryStage());
        System.out.println(selectFile);

    }

/*    private void SwitchScene(String fxml) throws IOException {
        AnchorPane anchorpane = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(fxml));
        anchorpane = (AnchorPane) loader.load();
        borderpan.setCenter(anchorpane);
        Main.getPrimaryStage().setHeight(717);
        Main.getPrimaryStage().setWidth(768);


        }*/

}