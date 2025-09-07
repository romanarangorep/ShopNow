import java.util.Date;
import model.Client;
import model.Order;
import model.Product;

public class Main {
    //instanciar=crear, en java crear tiene una palabra la cual es new
    public static void main(String[] args) {
        Client client1 = new Client("Roman Arango","roman.arango@uptc.edu.co");
        client1.showInfo();
        Date buyDate = new Date();
        //creamos los 5 productos para ejecutarlos
        Product product1 = new Product("Computador", 1500000, 167378);
        Product product2 = new Product("celular", 200000, 160598);
        Product product3 = new Product("Reloj inteligente", 80000, 160799);
        Product product4 = new Product("iphone 16 Pro Max", 1000000, 160998);
        Product product5 = new Product("playstation 5", 2500000, 160888);
        Order order1 = new Order(1,buyDate);
        order1.addProduct(product1);
        order1.addProduct(product2);
        order1.addProduct(product3);
        order1.addProduct(product4);
        order1.addProduct(product5);
        order1.showOrder();

       
    }

}
