import java.time.LocalDateTime;

public class Transactions {

    private long trxId;
    private   String trxStatus;
    private    String trxType;
    private   double trxAmount;
    private LocalDateTime dateTime;
    private double remBal;


    Transactions (long trxId, String trxStatus , String trxType , double trxAmount, double remBal){
        this.trxId= trxId;
        this.trxStatus = trxStatus;
        this.trxType=trxType;
        this.trxAmount=trxAmount;
        this.dateTime = LocalDateTime.now();
        this.remBal=remBal;
    }

    public double getRemBal(){
        return this.remBal;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }
    public long getTrxId() {
        return trxId;
    }

    public void setTrxId(long trxId) {
        this.trxId = trxId;
    }

    public String getTrxStatus() {
        return trxStatus;
    }

    public void setTrxStatus(String trxStatus) {
        this.trxStatus = trxStatus;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public double getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(double trxAmount) {
        this.trxAmount = trxAmount;
    }

}
