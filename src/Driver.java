import java.util.Scanner;

public class Driver {
   static Scanner in ;
   static Bank bank = new Bank("Bank of India","BOI0000876");
    public static void main(String[] args) {
       in = new Scanner(System.in);
        loginPage();
    }

    public static void loginPage(){

        boolean isTrue = true;

        while (isTrue) {
            System.out.println("--------Welcome to " + bank.getBankName() + " --------");
            System.out.println();
            System.out.println("1.Login \n2.Sign-Up \n0.Exit");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    bank.login();
                    break;

                case 2:
                    bank.signUp();
                    break;

                case 0:
                    isTrue=false;
                default:
                    System.out.println("Incorrect choice !!!");
            }
        }
    }
}