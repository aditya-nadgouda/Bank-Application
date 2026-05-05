import java.util.ArrayList;

public class Account {

//Account holder name, Account number, contact number, account holder's address, branch, account balance and Pin.
    private String achName;
    private long acNum;
    private long achMobNum;
    private String achAddress;
    private String branch;
    private double acBal;
    private int acPin;

    Transactions trx;

    ArrayList<Transactions> trxList = new ArrayList<Transactions>();

    Account(String achName,long acNum, long achMobNum, String achAddress, String branch, int acPin){
        this.achName=achName;
        this.acNum=acNum;
        this.achMobNum=achMobNum;
        this.achAddress=achAddress;
        this.branch=branch;
        this.acPin=acPin;
    }

//    Transactions (long trxId, String trxStatus , String trxType , double trxAmount){

    long trxId = 1111111111L;
    public void createTransactionRecord (String trxStatus, String trxType, double trxAmt){
        trx = new Transactions(++trxId,trxStatus,trxType,trxAmt,this.acBal);
        trxList.add(trx);
    }

//
//    public void showTransactionHistory (){
//        int srNum = 1;
//        System.out.println("Sr.Num. | Trx Status | Trx Type | Trx Amount | Remaining Balance | Date and Time ");
//        for (Transactions transaction : trxList){
//            System.out.println((srNum++) +" | "+transaction.getTrxStatus()+" | "+transaction.getTrxType()+" | "+transaction.getTrxAmount()+" | "+transaction.getTrxAmount()+" | "+transaction.getDateTime());
//        }
//    }

    public void showTransactionHistory() {
        int srNum = 1;

        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-15s %-15s %-15s %-20s %-25s\n",
                "Sr.No", "Status", "Type", "Amount", "Balance", "Date & Time");
        System.out.println("------------------------------------------------------------------------------------------");

        for (Transactions transaction : trxList) {
            System.out.printf("%-8d %-15s %-15s %-15.2f %-20.2f %-25s\n",
                    srNum++,
                    transaction.getTrxStatus(),
                    transaction.getTrxType(),
                    transaction.getTrxAmount(),
                    transaction.getRemBal(), // replace with balance if you have it
                    transaction.getDateTime()
            );
        }

        System.out.println("------------------------------------------------------------------------------------------");
    }

    // Setters
    public void setAchName(String name){
        this.achName=name;
    }
    public void setAcNum(long acNum){
        this.acNum=acNum;
    }
    public void setAchMobNum (long mobNum){
        this.achMobNum = mobNum;
    }
    public void setAchAddress (String achAddress){
        this.achAddress=achAddress;
    }
    public void setBranch (String branch){
        this.branch = branch;
    }
    public void setAcBal(double money){
        this.acBal=money;
    }
    public void setAcPin(int pin){
        this.acPin=pin;
    }

    //Getters
    public String getAchName(){
        return this.achName;
    }
    public long getAcNum(){
        return  this.acNum;
    }
    public long getAchMobNum(){
        return this.achMobNum;
    }
    public String getAchAddress(){
        return this.achAddress;
    }
    public String getBranch (){
        return this.branch;
    }
    public double getAcBal(){
       return this.acBal;
    }
    public int getAcPin(){
        return this.acPin;
    }

}
