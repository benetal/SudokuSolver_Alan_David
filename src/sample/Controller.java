package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {

    @FXML
    public Button sudokuSample;
    public VBox sudokuGrid;

//https://stackoverflow.com/questions/46328192/javafx-illegalargumentexception-is-already-set-as-root-of-another-scene

    public void handleButtonSudokuSample(ActionEvent actionEvent) throws IOException, URISyntaxException {
        System.out.println("hallo");
        Scene scene = sudokuSample.getScene();
        Parent root = getGrid();
        scene.setRoot(root);
//        sudokuSample.this.getGrid();
    }

//    public void solveSudoku(ActionEvent actionEvent) throws IOException, URISyntaxException {
//        System.out.println("hallo");
//        Scene scene = sudokuSample.getScene();
//        Parent root = getGrid();
//        scene.setRoot(root);
////        sudokuSample.this.getGrid();
//    }
//
//    private void changeScene(Pane pane) {
//        Scene scene = new Scene(pane);
//        Main.getPrimaryStage().setScene(new Scene(pane, 600, 450));
//    }


    public Pane getGrid() throws URISyntaxException, IOException {

        HashMap<Integer, TextField[]> sudokuCellReferenceArray = new HashMap<Integer, TextField[]>();

        VBox pane = new VBox();
        JSONObject json = new JSONObject(Files.readString(Paths.get(getClass().getResource("sudoku.json").toURI())));
        JSONArray sudokuMap = json.getJSONArray("sudokuMap");

        int rowLength = 0;
        int columnLength = 0;

        for (int row = 0; row < sudokuMap.length(); row++) {
            JSONArray sudokuRow = sudokuMap.getJSONArray(row);
            HBox currentRow = new HBox();
            TextField[] textFieldsOfCurrentRow = new TextField[sudokuRow.length()];
            rowLength = sudokuRow.length();
            for (int cell = 0; cell < rowLength; cell++) {
                TextField textInput = new TextField();
                textInput.setText(String.valueOf(sudokuRow.getInt(cell)));
                if(sudokuRow.getInt(cell) > 0) {
                    textInput.setDisable(true);
                }
          /*      if(sudokuRow.getInt(cell) == 0) {
                    textInput.clear();
                }*/
                textFieldsOfCurrentRow[cell] = textInput;
                HBox cellContainer = new HBox();
                cellContainer.getChildren().add(textInput);
//                currentRow.setSpacing(10);
                currentRow.getChildren().add(cellContainer);
            }
            columnLength++;
            sudokuCellReferenceArray.put(row, textFieldsOfCurrentRow);
            pane.getChildren().add(currentRow);
        }

        VBox buttonRow = new VBox();
        Button solveButton = new Button();
        solveButton.setText("Solve");
        int finalColumnLength = columnLength;
        int finalRowLength = rowLength;
        solveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("handleThisShit");

                int[][] sudokuArrayToSolve = new int[finalColumnLength][finalRowLength];

                for (int row = 0; row < sudokuCellReferenceArray.size(); row++) {
                    TextField[] textFieldsRow = sudokuCellReferenceArray.get(row);
                    for (int cell = 0; cell < textFieldsRow.length; cell++) {
                        sudokuArrayToSolve[row][cell] = Integer.parseInt(textFieldsRow[cell].getText());
                    }
                }

//                Sudoku sudoku = new Sudoku(sudokuArrayToSolve);
//                int[][] solvedSudoku = sudoku.getSolved();

                Solver davidSolver = new Solver(sudokuArrayToSolve);
                int [][] solvedSudoku = new int[0][];
                try {
                    solvedSudoku = davidSolver.returnSolvedSudoku();
                    System.out.println("solver is done ,"+davidSolver.isPossible);
                } catch (Exception e) {
                    e.printStackTrace();
                }


//                Solver sudokuSolver = new Solver(sudokuArrayToSolve);
//                try {
//                    sudokuSolver.startSolving();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                int[][] solvedSudoku = new int[0][];
//                try {
//                    solvedSudoku = sudokuSolver.returnSolvedSudoku();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }


                if (solvedSudoku == null) {
                        Button button = (Button)actionEvent.getSource();
                        Scene scene = button.getScene();
                        //TODO ADD ERROR MESSAGE TO CANVAS
                } else {
                    for (int row = 0; row < solvedSudoku.length; row++) {
                        int[] cells = solvedSudoku[row];
                        for (int cell = 0; cell < cells.length; cell++) {
                            sudokuCellReferenceArray.get(row)[cell].setText(String.valueOf(cells[cell]));
                        }
                    }
                }
            }

        });
        buttonRow.getChildren().

                add(solveButton);
        pane.getChildren().

                add(buttonRow);
        return pane;
    }
}



/*
    public String sudokuSampleLoader() throws URISyntaxException, IOException {
        VBox pane = new VBox();
        JSONObject json = new JSONObject(Files.readString(Paths.get(getClass().getResource("sudoku.json").toURI())));
        JSONArray sudokuMap = json.getJSONArray("sudokuMap");

        for (int row = 0; row < sudokuMap.length(); row++) {
            JSONArray sudokuRow = sudokuMap.getJSONArray(row);
            HBox currentRow = new HBox();
            for (int cell = 0; cell < sudokuRow.length(); cell++) {
                TextField textInput = new TextField();
                textInput.setText(String.valueOf(sudokuRow.getInt(cell)));
                HBox cellContainer = new HBox();
                cellContainer.getChildren().add(textInput);
//                currentRow.setSpacing(10);
                currentRow.getChildren().add(cellContainer);
            }
            pane.getChildren().add(currentRow);
        }
    return sudokuSampleLoader();
    }
}
*/

/**/


/*        VBox pane = new VBox();
        JSONObject json = new JSONObject(Files.readString(Paths.get(getClass().getResource("sudoku.json").toURI())));
        JSONArray sudokuMap = json.getJSONArray("sudokuMap");

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


    /*public sudokuSampleLoader()

    {
        VBox pane = new VBox();
        JSONObject json = new JSONObject(Files.readString(Paths.get(getClass().getResource("sudoku.json").toURI())));
        JSONArray sudokuMap = json.getJSONArray("sudokuMap");

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
        }
        return sudokuSampleLoader;
    }
}*/