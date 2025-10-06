package model;


public class BankTransfer extends PaymentMethod{
    private int accountNumber;
    private String bankName;

    public BankTransfer(String ownerName, double amount, int accountNumber, String bankName){
        super(ownerName, amount);
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }

   @Override
   public void processPayment() {
       String text = String.valueOf(accountNumber);
       int lenght = text.length();
       int start = Math.max(0, lenght-4);
       String lastFour = text.substring(start);
       System.out.println("procesando desde cuenta con ultimos digitos: "+lastFour+",  de banco: "+ bankName);
   } 


}
