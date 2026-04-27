
package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {
    private Scanner input = new Scanner(System.in);

    public Validator() {}

    public int validatorChoiceMenu(int min, int max) {
        int choice = 0;
        try {
            choice = input.nextInt(); 
            if(choice < min || choice > max) {
                choice = 0;
                return choice;
            }
        }
        catch(InputMismatchException e) {
            System.out.println("No strings allowed!");
            choice = 0;
            return choice;
        }
        finally { // always runs, used for resetting anything
            // do nothing
        }
        return choice; // returns 1, 2, 3 or 1, 2
    } 
    
} // end class
