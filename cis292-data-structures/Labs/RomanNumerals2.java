import java.util.Scanner;

public class RomanNumerals2 {

    // Parallel arrays to hold corresponding roman and arabic numerals
    private static int[] arabic = { 1, 5, 10, 50, 100, 500, 1000 };
    private static char[] roman = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };

    // variable to store user's input numeral
    private String inputNumeral;

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        RomanNumerals2 test1 = new RomanNumerals2();

        boolean cont = true;
        while( cont ) {
            System.out.print("Enter a roman numeral or Q to quit: ");
            String tmpInput = input.next();
            tmpInput = tmpInput.toUpperCase();

            // test for quit option 
            if( tmpInput.equals( "Q" ) ) {
                cont = false;
                break;
            }

            // tests for valid input and sets inputNumeral
            test1.setInputNumeral( tmpInput );

            if( test1.getInputNumeral() == null ) {
                continue;
            }

            // calls displayResults using the result of search/conversion
            test1.displayResult( test1.toArabic( test1.getInputNumeral() ) );
        }

    }

    

    public void setInputNumeral( String tmpNum ) {
        // Verifies input is valid using switch statement
        // If valid, numeral is stored in inputNumeral
        // If invalid, inputNumeral is null

        boolean valid = false;
        
        for( int i = 0; i < tmpNum.length(); i++ ) {

            char romNum = tmpNum.charAt( i );

            switch( romNum ) {
                case 'I': 
                case 'V': 
                case 'X':
                case 'L':
                case 'C':
                case 'D':
                case 'M':
                    valid = true;
                    break;
            }

            if( valid == false)
                break;
        }
        
        if( valid == true ) {
            inputNumeral = tmpNum;
        } else {
            System.out.println("Not a Valid roman numeral.\n");
            inputNumeral = null;
        }

    }

    public String getInputNumeral() {
        return inputNumeral;
    }

    public int toArabic( String romNum ) {
        
        int convArabic = 0;

        // copy user's input to character array
        char[] rnArray = inputNumeral.toCharArray();
        int[] abArray = new int[ rnArray.length ];

        // find numeral in roman array and copy corresponding arabic value to abArray
        for( int i = 0; i < rnArray.length; i++ ) { // loops though inputNumeral char array
            for( int j = 0; j < roman.length; j++ ) { // loops through roman array        
                if( rnArray[ i ] == roman[ j ] ) { 
                    abArray[ i ] = arabic[ j ]; 
                }
            }
        }

        // calculate arabic conversion
        for( int i = 0; i < abArray.length; i++ ) {
            if( i < (abArray.length - 1) && abArray[ i ] < abArray[ i + 1 ] ) {
                convArabic += (abArray[ i + 1 ] - abArray[ i ] );
                i++;
                continue;
            } else { 
                convArabic += abArray[ i ];
            }
        }

        return convArabic;
    }

    public void displayResult( int arabic ) {
        System.out.println( getInputNumeral() + " is " + arabic + ".\n");
    }

}