package businessLogic;

public class Solver {
    public int row = 0;
    public int column = 0;
    int[] possibleNumbers = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    boolean isANumberZero;
    public int[][] attemptingToSolveArray;
    public int[][] solvedArray;
    boolean isPossible;


    //get Array from Controller
    public Solver(int[][] sudokuArrayToSolve) {
        attemptingToSolveArray = sudokuArrayToSolve;
    }

    public Solver() {

    }


    //return solved Sudoku Method
    public int[][] returnSolvedSudoku() {
        startSolving();
        return solvedArray;
    }

    //this Method starts the Solving and includes Backtracking
    public boolean startSolving() {
        int rowSolver = row;
        int columnSolver = column;
        int[] possibleNumbersWhileSolving = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        SearchForEmptyNumberInArray();
        if (isANumberZero) {
            possibleNumbers = possibleNumbersWhileSolving;
            possibleNumbersWhileSolving = SearchForNumbersMethods(new int[]{0, 0, 1, 0, 1, 0, 0, 1, 1, 1});
            for (int i = 0; i < 10; i++) { // if there is no empty field save Sudoku in solvedArray
                if (!isANumberZero) {
                    solvedArray = attemptingToSolveArray;
                } else if (possibleNumbersWhileSolving[i] == 0) { //put a valid number in empty Array
                    attemptingToSolveArray[row][column] = i + 1;
                    SearchForNumbersMethods(new int[]{0, 0, 1, 0, 1, 0, 0, 1, 1, 1});
                    startSolving();
                } else if (i == 9) { // if we cant fit a number then go to last Array and set Number to zero
                    row = rowSolver;
                    column = columnSolver;
                    attemptingToSolveArray[rowSolver][columnSolver] = 0;
                }
            }

        } else {
            return false;
        }
        return false;
    }

    //this method searches for the first empty Array in Sudoku
    public boolean SearchForEmptyNumberInArray() {
        outer:
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (attemptingToSolveArray[i][j] == 0) {
                    row = i;
                    column = j;
                    isANumberZero = true;
                    break outer;
                }
                isANumberZero = false;
            }
        }
        return isANumberZero;
    }


    //the following three methods in SearchForNumberMethods do the same thing as in real life we search in row/column and 3by3 grid for (a) possible number(s)
    public int[] SearchForNumbersMethods(int[] ints) {
        possibleNumbers = SearchForPossibleNumbersInRow(possibleNumbers);
        possibleNumbers = searchForPossibleNumbersInColumn(possibleNumbers);
        possibleNumbers = searchForPossibleNumbersInThreeByThree(getThreeByThree(row), getThreeByThree(column), possibleNumbers);
        return possibleNumbers;
    }

    public int[] SearchForPossibleNumbersInRow(int[] possibleNumbers) {
        //search in row
        for (int i = 0; i < 9; i++) {
            for (int j = 1; j < 10; j++) {
                if (attemptingToSolveArray[row][i] == j) {
                    possibleNumbers[j - 1] = 1;//Nummer J (z.b. 5) wird als index 4 gespeichert (deshalb J -1) weil index 4 wenn wir wieder auslesen +1 machen um die zahl zu bekommen
                    break;
                }
            }
        }
        return possibleNumbers;
    }

    public int[] searchForPossibleNumbersInColumn(int[] possibleNumbers) {
        //search in row //Nummer J (z.b. 5) wird als index 4 gespeichert (deshalb J -1) weil index 4 wenn wir wieder auslesen +1 machen um die zahl zu bekommen
        for (int i = 0; i < 9; i++) {
            for (int j = 1; j < 10; j++) {
                if (attemptingToSolveArray[i][column] == j) {
                    possibleNumbers[j - 1] = 1;
                    break;
//                    System.out.println("row: " + i + ", column " + column + " number" + j + " posbbilenumber" + possibleNumbers.toString());
                }
            }
        }
        return possibleNumbers;
    }

    // this is needed to make sure we search in the right 3by3 grid
    public int getThreeByThree(int indexNumber) {
        // index[3(row)][3(column)]
        if (indexNumber >= 0 && indexNumber <= 2) {
            return 0;
        } else if (indexNumber >= 3 && indexNumber <= 5) {
            return 3;
        } else {
            return 6;
        }
    }

    private int[] searchForPossibleNumbersInThreeByThree(int threeByThreeRow, int threeByThreeColumn, int[] possibleNumbers) {
        int loader;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                loader = attemptingToSolveArray[threeByThreeRow + i][threeByThreeColumn + j];
                if (loader != 0) {
                    for (int k = 1; k < 10; k++) {
                        if (loader == k) {
                            possibleNumbers[k - 1] = 1; //Nummer k (z.b. 5) wird als index 4 gespeichert (deshalb J -1) weil index 4 wenn wir wieder auslesen +1 machen um die zahl zu bekommen
                            break;
                        }
                    }
                }
            }
        }
        return possibleNumbers;
    }


}