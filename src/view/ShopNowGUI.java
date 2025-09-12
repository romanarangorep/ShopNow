package view;

import model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.border.Border;

import java.util.List;

public class ShopNowGUI extends JFrame {
    private JList<String> productList;
    private DefaultListModel<String> listModel;
    private JTextArea cartArea;
    private Order order;
    private Client client;
    private List<Product> catalog;

    public ShopNowGUI(){
        setTitle("ShopNow - Carrito de Compras");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //cargar productos
        catalog = ProductLoader.loadProducts("D:\\\\UPTC\\\\Segundo semestre\\\\Programacion\\\\ShopNow\\\\ShopNow\\\\src\\\\Catalog.txt");
        LocalDateTime buyDate = LocalDateTime.now();
        order = new Order(101, buyDate);
        Client client1 = new Client("Roman Arango","romanarango.ro@gmail.com");

        //panel de catálogo
        listModel = new DefaultListModel<>();
        for (Product p : catalog) {
            listModel.addElement(p.getProductId()+" - "+p.getProductName()+" ($"+p.getProductPrice()+")");            
        }
        productList = new JList<>(listModel);
        JScrollPane scrollCatalog = new JScrollPane(productList);

        JButton addButton = new JButton("Agregar al carrito");

        addButton.addActionListener((ActionEvent e) -> {
            int index = productList.getSelectedIndex();
            if(index != -1){
                Product p = catalog.get(index);
                order.addProduct(p);
                cartArea.append(p.getProductName() + " $"+p.getProductPrice() + "\n");
            }
        });

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Catálogo de productos: "), BorderLayout.NORTH);
        leftPanel.add(scrollCatalog, BorderLayout.CENTER);
        leftPanel.add(addButton, BorderLayout.SOUTH);

        //panel de carrito
        cartArea = new JTextArea();
        cartArea.setEditable(false);
        JScrollPane scrollCart = new JScrollPane(cartArea);

        JPanel rightJPanel = new JPanel(new BorderLayout());
        rightJPanel.add(new JLabel("Carrito"), BorderLayout.NORTH);
        rightJPanel.add(scrollCart, BorderLayout.CENTER);

        JButton checkoutButton = new JButton("Finalizar Compra");
        checkoutButton.addActionListener((ActionEvent e) -> {
            cartArea.append(order.showOrder());            
        });
        
        rightJPanel.add(checkoutButton, BorderLayout.SOUTH);

        //dividir pantalla en dos
        add(leftPanel, BorderLayout.WEST);
        add(rightJPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new ShopNowGUI().setVisible(true);
        });
    }
}
