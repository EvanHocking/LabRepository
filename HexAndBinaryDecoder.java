/*
* https://stackoverflow.com/questions/7569335/reverse-a-string-in-java used to figure out how to invert a string
*
 * */

import java.util.Scanner;
import java.lang.StringBuilder;

public class HexAndBinaryDecoder {
    public static void main(String[] args){

        int menuChoice = 0;
        String userCode; //User's input of what type of code they want to convert

        do{
            try {
                Scanner myScanner = new Scanner(System.in);
                System.out.println("Decoding Menu");
                System.out.println("-------------");
                System.out.println("1. Decode hexadecimal");
                System.out.println("2. Decode binary");
                System.out.println("3. Convert binary to hexadecimal");
                System.out.println("4. Quit");
                System.out.println(" ");
                System.out.println("Please enter an option: ");

                menuChoice = myScanner.nextInt();

                if (menuChoice == 1) {
                    System.out.println("Please enter the numeric string to convert: ");
                    userCode = myScanner.next();
                    System.out.println("Result: " + hexStringDecode(userCode));
                } else if (menuChoice == 2) {
                    System.out.println("Please enter the numeric string to convert: ");
                    userCode = myScanner.next();
                    System.out.println("Result: " + binaryStringDecode(userCode));
                } else if (menuChoice == 3) {
                    System.out.println("Please enter the numeric string to convert: ");
                    userCode = myScanner.next();
                    System.out.println("Result: " + binaryToHex(userCode));
                } else if (menuChoice == 4) {
                    System.exit(0);
                } else {
                    System.out.println("Please enter a integer 1-4.");
                }
            }
            catch (Exception generalError){
                System.out.println("Invalid input!");
            }
        }while(menuChoice != 4);

    }

    //converts a hex code string into a number (long)
    public static long hexStringDecode(String hex){
        long hexCode = 0;
        int baseExponent = hex.length()-1;

        hex.toLowerCase();

        for (int i = 0; i < hex.length(); i++){

            // ignores all x and 0 values that are found within the string
            if (hex.charAt(i) == '0' || hex.charAt(i) == 'x'){
                hexCode += 0;
                baseExponent--;
            }
            //determines the portion of hexcode's value
            else {
                switch(hex.charAt(i)){
                    case 'a':
                        hexCode += 10 * Math.pow(16, baseExponent);
                        break;
                    case 'b':
                        hexCode += 11 * Math.pow(16, baseExponent);
                        break;
                    case 'c':
                        hexCode += 12 * Math.pow(16, baseExponent);
                        break;
                    case 'd':
                        hexCode += 13 * Math.pow(16, baseExponent);
                        break;
                    case 'e':
                        hexCode += 14 * Math.pow(16, baseExponent);
                        break;
                    case 'f':
                        hexCode += 15 * Math.pow(16, baseExponent);
                        break;
                    default:
                        hexCode += Character.getNumericValue(hex.charAt(i)) * Math.pow(16, baseExponent);
                        break;

                }
                baseExponent--;
            }
        }
        return hexCode;
    }

    public static short hexCharDecode(String digit){
        return 0;
    }

    //converts a binary code string into a number (short)
    public static short binaryStringDecode(String binary){
        short binaryCode = 0;
        int binaryExponent = binary.length() - 1; //This is minus 1 because binary starts with base 2^0;

        for (int i = 0; i < binary.length() - 1; i++){

            //Skips the 0b start of the string if there is any
            if (binary.charAt(i) == '0' && binary.charAt(i + 1) == 'b'){
                i += 1;
                binaryExponent -=2;
            }
            else{
                binaryCode += Character.getNumericValue(binary.charAt(i)) * Math.pow(2, binaryExponent);
                binaryExponent--;
            }
        }

        return binaryCode;
    }

    //coverts a binary string into a hex string
    public static String binaryToHex(String binary){
        String hexCode = "";
        int stringLength = binary.length(); //allows me to start from the end of the array
        int sum = 0;
        int binaryOrder = 0; //keeps track of the end of a binary set

        String inverseBinary = new StringBuilder(binary).reverse().toString();

        for (int i = 0; i <= stringLength - 1; i++){
            binaryOrder++;
            if(binaryOrder == 1){
                sum += Character.getNumericValue(inverseBinary.charAt(i)) * 1;
            }
            else if(binaryOrder == 2){
                sum += Character.getNumericValue(inverseBinary.charAt(i)) * 2;
            }
            else if (binaryOrder == 3){
                sum += Character.getNumericValue(inverseBinary.charAt(i)) * 4;
            }
            else if (binaryOrder == 4){
                sum += Character.getNumericValue(inverseBinary.charAt(i)) * 8;
            }

            if (binaryOrder == 4 || i == binary.length() - 1){
                switch (sum){
                    case 10:
                        hexCode = hexCode.concat("a");
                        break;
                    case 11:
                        hexCode = hexCode.concat("b");
                        break;
                    case 12:
                        hexCode = hexCode.concat("c");
                        break;
                    case 13:
                        hexCode = hexCode.concat("d");
                        break;
                    case 14:
                        hexCode = hexCode.concat("e");
                        break;
                    case 15:
                        hexCode = hexCode.concat("f");
                        break;
                    default:
                        hexCode = hexCode.concat(Integer.toString(sum)); //converts the sum into a string and concats
                }
                binaryOrder = 0;
                sum = 0;
            }
        }

        String inverseHexCode = new StringBuilder(hexCode).reverse().toString(); // we must undo the reverse from earlier
        return inverseHexCode;
    }
}
