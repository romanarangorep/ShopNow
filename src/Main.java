import model.BankTransfer;
import model.Card;
import model.Client;
import model.DigitalWallet;
import model.Order;
import model.PaymentMethod;
import model.Product;
import model.ProductLoader;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int id;


        Client client1 = new Client("Roman Arango","roman.arango@uptc.edu.co");
        client1.showInfo();

        /*hacemos el catalogo y lo llenamos, es el "carrito"

        List<Product> catalog = new ArrayList<>();
        
        catalog.add(new Product("Computador", 1500000.0, 1));
        catalog.add(new Product("Celular", 2000000.0, 2));
        catalog.add(new Product("Reloj inteligente", 80000.0, 3));
        catalog.add(new Product("iPhone 16 Pro Max", 1000000.0, 4));
        catalog.add(new Product("PlayStation 5", 2500000.0, 5));
        catalog.add(new Product("Auriculares Bluetooth", 120000.0, 6));
        catalog.add(new Product("Teclado mecánico", 220000.0, 7));
        catalog.add(new Product("Monitor 24\"", 600000.0, 8));
        catalog.add(new Product("SSD 1TB", 320000.0, 9));
        catalog.add(new Product("Mouse gaming", 90000.0, 10));
        */
        LocalDateTime buyDate = LocalDateTime.now();
        Card payCard = new Card("Roman Arango",10000.0, 285, 105789320, buyDate);
        BankTransfer payBank = new BankTransfer("Roman Arango", 10000.0, 1057897634, "Davivienda");
        DigitalWallet payWallet = new DigitalWallet("Roman Arango", 10000.0, 1057894567);

        List<Product> catalog = ProductLoader.loadProducts("D:\\UPTC\\Segundo semestre\\Programacion\\ShopNow\\ShopNow\\src\\Catalog.txt");



        //mostramos el catalogo

        System.out.println("catalogo:");
        for (Product p : catalog) {
            System.out.println(p.getProductId() + " - " + p.getProductName() + " Price: " + p.getProductPrice());
        }

        //instanciamos el pedido

        
        Order order1 = new Order(1,buyDate,payCard);
        

        //do-while para que el cliente utilice el carrito 

          do {
            System.out.print("Ingresa el id del producto (0 para finalizar): ");
            id = sc.nextInt();

            //null significa que esta vacio

            Product seleccionado = null;
            for (Product c : catalog) {
                if (c.getProductId() == id) {
                    seleccionado = c;
                    break;
                }
            }

            //si esta diferente de null, se tiene un producto entonces se muestra el nombre del producto agregado

            if (seleccionado != null) {
                order1.addProduct(seleccionado);
                System.out.println(" Producto agregado: " + seleccionado.getProductName());
                //si es diferente de 0 y es null la opcion es invalida
            } else if (id != 0) {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
            //Si el usuario pone 0 se cierra el do-while

        } while (id != 0);

        sc.close();

        // mostramos el resumen del pedido
        order1.showOrder();
        order1.processOrder();
        

       
    }

}
