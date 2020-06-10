package test;

import org.junit.jupiter.api.Test;
import sample.Solver;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class SolverTest {



    @Test
        void returnSolvedSudoku() {




    }


    @Test
    void startSolving() {



    }

    @Test
    void searchForEmptyNumberInArray() {

    }

    @Test
    void searchForNumbersMethods() {
        Solver testSolver = new Solver();

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