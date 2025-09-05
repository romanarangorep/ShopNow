import model.Client;
import model.Product;

public class Main {
    //instanciar=crear, en java crear tiene una palabra la cual es new
    public static void main(String[] args) {
        Client client1 = new Client("Andres Vargas","andrescamilo.vargas@uptc.edu.co");
        Product product1 = new Product("Computador", 1500000, 1607378);
        client1.showInfo();
        product1.showProduct();
       
    }

}
