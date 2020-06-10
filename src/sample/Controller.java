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
    private BorderPane borderPane;

    @FXML
    private Button solveButton;


    int[][] currentSudoku = new int[9][9];
    int row = 0;
    int column = 0;


    @FXML
    public void handleLoadAction() throws IOException, URISyntaxException {

        AddScene("9x9.fxml");
        getSudokuArray();
        solveButton.setDisable(false);

    }

       /* FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        fileChooser.setTitle("Open Sudoku File");
        File selectFile = fileChooser.showOpenDialog(Main.getPrimaryStage());
        String filePath = selectFile.getPath();
        *//*sudoku.setFilePath(filePath);*//*
        sudoku.setFilePath(filePath);*/




/*
        JSONObject json = new JSONObject(Files.readString(Paths.get(getClass().getResource("sudoku.json").toURI())));
        JSONArray sudokuMap = json.getJSONArray("sudokuMap");


        System.out.println(sudokuMap.toString());


        // fill grid
        int row = 0, column = 0;
        for (Node gridToFill : getPane().getChildren()) {
            if (gridToFill instanceof TextField) {
                ((TextField) gridToFill).setText(String.valueOf(sudokuMap.getJSONArray(row).getInt(column)));
                column++;
                if (column == 9) {
                    row++;
                    column = 0;
                }

            }


        }*/





    private Pane getPane() {

        AnchorPane anchorpane = null;
        for (Node borderPane : borderPane.getChildren()) {
            if (borderPane instanceof AnchorPane) {
                anchorpane = ((AnchorPane) borderPane);
            }
        }
        // get Pane from AnchorPane
        Pane pane = null;
        for (Node anchorPane : anchorpane.getChildren()) {
            if (anchorPane instanceof Pane) {
                pane = ((Pane) anchorPane);
            }

        }
        return pane;
    }

    private void AddScene(String additionalScene) throws IOException {
        AnchorPane anchorpane;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(additionalScene));
        anchorpane = loader.load();
        borderPane.setCenter(anchorpane);
        Main.getPrimaryStage().setHeight(700);
        Main.getPrimaryStage().setWidth(750);
    }


    @FXML
    public void handleSolveAction() {

        fillSudoku();
        fillSolvedSudoku();

        /*int[][] currentSudoku = new int[9][9];*/
    /*    int row = 0, column = 0;
        for (Node node3 : getPane().getChildren()) {
            if (node3 instanceof TextField) {
                currentSudoku[row][column] = Integer.parseInt(((TextField) node3).getText());
                column++;
                if (column == 9) {
                    row++;
                    column = 0;
                }
            }

        }*/


    /*    Solver sudokuSolver = new Solver(currentSudoku);
        int[][] solvedSudoku;
        solvedSudoku = sudokuSolver.returnSolvedSudoku();


        row = 0;
        column = 0;

        for (Node solvedGrid : getPane().getChildren()) {
            if (solvedGrid instanceof TextField) {
                ((TextField) solvedGrid).setText(String.valueOf(solvedSudoku[row][column]));
                column++;
                if (column == 9) {
                    row++;
                    column = 0;
                }
            }
        }*/
    }


/*    public void handleOpenAction() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        fileChooser.setTitle("Open Sudoku File");
        File selectFile = fileChooser.showOpenDialog(Main.getPrimaryStage());
        System.out.println(selectFile);

    }*/

    /*    private void SwitchScene(String fxml) throws IOException {
            AnchorPane anchorpane = null;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(fxml));
            anchorpane = (AnchorPane) loader.load();
            borderpan.setCenter(anchorpane);
            Main.getPrimaryStage().setHeight(717);
            Main.getPrimaryStage().setWidth(768);


            }*/
    public void fillSudoku() {

        /*int[][] currentSudoku = new int[9][9];*/

        int row = 0, column = 0;
        for (Node node3 : getPane().getChildren()) {
            if (node3 instanceof TextField) {
                currentSudoku[row][column] = Integer.parseInt(((TextField) node3).getText());
                column++;
                if (column == 9) {
                    row++;
                    column = 0;
                }
            }

        }
    }

    public void fillSolvedSudoku() {

        Solver sudokuSolver = new Solver(currentSudoku);
        int[][] solvedSudoku;
        solvedSudoku = sudokuSolver.returnSolvedSudoku();


        row = 0;
        column = 0;

        for (Node solvedGrid : getPane().getChildren()) {
            if (solvedGrid instanceof TextField) {
                ((TextField) solvedGrid).setText(String.valueOf(solvedSudoku[row][column]));
                column++;
                if (column == 9) {
                    row++;
                    column = 0;
                }
            }
        }
    }

    public void getSudokuArray() throws URISyntaxException, IOException {

        JSONObject json = new JSONObject(Files.readString(Paths.get(getClass().getResource("sudoku.json").toURI())));
        JSONArray sudokuMap = json.getJSONArray("sudokuMap");

        int row = 0, column = 0;
        for (Node gridToFill : getPane().getChildren()) {
            if (gridToFill instanceof TextField) {
                ((TextField) gridToFill).setText(String.valueOf(sudokuMap.getJSONArray(row).getInt(column)));
                column++;
                if (column == 9) {
                    row++;
                    column = 0;
                }

            }


        }


    }

}