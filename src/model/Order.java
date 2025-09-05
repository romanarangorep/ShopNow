package model;
import java.util.*;


public class Order {
    private int idOrder;
    private ArrayList<Product> productsArray = new ArrayList<>();
    //si fuera list seria private List<Product> productsArray = new ArrayList<>();

    public Order(int idOrder) {
        this.idOrder = idOrder;
    }

    public void addProducts(Product newProduct){
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

    


}
