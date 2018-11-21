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
    
    // temp values
    public static int[][] board1 = {
        {9,0,0,1,0,0,0,0,5},
        {0,0,5,0,9,0,2,0,1},
        {8,0,0,0,4,0,0,0,0},
        {0,0,0,0,8,0,0,0,0},
        {0,0,0,7,0,0,0,0,0},
        {0,0,0,0,2,6,0,0,9},
        {2,0,0,3,0,0,0,0,6},
        {0,0,0,2,0,0,9,0,0},
        {0,0,1,9,0,4,5,7,0},
};
    
    private static int[][] board2 = {
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

    public static void main( String[] args) {
        
        // fill board with hint values from file
        System.out.println( "Board to solve:" );
        printBoard();

        // call solve function and print if solution is found
        if ( solve() ) {
            System.out.println( "Board solution found:" );
            printBoard();
        } else {
            System.out.println("No solution found.");
        }
        

    } // end main()

    private static boolean solve() {

        // cycle through rows and columns to try solution values
        for( int row = 0; row < MAX_VALUE; row++ ) { // change 3 to MAX_VALUE
            for( int col = 0; col < MAX_VALUE; col++ ) { // change 3 to MAX_VALUE
                // test for empty cell 
                if( board[ row ][ col ] == EMPTY_VALUE ) {
                    // test if cell is empty and chose a value
                    for( int i = 1; i <= MAX_VALUE; i++ ) {
                        
                        //System.out.println("Trying " + i + " in row " + row + " col " + col); // debug
                        // check if cell is valid
                        if( isValid( row, col, i ) ) {
                            // assign index value to cell if valid
                            board[ row ][ col ] = i;
                            //printBoard(); //debug
                        
                            // check if cell leads to solution
                            if( solve() ) {
                                return true;
                            } else {
                                // reset cell if cell is invalid
                                board[ row ][ col ] = EMPTY_VALUE;
                            }
                        } 
                        
                    }
                    // if all values are invalid
                    return false;
                }
            }
        }
        // solution found
        return true;
    } // end solve()

    private static boolean isValid( int row, int col, int value ) {
        // returns true if cell is valid 
         return rowValid( row, value ) && colValid( col, value ) && subgridValid( row, col, value );
    }

    private static boolean rowValid( int row, int val ) {
        // test if cell value is already present in row
        for( int i = 0; i < MAX_VALUE; i++ ) {
            // return false if value is present
            if( board[ row ][ i ] == val ) {
                //System.out.println( "--Value " + val + " is not valid in row " + row + " ind " + i ); // debug
                return false;
            }
        }
        // return true if cell is valid within the specified row
        //System.out.println( "--Value " + val + " is valid in row " + row ); // debug
        return true;
    }

    private static boolean colValid( int col, int val ) {
        // test if cell value is already present in column
        for( int i = 0; i < MAX_VALUE; i++ ) {
            // return false if value is present
            if( board[ i ][ col ] == val ) {
                //System.out.println( "--Value " + val + " is not valid in col " + col ); // debug
                return false;
            }
        }
        // return true if cell is valid within the specified column
        //System.out.println( "--Value " + val + " is valid in col " + col ); // debug
        return true;
        
    }

    private static boolean subgridValid( int row, int col, int val ) {
        int subgridSize = 3;
        // define subgrid
        int subgridRowBegin = ( row / subgridSize ) * subgridSize;
        int subgridRowEnd = subgridRowBegin + subgridSize;
        int subgridColBegin = ( col / subgridSize ) * subgridSize;
        int subgridColEnd = subgridColBegin + subgridSize;

        // test if value is already present in subgrid
        for( int subRow = subgridRowBegin; subRow < subgridRowEnd; subRow++ ) {
            for( int subCol = subgridColBegin; subCol < subgridColEnd; subCol++ ) {
                // return false if value is present
                if( board[ subRow ][ subCol ] == val ) {
                    //System.out.println( "--Value " + val + " is not valid in subgrid[" + subgridRowBegin + "-" + subgridRowEnd + "][" +subgridColBegin +"-"+subgridColEnd+"]"  ); // debug
                    return false;
                }
            }
        }
        // return true if valid within specified subgrid
        //System.out.println( "--Value " + val + " is valid in subgrid[" + subgridRowBegin + "-" + subgridRowEnd + "][" +subgridColBegin +"-"+subgridColEnd+"]"  ); // debug
        return true;
    } // end subgridValid



    private static void printBoard() {

        for( int row = 0; row < MAX_VALUE; row++ ) {
            for( int col = 0; col < MAX_VALUE; col++ ) {
                System.out.printf( " %d", board[row][col] );
                // go to new line if at end of current row
                if( col == ( MAX_VALUE - 1 ) )
                    System.out.printf( "%n" );
            }
        }
        System.out.printf( "%n" );

    } // end printBoard()

} 

