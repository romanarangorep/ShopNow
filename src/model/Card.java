package model;
import java.time.LocalDate;

public class Card extends PaymentMethod{
    private short cvv;
    private int cardNumber;
    private LocalDate date;

    public Card(String ownerName, double amount, short cvv, int cardNumber, LocalDate date){
        super(ownerName, amount);
        this.cvv = cvv;
        this.cardNumber = cardNumber;
        this.date = date;
    }

    @Override
    public void processPayment() {
        String text = String.valueOf(cardNumber);
        int lenght = text.length();
        int start = Math.max(0, lenght - 4);
        String lastFour = text.substring(start);       
        System.out.println("Procesando pago con tarjeta terminada en: "+lastFour );
    }



}
