/*
    CIS292 Data Structures
    Lab 5 - Recursion with Backtracking
        Sudoku Puzzle Solver

    Lane, Brian
*/

public class SudokuPuzzle {

    // create and initialize board and related variables
    private static final int EMPTY_VALUE = 0;
    private static final int MAX_VALUE = 9;
    
    //private static int[][] board = new int[MAX_VALUE][MAX_VALUE];

    public static void main( String[] args) {
        
        // fill board with hint values from file

        // temp values
        int[][] board1 = {
            { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
            { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
            { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
            { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
            { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
            { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
            { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
            { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
            { 0, 0, 0, 0, 8, 0, 0, 7, 9 },
        };

        printBoard( board1 );

        // call solve function and print if solution is found
        /*if ( solve() )
            printBoard();
        else
            System.out.println("No solution found.");
        */

    }

    private static boolean solve( int[][] board ) {

        for( int row = 0; row < MAX_VALUE; row++ ) {
            for( int col = 0; col < MAX_VALUE; col++) {
                if( board[row][col] == EMPTY_VALUE ) {

                }

            }
        }
        return false;
    }

    

    private static void printBoard( int[][] board ) {

        for( int row = 0; row < MAX_VALUE; row++ ) {
            for( int col = 0; col < MAX_VALUE; col++ ) {
                System.out.printf( " %d", board[row][col] );
                // go to new line if at end of current row
                if( col == ( MAX_VALUE - 1 ) )
                    System.out.printf( "%n" );
            }
        }

    }

}

