import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private String bankName;
    private String ifsc;
    Account acc;

    ArrayList<Account> accList = new ArrayList<Account>();
    Utility utility = new Utility();
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
        System.out.println("============= LOGIN =============");

        System.out.println("Enter your Account Num : ");
        long acNum =utility.takeLongInput();
        System.out.println("Enter your PIN : ");
        int pin = utility.takeIntInput();
        if (validateLoginDetailsFromList(acNum,pin)){
            homePage();
        }
        else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    public boolean validateLoginDetailsFromList(Long acNum, int pin){

        acc = null;
        for (Account a : accList){
            if (a.getAcNum() == acNum && a.getAcPin() == pin){
                acc = a;
                return true;
            }
        }
        return false;
    }
//    Account(String achName,long acNum, long achMobNum, String achAddress, String branch, double acBal, int acPin){
    long acNum = 12345678L;
    public void signUp(){
        System.out.println("=================================");
        System.out.println("    ACCOUNT REGISTRATION");
        System.out.println("=================================");
        System.out.println("Enter Full Name       : ");
        String name = in.nextLine();
        System.out.println("Enter Mobile Number   : ");
        long mobNum = utility.takeValidMobileNumber();
        System.out.println("Enter Address         : ");
        String address = in.nextLine();
        System.out.println("Enter Branch Name     : ");
        String branch = in.nextLine();
        System.out.println("Enter 4-Digit PIN     : ");
        int pin = utility.takeValidPin();
        acc = new Account(name,++acNum,mobNum, address,branch,pin);
        accList.add(acc);
        System.out.println("=================================");
        System.out.println("  ACCOUNT CREATED SUCCESSFULLY");
        System.out.println("=================================");
        System.out.println("Account Number : "+acNum);
        System.out.println("Branch         : "+branch);
        System.out.println("=================================");
        System.out.println("Please save your account number for future reference.");
    }
    public void homePage(){
        boolean isTrue = true;
        System.out.println("Logged in successfully.\n" +
                "Welcome back, "+acc.getAchName());
        while (acc != null) {
            System.out.println();
            System.out.println("=================================");
            System.out.println("Account Number : "+acc.getAcNum()+"\nAccount Holder : "+acc.getAchName());
            System.out.println("=================================");
            System.out.println("           HOME MENU");
            System.out.println("=================================");

            System.out.println("1.View Account Details \n2.Update Account Details \n3.Withdrawal Services \n4.Deposit Money " +
                    "\n5.View Transaction history \n6.Delete Account \n0.Logout");
            System.out.println("=================================");
            System.out.println("Enter your choice : ");
            int choice = utility.takeIntInput();
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
                    System.out.println("Logged Out ! Thank you for banking with us.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select from menu.");
            }

        }
    }



    public void deleteAccount() {

        while (true) {
            System.out.println("=================================");
            System.out.println("        DELETE ACCOUNT");
            System.out.println("=================================");
            System.out.println("⚠ WARNING: Account deletion is permanent.\n" +
                    "Do you want to continue? (Y/N):");
            String c = in.nextLine();
            if (c.equalsIgnoreCase("Y")) {
                int n = 3;
                while (n>0){
                    System.out.println("Enter 4-Digit PIN : ");
                    int pin = utility.takeIntInput();
                    if (pin == acc.getAcPin()) {
                        System.out.println("=================================");
                        System.out.println("ACCOUNT DELETED SUCCESSFULLY\n" +
                                "Thank you for banking with us.");
                        System.out.println("=================================");

                        accList.remove(acc);
                        acc = null;
                        return;
                    } else {
                        System.out.println("Invalid PIN.");
                        n--;
                        if (n>0){
                            System.out.println("Remaining attempts : "+n);
                        }
                        else {
                            System.out.println("Maximum attempts exceeded.");
                            return;
                        }
                    }
                }
            } else if (c.equalsIgnoreCase("N")){
                return;
            }
            else {
                System.out.println("Invalid choice. Enter Y or N.");
            }
        }
    }

    public void depositMoney() {
//        public void createTransactionRecord (String trxStatus, String trxType, double trxAmt){
        System.out.println("=================================");
        System.out.println("         DEPOSIT MONEY");
        System.out.println("=================================");

        System.out.println("Enter amount to Deposit : ");
        double amt =utility.takeDoubleInput();
        if (amt > 0){
            acc.setAcBal(acc.getAcBal()+amt);
            System.out.println("=================================");
            System.out.println("      DEPOSIT SUCCESSFUL");
            System.out.println("=================================");
            System.out.println("Amount Deposited  : Rs. "+amt);
            System.out.println("Available Balance : Rs. "+acc.getAcBal());
            System.out.println("Status            : APPROVED");
            System.out.println("=================================");
            acc.createTransactionRecord("APPROVED","Deposit",amt);

        }
        else {
            System.out.println("Invalid amount entered !!!");
            acc.createTransactionRecord("DECLINED","Deposit",amt);

        }

    }

    public void withdrawMoneyOrCheckBalance() {

        while (true) {
            System.out.println("=================================");
            System.out.println("     WITHDRAWAL SERVICES");
            System.out.println("=================================");
            System.out.println("1.Balance Inquiry \n2.Cash Withdrawal \n0.Back");
            int choice = utility.takeIntInput();
            switch (choice) {
                case 1:
                    System.out.println("=================================");
                    System.out.println("        BALANCE INQUIRY");
                    System.out.println("=================================");
                    System.out.println("Enter 4-Digit PIN : ");
                    int pin = utility.takeIntInput();
                    if (pin == acc.getAcPin()) {
                        System.out.println("=================================");
                        System.out.println("Account number  : "+acc.getAcNum());
                        System.out.println("Account Balance : " + acc.getAcBal());
                        System.out.println("=================================");
                    } else {
                        System.out.println("Invalid PIN.");
                    }
                    break;

                case 2:
                    System.out.println("=================================");
                    System.out.println("       WITHDRAW MONEY");
                    System.out.println("=================================");

                    System.out.println("Enter Amount to withdraw : ");
                    double amt = utility.takeDoubleInput();
                    System.out.println("Enter 4-Digit Pin : ");
                    pin = utility.takeIntInput();
                    if (acc.getAcPin() == pin){
                        if (amt < acc.getAcBal()) {
                            System.out.println("Withdrawal successful.");
                            acc.setAcBal(acc.getAcBal() - amt);
                            System.out.println("=================================");
                            System.out.println("Account number  : "+acc.getAcNum());
                            System.out.println("Account Balance : " + acc.getAcBal());
                            System.out.println("=================================");
                            acc.createTransactionRecord("APPROVED", "Withdraw", amt);

                        } else {
                            System.out.println("Insufficient Balance !!!");
                            acc.createTransactionRecord("DECLINED", "Withdraw", amt);

                        }
                    }
                    else {
                        System.out.println("Invalid PIN.");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice. Please select from menu.");
            }
        }

    }

    public void updateAccountDetails() {

        while (true) {
            System.out.println("=================================");
            System.out.println("    UPDATE ACCOUNT DETAILS");
            System.out.println("=================================");
            System.out.println("Select what you want to update from below : ");
            System.out.println("1.Update Mobile Number \n2.Update Address \n3.Update PIN \n0.Back");
            System.out.println("Choice : ");
            int choice = utility.takeIntInput();
            switch (choice) {

                case 1 :
                    System.out.println("Enter new Mobile number : ");
                    long num = utility.takeValidMobileNumber();
                    acc.setAchMobNum(num);
                    System.out.println("Mobile number updated successfully.");
                    break;

                case 2 :
                    System.out.println("Enter new address : ");
                    String address  = in.nextLine();
                    acc.setAchAddress(address);
                    System.out.println("Address updated successfully.");
                    break;

                case 3 :
                    System.out.println("Enter your current PIN : ");
                    int pin = utility.takeValidPin();
                    if (pin==acc.getAcPin()){
                        System.out.println("Enter your new PIN : ");
                        int newPin = utility.takeIntInput();
                        acc.setAcPin(newPin);
                        System.out.println("PIN updated successfully.");
                    }
                    else {
                        System.out.println("Invalid PIN.");
                    }
                    break;

                case 0 :
                    return;
                default:
                    System.out.println("Invalid choice. Please select from menu.");
            }
        }
    }

    //    Account(String achName,long acNum, long achMobNum, String achAddress, String branch, double acBal, int acPin){

    public void showAccountDetails() {
        System.out.println("=================================");
        System.out.println("        ACCOUNT DETAILS");
        System.out.println("=================================");
        System.out.println("--------- CUSTOMER INFO ---------");
        System.out.println("Account Holder Name   : "+acc.getAchName());
        System.out.println("Account Number        : "+acc.getAcNum());
        System.out.println("Branch                : "+acc.getBranch());
        System.out.println("Mobile Number         : +91 "+acc.getAchMobNum());
        System.out.println("Address               : "+acc.getAchAddress());
        System.out.println("---------- BANK INFO ------------");
        System.out.println("Bank Name             : "+this.bankName);
        System.out.println("IFSC                  : "+this.ifsc);
        System.out.println("=================================");
    }

}
