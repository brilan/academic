import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestInput {

    public static void main(String[] args) 
        throws FileNotFoundException {

        Scanner scan = new Scanner( System.in );

        int[][] testArray = new int[ 9 ][ 9 ];

        boolean validFile = false; // valid file flag
        int counter = 0; // to track invalid file entries
        while( !validFile ) {
            try {
                if( 0 < args.length ) {
                    scan = new Scanner( new File( args[ 0 ] ) );
                } else {
                    System.out.print( "Enter name of hint file: " );
                    String filename = scan.nextLine();

                    scan = new Scanner( new File( filename ) );
                }
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

        // read values from file
        for( int i = 0; i < 9; i++ ) {
            for( int j = 0; j < 9; j++) {
                testArray[ i ][ j ] = scan.nextInt();
            }
        }

        // print values
        System.out.println(); // insert blank line
        for( int i = 0; i < 9; i++ ) {
            for( int j = 0; j < 9; j++) {
                System.out.print( testArray[ i ][ j ] + " " );
                if( j == 8 )
                    System.out.println();
            }
        }

    }

}