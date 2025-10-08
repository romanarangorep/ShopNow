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

        // Cargamos los productos desde el archivo
        List<Product> catalog = ProductLoader.loadProducts("D:\\UPTC\\Segundo semestre\\Programacion\\ShopNow\\ShopNow\\src\\Catalog.txt");

        //mostramos el catalogo
        System.out.println("catalogo:");
        for (Product p : catalog) {
            System.out.println(p.getProductId() + " - " + p.getProductName() + " Price: " + p.getProductPrice());
        }

        //instanciamos el pedido con un método de pago temporal (null)
        Order order1 = new Order(1, buyDate, null);

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

        // llamamos la nueva función para elegir método de pago usando el costo total del pedido
        PaymentMethod metodo = selectPaymentMethod(sc, client1.getName(), order1, buyDate);
        order1.setPayMethod(metodo); // ahora el pedido usa el método de pago elegido

        sc.close();

        // mostramos el resumen del pedido
        order1.showOrder();
        order1.processOrder();
    }

    // nueva función para seleccionar y procesar el método de pago (usa TotalCost del pedido)
    public static PaymentMethod selectPaymentMethod(Scanner sc, String clientName, Order order, LocalDateTime buyDate) {
        double totalAmount = order.TotalCost(); // se obtiene el total real del pedido
        System.out.println("\nSeleccione un método de pago:");
        System.out.println("1. Tarjeta");
        System.out.println("2. Transferencia bancaria");
        System.out.println("3. Billetera digital");
        System.out.print("Opción: ");
        int option = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        PaymentMethod payment = null;

        switch (option) {
            case 1:
                System.out.print("Ingrese el número de tarjeta: ");
                int cardNumber = sc.nextInt();
                System.out.print("Ingrese el código cvv: ");
                int cvv = sc.nextInt();
                sc.nextLine();
                payment = new Card(clientName, totalAmount, cvv, cardNumber, buyDate);
                break;
            case 2:
                System.out.print("Ingrese el número de cuenta bancaria: ");
                int bankAccount = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese el nombre del banco: ");
                String bankName = sc.nextLine();
                payment = new BankTransfer(clientName, totalAmount, bankAccount, bankName);
                break;
            case 3:
                System.out.print("Ingrese el número de su billetera digital: ");
                int walletNumber = sc.nextInt();
                sc.nextLine();
                payment = new DigitalWallet(clientName, totalAmount, walletNumber);
                break;
            default:
                System.out.println("Opción inválida. Se usará Tarjeta.");
                payment = new Card(clientName, totalAmount, 0, 0, buyDate);
                break;
        }

        System.out.println("\nProcesando pago");
        payment.processPayment();
        System.out.println("Pago completado con éxito");
        return payment;
    }
}
