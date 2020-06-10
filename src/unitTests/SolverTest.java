package unitTests;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import businessLogic.Solver;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class SolverTest {

    public int[][] solvedArrayTest = new int[9][9];


    @Test
    void returnSolvedSudokuTest() {

        Solver solverTest = new Solver();

        solvedArrayTest = new int[][]{
                {8, 1, 2, 3, 5, 4, 6, 7, 9},
                {9, 4, 3, 6, 7, 2, 1, 8, 5},
                {6, 7, 5, 8, 9, 1, 2, 4, 3},
                {1, 5, 4, 2, 3, 7, 8, 9, 6},
                {3, 8, 6, 9, 4, 5, 7, 1, 2},
                {7, 2, 9, 1, 6, 8, 5, 3, 4},
                {5, 3, 1, 7, 2, 9, 4, 6, 8},
                {4, 6, 8, 5, 1, 3, 9, 2, 7},
                {2, 9, 7, 4, 8, 6, 3, 5, 1}
        };

        assertArrayEquals(solvedArrayTest, solverTest.returnSolvedSudoku());


    }


    @Test
    void startSolving() {


    }

    @Test
    void searchForEmptyNumberInArray() {


    }

    @Test
    void searchForNumbersMethods() {


    }

    @Test
    void searchForPossibleNumbersInRow() {


    }

    @Test
    void searchForPossibleNumbersInColumn() {


    }

    @Test
    void expectedThreeByThreeTest() {

        Solver testSolver = new Solver();

        int indexNumber1 = 1;
        int indexNumber2 = 4;
        int indexNumber3 = 9;
        assertEquals(0, testSolver.getThreeByThree(indexNumber1), "for indexNumber >=0 & <=2 expected 0");
        assertEquals(3, testSolver.getThreeByThree(indexNumber2), "for indexNumber >=3 & <=5 expected 3");
        assertEquals(6, testSolver.getThreeByThree(indexNumber3), "for indexNumber > 5 expected 6");

    }
}