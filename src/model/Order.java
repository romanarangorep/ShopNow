package model;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private int idOrder;
    private LocalDateTime buyDate;
    private ArrayList<Product> productsArray = new ArrayList<>();
    //si fuera list seria private List<Product> productsArray = new ArrayList<>();

    public Order(int idOrder, Date buyDate) {
        this.idOrder = idOrder;
        this.buyDate = LocalDateTime.now();
    }

    public void addProduct(Product newProduct){
        productsArray.add(newProduct);
        /*add.(int index, product), index es la posicion en la que debe colocar el product, ej:
        add.(3,newProduct)*/
    }

    public double TotalCost(){
        double totalCost=0;
        //si queremos usar un += el totalcost debe tener un dato inicial
        for(int i = 0;i < productsArray.size();i++){
            //+= es como si dijera totalcost+productsArray.het(i).getProductPrice(), entonces este dato es mas simplificado
            totalCost += productsArray.get(i).getProductPrice();
        }
        return totalCost;
    }
    //colocar metodo con showinfo order con el id y el costo (optimizar mas) usar bucles mas optimos como for each

    public void showOrder(){
        System.out.println("Pedido #: "+idOrder);
        for (Product product : productsArray) {
            System.out.println("-"+product.getProductName()+" id:"+product.getProductId()+" precio: "+product.getProductPrice());
        }
        System.out.println("el precio total de su pedido es: $"+TotalCost()+" la fecha maxima de pago de su orden es: "+ buyDate.plusHours(24));
    }


}
