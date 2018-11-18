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
        if ( solve( board1 ) )
            printBoard( board1 );
        else
            System.out.println("No solution found.");
        

    } // end main()

    private static boolean solve( int[][] board ) {

        // cycle through rows and columns to try solution values
        for( int row = 0; row < 1; row++ ) { // debug > change 1 to MAX_VALUE
            for( int col = 0; col < MAX_VALUE; col++) {
                // test if cell is empty and chose a value 
                if( board[ row ][ col ] == EMPTY_VALUE ) {
                    for( int i = 1; i <= MAX_VALUE; i++ ) {
                        // assign index value to cell 
                        board[ row ][ col ] = i;
                        System.out.println("Trying " + i + " in row " + row + " col " + col); // debug
                        printBoard(board);
                        // check if cell is valid and leads to a solution 
                        if( isValid( board, row, col ) && solve( board ) ) {
                            System.out.println( "Value " + board[row][col] + " is valid" ); // debug
                            return true;
                        } else {
                            // reset cell if cell is invalid
                            System.out.println( "Value " + board[row][col] + " is not valid" ); // debug
                            board[ row ][ col ] = EMPTY_VALUE;
                        }
                    }
                }

            }
        }

        // if all values are invalid
        return false;
    } // end solve()

    private static boolean isValid( int[][] board, int row, int col ) {
        // returns true if cell is valid 
        if( rowValid( board, row, col ) && colValid( board, row, col ) && subgridValid( board, row, col ) )
            return true;
        else
            return false;
    }

    private static boolean rowValid( int[][] board, int row, int col ) {
        // test if cell value is already present in row
        for( int i = 0; i < MAX_VALUE; i++ ) {
            // return false if value is present
            if( board[ row ][ i ] == board[ row ][ col ] ) {
                System.out.println( "--Value " + board[row][col] + " is not valid in row " + row + " ind " + i ); // debug
                return false;
            }
        }
        // return true if cell is valid within the specified row
        System.out.println( "--Value " + board[row][col] + " is valid in row " + row ); // debug
        return true;
    }

    private static boolean colValid( int[][] board, int row, int col ) {
        // test if cell value is already present in column
        for( int i = 0; i < MAX_VALUE; i++ ) {
            // return false if value is present
            if( board[ i ][ col ] == board[ row ][ col ] ) {
                System.out.println( "--Value " + board[row][col] + " is not valid in col " + col ); // debug
                return false;
            }
        }
        // return true if cell is valid within the specified column
        System.out.println( "--Value " + board[row][col] + " is valid in col " + col ); // debug
        return true;
        
    }

    private static boolean subgridValid( int[][] board, int row, int col ) {
        int subgridSize = 3;
        // define subgrid
        int subgridRowBegin = ( row / subgridSize ) * subgridSize;
        int subgridRowEnd = subgridRowBegin + subgridSize;
        int subgridColBegin = ( col / subgridSize ) * subgridSize;
        int subgridColEnd = subgridColBegin + subgridSize;

        // test if value is already present in subgrid
        for( int subRow = subgridColBegin; subRow < subgridRowEnd; subRow++ ) {
            for( int subCol = subgridColBegin; subCol < subgridColEnd; subCol++ ) {
                // return false if value is present
                if( board[ subRow ][ subCol ] == board[ row ][ col ] ) {
                    System.out.println( "--Value " + board[row][col] + " is not valid in subgrid[" + subgridRowBegin + "-" + subgridRowEnd + "][" +subgridColBegin +"-"+subgridColEnd+"]"  ); // debug
                    return false;
                }
            }
        }
        // return true if valid within specified subgrid
        return true;
    } // end subgridValid



    private static void printBoard( int[][] board ) {

        for( int row = 0; row < MAX_VALUE; row++ ) {
            for( int col = 0; col < MAX_VALUE; col++ ) {
                System.out.printf( " %d", board[row][col] );
                // go to new line if at end of current row
                if( col == ( MAX_VALUE - 1 ) )
                    System.out.printf( "%n" );
            }
        }
        System.out.printf( "%n" );

    }

} // end printBoard()

