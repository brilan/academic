import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestInput {

    public static void main(String[] args) {

        Scanner scan = null;

        int[] testArray = new int[ 9 ];

        // open file
        try {

            scan = new Scanner( new File( args[ 0 ] ) );

        } catch( FileNotFoundException e ) {
            e.printStackTrace();
        }

        // read values from file
        for( int i = 0; i < testArray.length; i++ ) {

            testArray[ i ] = scan.nextInt();

        }

        for( int i = 0; i < testArray.length; i++ ) {
            System.out.print( testArray[ i ] + " " );
        }

    }

}