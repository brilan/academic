/*
    Lane, Brian
    CIS292 Data Structures
    Lab 5 - Recursion with Backtracking
        Sudoku Puzzle Solver

    Input file: list as command argument or wait for prompt
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuPuzzle {
    
    // create and initialize board and related variables
    private final int EMPTY_VALUE = 0;
    private final int MAX_VALUE = 9;

    private int[][] board = new int[ MAX_VALUE ][ MAX_VALUE ];

    public static void main( String[] args) throws FileNotFoundException {

        // Create new puzzle
        SudokuPuzzle puzzle = new SudokuPuzzle();

        // fill board with hint values from file
        puzzle.setBoard( args );
        
        // print original board
        System.out.println( "Board to solve:" );
        puzzle.printBoard();

        // call solve function and print if solution is found or report no solution
        if ( puzzle.solve() ) {
            System.out.println( "Board solution found:" );
            puzzle.printBoard();
        } else {
            System.out.println("No solution found.");
        }
        
    } // end main()


    private void setBoard( String[] args ) {

        Scanner usrIn = new Scanner( System.in ); // for user input
        Scanner scan = null; // for reading file

        boolean validFile = false; // valid file flag
        int counter = 0; // to track invalid file entries

        // validate file and reprompt if not found
        while( !validFile ) {
            try {
                String filename = null;
                if( 0 < args.length ) {
                    filename = args[ 0 ];
                } else {
                    System.out.print( "Enter name of hint file: " );
                    filename = usrIn.nextLine();
                }
                scan = new Scanner( new File( filename ) );
                validFile = true;
            } catch( FileNotFoundException e ) {
                System.out.printf("File not found!%n%n");
            }

            // increment counter and exit program after three invalid file entries 
            counter++;
            if( counter > 2 ) {
                System.out.println( "File not found. Please try again." );
                System.exit(0);
            }
        }

        // read values from file into board
        for( int i = 0; i < 9; i++ ) {
            for( int j = 0; j < 9; j++) {
                board[ i ][ j ] = scan.nextInt();
            }
        }
        // close scanners
        usrIn.close();
        scan.close();
    }
    
    public boolean solve() {

        // cycle through rows and columns to try solution values
        for( int row = 0; row < MAX_VALUE; row++ ) { 
            for( int col = 0; col < MAX_VALUE; col++ ) {
                // test for empty cell 
                if( board[ row ][ col ] == EMPTY_VALUE ) {
                    // test if cell is empty and chose a value
                    for( int i = 1; i <= MAX_VALUE; i++ ) {
                        // check if cell is valid
                        if( isValid( row, col, i ) ) {
                            // assign index value to cell if valid
                            board[ row ][ col ] = i;
                                                  
                            // check if cell leads to solution
                            // uses recursion with backtracking
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

    private boolean isValid( int row, int col, int value ) {
        // returns true if cell is valid 
        return rowValid( row, value ) && colValid( col, value ) && subgridValid( row, col, value );
    }

    private boolean rowValid( int row, int val ) {
        // test if cell value is already present in row
        for( int i = 0; i < MAX_VALUE; i++ ) {
            // return false if value is present
            if( board[ row ][ i ] == val ) {
                return false;
            }
        }
        // return true if cell is valid within the specified row
        return true;
    }

    private boolean colValid( int col, int val ) {
        // test if cell value is already present in column
        for( int i = 0; i < MAX_VALUE; i++ ) {
            // return false if value is present
            if( board[ i ][ col ] == val ) {
                return false;
            }
        }
        // return true if cell is valid within the specified column
        return true;
        
    }

    private boolean subgridValid( int row, int col, int val ) {
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
                    return false;
                }
            }
        }
        // return true if valid within specified subgrid
        return true;
    } // end subgridValid


    public void printBoard() {

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

