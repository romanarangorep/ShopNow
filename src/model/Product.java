package model;

public class Product {
    private String productName;
    private double productPrice;
    private int idProduct;

    public Product(String productName, double productPrice, int idProduct){
        this.productName = productName;
        this.productPrice = productPrice;
        this.idProduct= idProduct;
    }

    public void showProduct(){
        System.out.println("Product info: "+ "Product name: "+ productName + "Product price: "+ productPrice + "Product ID: "+idProduct);
    }

    public double getProductPrice() {
        return productPrice;
    }



}
