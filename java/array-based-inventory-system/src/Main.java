// array-based inventory system
// v1.1
// @m4theus-dev

// IMPORTS
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager inventoryManager = new InventoryManager();
        int index1, index2;

        while (true) {
            System.out.println("> - Array-Based Inventory System (v1.1) - <");
            System.out.println("> - Dev: m4theus-dev - <");

            System.out.println("\n> * PAGES *\n");
            System.out.println("> 1 | Stock Management");
            System.out.println("> 2 | Product Management");
            System.out.println("> 3 | Inventory Monitoring");
            System.out.println("> 4 | Exit");

            index1 = readInt(scanner, "\nInput: ");

            switch (index1) {
                case 1:
                    System.out.println("\n> - Stock Management - <");
                    System.out.println("\n> * OPTIONS *\n");
                    System.out.println("> 1 | Adjust Stock Quantity");
                    System.out.println("> 2 | Set Stock Quantity");
                    System.out.println("> 3 | Back to Main Menu");

                    index2 = readInt(scanner, "\nInput: ");

                    switch (index2) {
                        case 1: // adjust stock
                            inventoryManager.listProducts();
                            int changeIndex = readInt(scanner, "\nEnter product index to change: ");
                            int addQty = readInt(scanner, "Enter quantity to add/subtract: ");
                            inventoryManager.addStock(changeIndex, addQty);
                            break;

                        case 2: // set stock
                            inventoryManager.listProducts();
                            int setIndex = readInt(scanner, "\nEnter product index to set: ");
                            int setQty = readInt(scanner, "Enter new quantity: ");
                            inventoryManager.setStock(setIndex, setQty);
                            break;

                        case 3:
                            break; // back to main menu

                        default:
                            System.out.println("> Invalid option! Returning to main menu.");
                    }
                    break;

                case 2:
                    System.out.println("\n> - Product Management - <");
                    System.out.println("\n> * OPTIONS *\n");
                    System.out.println("> 1 | Add Product");
                    System.out.println("> 2 | Remove Product");
                    System.out.println("> 3 | Back to Main Menu");

                    index2 = readInt(scanner, "\nInput: ");

                    switch (index2) {
                        case 1: // add product
                            String productName = readString(scanner, "\nEnter the product's name: ");
                            double productPrice = readDouble(scanner, "Enter the product's price: ");
                            int productQty = readInt(scanner, "Enter initial quantity: ");
                            Product newProduct = new Product(productName, productPrice, productQty);
                            inventoryManager.addProduct(newProduct);
                            break;

                        case 2: // remove product
                            inventoryManager.listProducts();
                            int removeIndex = readInt(scanner, "\nEnter product index to remove: ");
                            inventoryManager.removeProduct(removeIndex);
                            break;

                        case 3:
                            break; // back to main menu

                        default:
                            System.out.println("> Invalid option! Returning to main menu.");
                    }
                    break;

                case 3:
                    System.out.println("\n> - Inventory Monitoring - <");
                    inventoryManager.listProducts();
                    break;

                case 4:
                    System.out.println("\n> - Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("\n> - Invalid option. Try again.");
            }
        }
    }

    // safe input methods

    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("> Invalid input! Please enter a number.");
                scanner.next(); // cleans invalid entry
            }
        }
    }

    public static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("> Invalid input! Please enter a valid number (decimal allowed).");
                scanner.next(); // cleans invalid entry
            }
        }
    }

    public static String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim(); // reads full string, removes extra spaces
    }
}