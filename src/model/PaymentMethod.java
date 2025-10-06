package model;

public abstract class PaymentMethod {
    private String ownerName;
    private double amount;

    public PaymentMethod(String ownerName, double amount){
        this.amount=amount;
        this.ownerName=ownerName;
    }

    public String getOwner(){
        return ownerName;
    }

    public void setOwner(String ownerName){
        this.ownerName = ownerName;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public void processPayment(){

    }


}
