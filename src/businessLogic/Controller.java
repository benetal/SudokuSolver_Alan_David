package businessLogic;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


import org.json.JSONArray;
import org.json.JSONObject;
import javafx.scene.control.Button;


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


    // Button which loads the first Sudoku Sample
    public void loadFirstSudoku() throws IOException, URISyntaxException {

        AddScene("../layouts/sudokuGridLayout.fxml");
        getFirstSudokuArray();
        solveButton.setDisable(false);

    }

    // Button which loads the second Sudoku Sample
    public void loadSecondSudoku() throws IOException, URISyntaxException {

        AddScene("../layouts/sudokuGridLayout.fxml");
        getSecondSudokuArray();
        solveButton.setDisable(false);

    }


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

    // Adds the SudokuGridLayout to the BorderPane
    private void AddScene(String additionalScene) throws IOException {
        AnchorPane anchorpane;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(additionalScene));
        anchorpane = loader.load();
        borderPane.setCenter(anchorpane);

    }

    // Button which replaces the Sample Sudoku and solves it
    public void handleSolveAction() {

        getFilledSudokuArray();
        fillSolvedSudoku();

    }


    public void getFilledSudokuArray() {

        int row = 0;
        int column = 0;
        for (Node existingGrid : getPane().getChildren()) {
            if (existingGrid instanceof TextField) {
                currentSudoku[row][column] = Integer.parseInt(((TextField) existingGrid).getText());
                column++;
                if (column == 9) {
                    row++;
                    column = 0;
                }
            }

        }
    }

    // Solves and fills the Sudoku in the Grid
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
        System.out.println(solvedSudoku);
    }


    public void getFirstSudokuArray() throws URISyntaxException, IOException {

        JSONObject json = new JSONObject(Files.readString(Paths.get(getClass().getResource("../unsolvedSudokus/sudoku.json").toURI())));
        JSONArray sudokuMap = json.getJSONArray("sudokuMap");

        int row = 0;
        int column = 0;
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


    public void getSecondSudokuArray() throws URISyntaxException, IOException {

        JSONObject json = new JSONObject(Files.readString(Paths.get(getClass().getResource("../unsolvedSudokus/sudoku.json").toURI())));
        JSONArray sudokuMap2 = json.getJSONArray("sudokuMap2");

        int row = 0;
        int column = 0;

        for (Node gridToFill : getPane().getChildren()) {
            if (gridToFill instanceof TextField) {
                ((TextField) gridToFill).setText(String.valueOf(sudokuMap2.getJSONArray(row).getInt(column)));
                column++;
                if (column == 9) {
                    row++;
                    column = 0;
                }

            }


        }


    }


}