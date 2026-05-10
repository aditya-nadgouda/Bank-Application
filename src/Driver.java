import java.util.Scanner;
import java.util.Stack;

public class Driver {
   static Scanner in ;
   static Bank bank = new Bank("BANK OF INDIA","BOI0000876");
   static Utility utility = new Utility();
    public static void main(String[] args) {
       in = new Scanner(System.in);
        loginPage();
    }

    public static void loginPage(){

        boolean isTrue = true;

        while (isTrue) {
            System.out.println("=================================");
            System.out.println("    WELCOME TO " + bank.getBankName());
            System.out.println("=================================");
            System.out.println("1.Login \n2.Sign-Up \n0.Exit");
            System.out.println("=================================");
            System.out.println("Enter your choice : ");
            int choice = utility.takeIntInput();
            switch (choice) {
                case 1:
                    bank.login();
                    break;

                case 2:
                    bank.signUp();
                    break;

                case 0:
                    isTrue=false;
                    System.out.println("Thank you for banking with us!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select from menu.");
            }
            System.out.println("=================================");
        }
    }
}