package view;

import model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import javax.swing.*;
import java.util.List;

public class ShopNowGUI extends JFrame {
    private JList<String> productList;
    private DefaultListModel<String> listModel;
    private JTextArea cartArea;
    private Order order;
    private Client client;
    private List<Product> catalog;

    public ShopNowGUI() {
        setTitle("ShopNow - Carrito de Compras");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // cargar productos
        catalog = ProductLoader.loadProducts("D:\\UPTC\\Segundo semestre\\Programacion\\ShopNow\\ShopNow\\src\\Catalog.txt");
        LocalDateTime buyDate = LocalDateTime.now();
        order = new Order(101, buyDate, null);
        client = new Client("Roman Arango", "romanarango.ro@gmail.com");

        // panel de catálogo
        listModel = new DefaultListModel<>();
        for (Product p : catalog) {
            listModel.addElement(p.getProductId() + " - " + p.getProductName() + " ($" + p.getProductPrice() + ")");
        }
        productList = new JList<>(listModel);
        JScrollPane scrollCatalog = new JScrollPane(productList);

        JButton addButton = new JButton("Agregar al carrito");

        addButton.addActionListener((ActionEvent e) -> {
            int index = productList.getSelectedIndex();
            if (index != -1) {
                Product p = catalog.get(index);
                order.addProduct(p);
                cartArea.append(p.getProductName() + " $" + p.getProductPrice() + "\n");
            }
        });

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Catálogo de productos: "), BorderLayout.NORTH);
        leftPanel.add(scrollCatalog, BorderLayout.CENTER);
        leftPanel.add(addButton, BorderLayout.SOUTH);

        // panel de carrito
        cartArea = new JTextArea();
        cartArea.setEditable(false);
        JScrollPane scrollCart = new JScrollPane(cartArea);

        JPanel rightJPanel = new JPanel(new BorderLayout());
        rightJPanel.add(new JLabel("Carrito"), BorderLayout.NORTH);
        rightJPanel.add(scrollCart, BorderLayout.CENTER);

        JButton checkoutButton = new JButton("Finalizar Compra");
        checkoutButton.addActionListener((ActionEvent e) -> {
            cartArea.append(order.showOrder() + "\n");

            PaymentMethod metodo = selectPaymentMethod(client.getName(), order, buyDate);
            order.setPayMethod(metodo);

            // Capturar el texto impreso por processPayment()
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(buffer);
            PrintStream originalOut = System.out;
            System.setOut(ps);

            metodo.processPayment();

            System.out.flush();
            System.setOut(originalOut);

            String mensajePago = buffer.toString().trim();

            cartArea.append(mensajePago + "\n");
        });

        rightJPanel.add(checkoutButton, BorderLayout.SOUTH);

        // dividir pantalla
        add(leftPanel, BorderLayout.WEST);
        add(rightJPanel, BorderLayout.CENTER);
    }

    // método para elegir el método de pago con JOptionPane
    private PaymentMethod selectPaymentMethod(String clientName, Order order, LocalDateTime buyDate) {
        double totalAmount = order.TotalCost();

        String[] options = {"Tarjeta", "Transferencia bancaria", "Billetera digital"};
        int option = JOptionPane.showOptionDialog(
                this,
                "Seleccione un método de pago:",
                "Método de Pago",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        PaymentMethod payment = null;

        if (option == 0) {
            String cardNumberStr = JOptionPane.showInputDialog(this, "Ingrese el número de tarjeta:");
            String cvvStr = JOptionPane.showInputDialog(this, "Ingrese el código CVV:");
            int cardNumber = 0;
            int cvv = 0;
            if (cardNumberStr != null && cvvStr != null) {
                cardNumber = Integer.parseInt(cardNumberStr);
                cvv = Integer.parseInt(cvvStr);
            }
            payment = new Card(clientName, totalAmount, cvv, cardNumber, buyDate);
        } else if (option == 1) {
            String bankAccountStr = JOptionPane.showInputDialog(this, "Ingrese el número de cuenta bancaria:");
            String bankName = JOptionPane.showInputDialog(this, "Ingrese el nombre del banco:");
            int bankAccount = 0;
            if (bankAccountStr != null) {
                bankAccount = Integer.parseInt(bankAccountStr);
            }
            payment = new BankTransfer(clientName, totalAmount, bankAccount, bankName);
        } else if (option == 2) {
            String walletStr = JOptionPane.showInputDialog(this, "Ingrese el número de su billetera digital:");
            int walletNumber = 0;
            if (walletStr != null) {
                walletNumber = Integer.parseInt(walletStr);
            }
            payment = new DigitalWallet(clientName, totalAmount, walletNumber);
        } else {
            JOptionPane.showMessageDialog(this, "Opción inválida. Se usará método por defecto (Tarjeta).");
            payment = new Card(clientName, totalAmount, 0, 0, buyDate);
        }

        JOptionPane.showMessageDialog(this,
                "Total a pagar: $" + totalAmount + "\nMétodo: " + payment.getClass().getSimpleName(),
                "Resumen de Pago",
                JOptionPane.INFORMATION_MESSAGE);

        return payment;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShopNowGUI().setVisible(true);
        });
    }
}
