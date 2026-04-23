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
        try {
            ArrayList<Product> inventory = getInventory();

//        Sort inventory by name
            inventory.sort(Comparator.comparing(Product::getName));
            mainMenu(inventory);
        } catch (Exception e) {
            System.out.println("An Error Occurred. Exiting...");
            System.exit(0);
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

    public static void mainMenu(ArrayList<Product> inventory) throws InterruptedException {
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
            scanner.nextLine();

            System.out.println();
            switch (userInput) {
                case 1 -> productList(inventory);
                case 2 -> lookupById(inventory);
                case 3 -> priceRangeLookup(inventory);

            }
            Thread.sleep(500);
            System.out.println();
        }
    }

    public static void productList(ArrayList<Product> inventory) {
        for (Product product : inventory) {
            System.out.printf("id: %d, %s - $%.2f%n", product.getId(), product.getName(), product.getPrice());
        }

    }

    public static void lookupById(ArrayList<Product> inventory) {
        try {
            while (true) {
                System.out.print("Enter a product id (X to exit): ");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("x")) {
                    System.out.println("Exiting...");
                    break;
                }

                boolean foundProduct = false;
                for (Product product : inventory) {
                    if (product.getId() == Integer.parseInt(userInput)) {
                        foundProduct = true;
                        System.out.printf("id: %d, %s - $%.2f%n", product.getId(), product.getName(), product.getPrice());
                    }
                }

                if (!foundProduct) {
                    System.out.println("Product not found");
                }
            }


        } catch (Exception e) {
            System.out.println("Error looking up by id");
            throw new RuntimeException(e);
        }

    }

    public static void priceRangeLookup(ArrayList<Product> inventory) {
        try {
            System.out.print("Enter minimum price range (numbers & decimals only): ");
            double minRange = scanner.nextDouble();

            System.out.print("Enter maximum price range (numbers & decimals only): ");
            double maxRange = scanner.nextDouble();

            boolean productFound = false;

            for (Product product : inventory) {
                if (minRange < product.getPrice() && product.getPrice() < maxRange) {
                    productFound = true;
                    System.out.printf("id: %d, %s - $%.2f%n", product.getId(), product.getName(), product.getPrice());
                }
            }
            if (!productFound) {
                System.out.println("Products not found");
            }

        } catch (Exception e) {
            System.out.println("Error looking up by price range");
            throw new RuntimeException(e);
        }
    }
}
