package model;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private int idOrder;
    private LocalDateTime buyDate;
    private ArrayList<Product> productsArray = new ArrayList<>();
    //si fuera list seria private List<Product> productsArray = new ArrayList<>();
    //constante para darle un formato a la fecha
    private static final DateTimeFormatter SPANISH_FORMATTER =
    DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm", Locale.of("es", "ES"));
    

    public Order(int idOrder, LocalDateTime buyDate) {
        this.idOrder = idOrder;
        this.buyDate = buyDate;
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
        
        //calculamos fecha limite
        LocalDateTime dueDate = buyDate.plusHours(24);
        //le damos un formato a esa fecha limite
        String formattedDueDate = dueDate.format(SPANISH_FORMATTER);

        System.out.println("El precio total de su pedido es: $" + TotalCost() +   "  La fecha máxima de pago de su orden es: " + formattedDueDate);
    }


}
