import java.util.Scanner;

public class RomanNumerals {

    // Parallel arrays to hold corresponding roman and arabic numerals
    private final static int[] ARABIC = { 1, 5, 10, 50, 100, 500, 1000 };
    private final static char[] ROMAN = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };

    // variable to store user's input numeral
    private String inputNumeral;

    // Empty constructor
    public RomanNumerals() {}
    // Constructor accepting roman numeral string parameter
    public RomanNumerals( String romNum ) {

        setInputNumeral( romNum );

    }
    
    public static void main(String[] args) {

        System.out.printf("%nRoman Numeral Converter%n%n");

        // Provide examples
        System.out.printf("Example:%n");
        // Create and initialize example objects
        RomanNumerals example1 = new RomanNumerals( "MCXIV" );
        RomanNumerals example2 = new RomanNumerals( "CCCLIX" );
        RomanNumerals example3 = new RomanNumerals( "MDCLXVI" );
        // Output example objects conversion
        System.out.printf( "%s%n",  example1.toString() );
        System.out.printf( "%s%n",  example2.toString() );
        System.out.printf( "%s%n",  example3.toString() );
        System.out.println();

        // Create object for user input
        RomanNumerals test = new RomanNumerals();
        // Call menu prompt for user input object
        test.menuPrompt();

    }

    public void menuPrompt() {

        Scanner input = new Scanner(System.in);

        /* 
            Prompts the user for input or quit option.
            - If a valid numeral is input, program will display conversion.
            - If input is invalid, user is prompted until a valid input is received.
        */ 
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
            setInputNumeral( tmpInput );

            if( getInputNumeral() == null ) {
                continue;
            }

            // calls displayResults using the result of search/conversion
            System.out.printf("%s%n%n", toString() );
        }

        input.close();

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
            System.out.printf("Not a Valid roman numeral.%n%n");
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
            for( int j = 0; j < ROMAN.length; j++ ) { // loops through roman array        
                if( rnArray[ i ] == ROMAN[ j ] ) { 
                    abArray[ i ] = ARABIC[ j ]; 
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

    public String toString() {
        // returns string in format "III is 3"
        String str = String.format( "%s%s%d", getInputNumeral(), " is ", toArabic( getInputNumeral() ) );
        return str;
    }

}