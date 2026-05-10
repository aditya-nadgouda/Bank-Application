import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility {

    // Here all the helper methods have been placed

    Scanner in = new Scanner(System.in);

    private int intInput ;
    private double doubleInput;
    private long longInput;

    public int takeIntInput(){
        while (true){
            try {
                intInput = in.nextInt();
                return intInput;
            }
            catch (InputMismatchException ref){
                System.out.println("Invalid input. Enter numeric value.");
                in.nextLine();

            }
        }
    }

    public double takeDoubleInput(){
        while (true){
            try {
                doubleInput = in.nextDouble();
                return doubleInput;
            }
            catch (InputMismatchException ref){
                System.out.println("Invalid input. Enter numeric value.");
                in.nextLine();

            }
        }
    }

    public long takeLongInput(){
        while (true){
            try {
                longInput = in.nextLong();
                return longInput;
            }
            catch (InputMismatchException ref){
                System.out.println("Invalid input. Enter numeric value.");
                in.nextLine();

            }
        }
    }


   public long takeValidMobileNumber(){
       while (true){
           try {
               longInput = in.nextLong();
               if (longInput >= 1000000000L && longInput <= 9999999999L){
                   return longInput;
               }
               else {
                   System.out.println("Invalid Mobile Number. Please Enter 10 digits");
               }
           }
           catch (InputMismatchException ref){
               System.out.println("Invalid input. Enter numeric value.");
               in.nextLine();

           }
       }
    }

    public int takeValidPin (){

        while (true){
            try {
                intInput = in.nextInt();
                if (intInput > 999 && intInput <= 9999){
                    return intInput;
                }
                else {
                    System.out.println("Invalid PIN. Enter a 4-digit PIN.");
                }
            }
            catch (InputMismatchException ref){
                System.out.println("Invalid input. Enter numeric value.");
                in.nextLine();

            }
        }
    }
}
