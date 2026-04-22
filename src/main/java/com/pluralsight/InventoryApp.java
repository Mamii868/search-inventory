package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class InventoryApp {
    public static void main(String[] args) {

        ArrayList<Product> inventory = getInventory();

//        Sort inventory by name
        inventory.sort(Comparator.comparing(Product::getName));

        System.out.println("We carry the following inventory: ");


        for (Product product : inventory) {
            System.out.printf("id: %d, %s - $%.2f \n", product.getId(), product.getName(), product.getPrice());
        }

    }

    public static ArrayList<Product> getInventory() {
        ArrayList<Product> productList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                productList.add(processFileData(currentLine));
            }
        } catch (Exception e) {
            System.out.println("An error occurred");
        }
        return productList;
    }

    public static Product processFileData(String data) {
//        id|name|price
        String[] splitFileData = data.split("\\|");
        return new Product(Integer.parseInt(splitFileData[0]), splitFileData[1], Double.parseDouble(splitFileData[2]));
    }
}
