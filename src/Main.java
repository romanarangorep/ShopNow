import model.Client;
import model.Order;
import model.Product;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int id;


        Client client1 = new Client("Roman Arango","roman.arango@uptc.edu.co");
        client1.showInfo();

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

        System.out.println("catalogo:");
        for (Product p : catalog) {
            System.out.println(p.getProductId() + " - " + p.getProductName() + " Price: " + p.getProductPrice());
        }

        LocalDateTime buyDate = LocalDateTime.now();
        Order order1 = new Order(1,buyDate);

          do {
            System.out.print("Ingresa el id del producto (0 para finalizar): ");
            id = sc.nextInt();

            Product seleccionado = null;
            for (Product p : catalog) {
                if (p.getProductId() == id) {
                    seleccionado = p;
                    break;
                }
            }

            if (seleccionado != null) {
                order1.addProduct(seleccionado);
                System.out.println(" Producto agregado: " + seleccionado.getProductName());
            } else if (id != 0) {
                System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (id != 0);

        sc.close();

        // mostramos el resumen del pedido
        order1.showOrder();
        

       
    }

}
