package com.pluralsight;

import java.util.ArrayList;

public class InventoryApp {
    public static void main(String[] args) {


    }

    public static ArrayList<Product> getInventory() {
        ArrayList<Product> productList = new ArrayList<Product>();

        productList.add(new Product(1, "AMD Ryzen 5600x", 156.99));
        productList.add(new Product(2, "Razer Chroma Cynosa", 49.99));
        productList.add(new Product(3, "Steel Series X", 79.99));
        productList.add(new Product(4, "Asus TUFF 240hz Monitor", 294.99));
        productList.add(new Product(5, "CoolerMaster CPU cooler", 34.99));

        return productList;
    }
}
