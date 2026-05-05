import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private String bankName;
    private String ifsc;
    Account acc;

    ArrayList<Account> accList = new ArrayList<Account>();
    Scanner in = new Scanner(System.in);
    Bank(String bankName, String ifsc){
        this.bankName=bankName;
        this.ifsc=ifsc;
    }
    public void setBankName(String bankName){
        this.bankName=bankName;
    }
    public void setIfsc(String ifsc){
        this.ifsc=ifsc;
    }
    public String getBankName(){
        return this.bankName;
    }
    public String getIfsc(){
        return this.ifsc;
    }
    public void login (){
        System.out.println("----- LOGIN -----");
        System.out.println("Enter your Account Num : ");
        long acNum = in.nextLong();
        System.out.println("Enter your PIN : ");
        int pin = in.nextInt();
        in.nextLine();

        if (matchLoginDetailsFromList(acNum,pin)){
            System.out.println("Logged In !!!");
            homePage();
        }
        else {
            System.out.println("Invalid account number OR Password !!!");
            System.out.println("No user found !!");        }
    }

    public boolean matchLoginDetailsFromList(Long acNum, int pin){

        acc = null;
        for (Account a : accList){
            if (a.getAcNum() == acNum && a.getAcPin() == pin){
                acc = a;
                return true;
            }
        }
        if (acc==null){

            return false;
        }

        return false;

    }
//    Account(String achName,long acNum, long achMobNum, String achAddress, String branch, double acBal, int acPin){
    long acNum = 12345678L;
    public void signUp(){
        System.out.println("----------------------------");
        System.out.println("Sign-Up here !!! ");
        System.out.println();
        System.out.println("Enter your Name : ");
        String name = in.nextLine();
        System.out.println("Enter your Mob Number : ");
        long mobNum = in.nextLong();
        in.nextLine();
        System.out.println("Enter your Address : ");
        String address = in.nextLine();
        System.out.println("Enter your branch name : ");
        String branch = in.nextLine();
        System.out.println("Enter your PIN : ");
        int pin = in.nextInt();
        acc = new Account(name,++acNum,mobNum, address,branch,pin);
        accList.add(acc);
        System.out.println("----------------------------");
        System.out.println("Account has been created successfully !");
        System.out.println("Your Account Number is : "+acNum);
        System.out.println("Please note it for future reference");
        login();
    }
    public void homePage(){
        boolean isTrue = true;
        while (isTrue) {
            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println("Welcome to the " + this.bankName);
            System.out.println("1.Account Details \n2.Update Details \n3.Withdraw Money \n4.Deposit Money " +
                    "\n5.Transaction history \n6.Delete account \n0.Logout");
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                    showAccountDetails();
                    break;
                case 2:
                    updateAccountDetails();
                    break;
                case 3:
                    withdrawMoneyOrCheckBalance();
                    break;
                case 4:
                    depositMoney();
                    break;
                case 5:
                    acc.showTransactionHistory();
                    break;
                case 6:
                    deleteAccount();
                    break;
                case 0:
                    return;
            }
            System.out.println("--------------------------------------");

        }
    }



    public void deleteAccount() {

        System.out.println("Confirm if you want to delete the account (Y/N) ?");
        String c = in.nextLine();
        if (c.equalsIgnoreCase("Y")){
            System.out.println("Enter your PIN : ");
            int pin = in.nextInt();
            if (pin==acc.getAcPin()){
                System.out.println("Account has been deleted successfully !!!");
                acc=null;
                Driver.loginPage();
            }
            else {
                System.out.println("Incorrect PIN !!!");
            }
        }
        else {
            homePage();
        }
    }

    public void depositMoney() {
//        public void createTransactionRecord (String trxStatus, String trxType, double trxAmt){

        System.out.println("Enter amount to Deposit : ");
        double amt = in.nextDouble();
        if (amt > 0){
            acc.setAcBal(acc.getAcBal()+amt);
            System.out.println("Amount has been deposited");
            System.out.println("Your current balance is : "+acc.getAcBal());
            acc.createTransactionRecord("Approved","Deposit",amt);

        }
        else {
            System.out.println("Invalid amount entered !!!");
            acc.createTransactionRecord("Declined","Deposit",amt);

        }

    }

    public void withdrawMoneyOrCheckBalance() {

        boolean isTrue = true;
        System.out.println("-----------------------------");
        System.out.println("1.Check Balance \n2.Withdraw Money \n0.Back");
        int choice = in.nextInt();
        switch (choice){
            case 1 :
                System.out.println("Enter your PIN : ");
                int pin = in.nextInt();
                if (pin==acc.getAcPin()){
                    System.out.println("Your available balance is : "+acc.getAcBal());
                }
                else {
                    System.out.println("Incorrect PIN !!!");
                }
                break;

            case 2 :
                System.out.println("Enter amount to withdraw : ");
                double amt = in.nextDouble();
                if (amt < acc.getAcBal()){
                    System.out.println("Money has been withdrawn successfully !!!");
                    acc.setAcBal(acc.getAcBal()-amt);
                    System.out.println("Your remaining balance is : "+acc.getAcBal());
                    acc.createTransactionRecord("Approved","Withdraw",amt);

                }
                else {
                    System.out.println("Insufficient Balance !!!");
                    acc.createTransactionRecord("Declined","Withdraw",amt);

                }

                break;

            case 0 :
                isTrue=false;

            default:
                System.out.println("Incorrect choice !!!");
        }

    }

    public void updateAccountDetails() {

        boolean isTrue = true;
        while (isTrue) {
            System.out.println("Update your Details : ");
            System.out.println("Select what you want to update from below : ");
            System.out.println("1.Update Mobile Num \n2.Update Address \n3.Update PIN \n0.Back");
            System.out.println("Choice : ");
            int choice = in.nextInt();
            switch (choice) {

                case 1 :
                    System.out.println("Enter new Mobile number : ");
                    long num = in.nextLong();
                    acc.setAchMobNum(num);
                    System.out.println("Mobile number has been updated !!!");
                    break;

                case 2 :
                    System.out.println("Enter new address : ");
                    String address  = in.nextLine();
                    acc.setAchAddress(address);
                    System.out.println("Address has been updated !!!");
                    break;

                case 3 :
                    System.out.println("Enter your current PIN : ");
                    int pin = in.nextInt();
                    if (pin==acc.getAcPin()){
                        System.out.println("Enter your new PIN : ");
                        int pin1 = in.nextInt();
                        acc.setAcPin(pin1);
                        System.out.println("Pin has been updated !!!");
                    }
                    else {
                        System.out.println("Incorrect PIN entered !!!");
                    }
                    break;

                case 0 :
                    isTrue=false;
                    homePage();

                default:
                    System.out.println("Incorrect option !!!");
            }
        }
    }

    //    Account(String achName,long acNum, long achMobNum, String achAddress, String branch, double acBal, int acPin){

    public void showAccountDetails() {
        System.out.println("------------------------");
        System.out.println("Account Details : ");
        System.out.println("Account Holder's Name : "+acc.getAchName());
        System.out.println("Account number : "+acc.getAcNum());
        System.out.println("Branch : "+acc.getBranch());
        System.out.println("Mobile Number : "+acc.getAchMobNum());
        System.out.println("Address : "+acc.getAchAddress());
        System.out.println("Bank Name : "+this.bankName);
        System.out.println("IFSC : "+this.ifsc);
        System.out.println("------------------------");
    }

}
