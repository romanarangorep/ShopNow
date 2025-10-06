package model;

public class DigitalWallet extends PaymentMethod{
    private int walletId;

    public DigitalWallet(String ownerName, double amount, int walletId){
        super(ownerName, amount);
        this.walletId = walletId;
    }

    public int getWalletId(){
        return walletId;
    }

    public void setWalletId(int walletId){
        this.walletId = walletId;
    }

    @Override
    public void processPayment(){
        System.out.println("Procesando desde billetera digital, id: "+walletId);
    }

}
