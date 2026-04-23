package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class InventoryApp {
    static Scanner scanner = new Scanner(System.in);
    static boolean appRunning = true;

    public static void main(String[] args) {

        ArrayList<Product> inventory = getInventory();

//        Sort inventory by name
        inventory.sort(Comparator.comparing(Product::getName));
        mainMenu(inventory);

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

    public static void mainMenu(ArrayList<Product> inventory) {
        while (appRunning) {
            System.out.println("=== Marc's Hardware(?) Store ===");
            System.out.println("""
                    What do you want to do?
                    1- List all products
                    2- Lookup a product by its id
                    3- Find all products within a price range
                    4- Add a new product
                    5- Quit the application""");
            System.out.print("Enter command: ");
            int userInput = scanner.nextInt();

            System.out.println();
            switch (userInput) {
                case 1 -> productList(inventory);
            }
            System.out.println();

        }
    }

    public static void productList(ArrayList<Product> inventory) {
        for (Product product : inventory) {
            System.out.printf("id: %d, %s - $%.2f%n", product.getId(), product.getName(), product.getPrice());
        }

    }
}
