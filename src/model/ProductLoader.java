package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ProductLoader {
    public static List<Product> loadProducts(String pathFile){
        List<Product> products = new ArrayList<>();
        //bufferedreader para leer texto desde un flujo de entrada de caracteres almacenando los caracteres 
        try (BufferedReader br= new BufferedReader(new FileReader(pathFile))){
            String line;
            while((line = br.readLine())!= null){
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String nombre = values[1];
                double precio = Double.parseDouble(values[2]);
                products.add(new Product(nombre, precio, id));
            }
        }
         catch (IOException e) {
            System.out.println("Error leyendo archivo:"+e.getMessage());
        }
        return products;
    }

}
