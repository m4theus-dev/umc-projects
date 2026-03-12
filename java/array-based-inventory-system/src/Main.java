// array-based inventory system
// v1.2
// @m4theus-dev

// IMPORTS
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        // define scanner for user input
        Scanner scanner = new Scanner(System.in);

        // create the inventory manager instance
        InventoryManager inventoryManager = new InventoryManager();

        int index1, index2;

        // main loop for menu
        while (true) {
            // display main menu header
            System.out.println("> - Array-Based Inventory System (v1.2) - <");
            System.out.println("> - Dev: m4theus-dev - <");

            // display pages
            System.out.println("\n> * PAGES *\n");
            System.out.println("> 1 | Stock Management");
            System.out.println("> 2 | Product Management");
            System.out.println("> 3 | Inventory Monitoring");
            System.out.println("> 4 | Exit");

            // read main menu input safely
            index1 = readInt(scanner, "\nInput: ");

            switch (index1) {
                case 1:
                    // stock management page
                    System.out.println("\n> - Stock Management - <");
                    System.out.println("\n> * OPTIONS *\n");
                    System.out.println("> 1 | Adjust Stock Quantity");
                    System.out.println("> 2 | Set Stock Quantity");
                    System.out.println("> 3 | Back to Main Menu");

                    index2 = readInt(scanner, "\nInput: ");

                    switch (index2) {
                        case 1: // adjust stock quantity
                            inventoryManager.listProducts();
                            int changeIndex = readInt(scanner, "\nEnter product index to change: ");
                            int addQty = readInt(scanner, "Enter quantity to add/subtract: ");
                            inventoryManager.addStock(changeIndex, addQty);
                            // wait for user before returning to stock menu
                            waitForEnter(scanner);
                            break;

                        case 2: // set stock quantity
                            inventoryManager.listProducts();
                            int setIndex = readInt(scanner, "\nEnter product index to set: ");
                            int setQty = readInt(scanner, "Enter new quantity: ");
                            inventoryManager.setStock(setIndex, setQty);
                            // wait for user before returning to stock menu
                            waitForEnter(scanner);
                            break;

                        case 3: // back to main menu
                            break;

                        default:
                            System.out.println("> invalid option! returning to main menu.");
                    }
                    break;

                case 2:
                    // product management page
                    System.out.println("\n> - Product Management - <");
                    System.out.println("\n> * OPTIONS *\n");
                    System.out.println("> 1 | Add Product");
                    System.out.println("> 2 | Remove Product");
                    System.out.println("> 3 | Back to Main Menu");

                    index2 = readInt(scanner, "\nInput: ");

                    switch (index2) {
                        case 1: // add product
                            // read product details
                            int productId = readInt(scanner, "Enter the product ID: ");
                            String productName = readString(scanner, "\nEnter the product's name: ");
                            double productPrice = readDouble(scanner, "Enter the product's price: ");
                            int productQty = readInt(scanner, "Enter initial quantity: ");
                            // create product and add to inventory
                            Product newProduct = new Product(productName, productPrice, productQty);
                            inventoryManager.addProduct(newProduct, productId);
                            // wait for user before returning to product menu
                            waitForEnter(scanner);
                            break;

                        case 2: // remove product
                            inventoryManager.listProducts();
                            int removeIndex = readInt(scanner, "\nEnter product index to remove: ");
                            inventoryManager.removeProduct(removeIndex);
                            // wait for user before returning to product menu
                            waitForEnter(scanner);
                            break;

                        case 3: // back to main menu
                            break;

                        default:
                            System.out.println("> invalid option! returning to main menu.");
                    }
                    break;

                case 3:
                    // inventory monitoring page
                    System.out.println("\n> - Inventory Monitoring - <");

                    // mini menu for inventory monitoring
                    System.out.println("\n> * OPTIONS *\n");
                    System.out.println("> 1 | List all products");
                    System.out.println("> 2 | Check low stock");
                    System.out.println("> 3 | Back to Main Menu");

                    index2 = readInt(scanner, "\nInput: ");

                    switch (index2) {
                        case 1: // list all products
                            inventoryManager.listProducts();
                            waitForEnter(scanner); // wait before returning
                            break;

                        case 2: // check low stock
                            int threshold = readInt(scanner, "Enter stock threshold: ");
                            inventoryManager.checkLowStock(threshold);
                            waitForEnter(scanner); // wait before returning
                            break;

                        case 3: // back to main menu
                            break;

                        default:
                            System.out.println("> Invalid option! Returning to main menu.");
                    }
                    break;

                case 4:
                    // exit program
                    System.out.println("\n> - Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("\n> - invalid option. try again.");
            }
        }
    }

    // ------------------------------
    // safe input methods
    // ------------------------------

    // read integer safely from user
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("> invalid input! please enter a number.");
                scanner.next(); // clear invalid input
            }
        }
    }

    // read double safely from user
    public static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("> invalid input! please enter a valid number (decimal allowed).");
                scanner.next(); // clear invalid input
            }
        }
    }

    // read string safely from user
    public static String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        scanner.nextLine(); // consume leftover newline from previous input
        return scanner.nextLine().trim();
    }

    // wait for user to press enter before returning to menu
    public static void waitForEnter(Scanner scanner) {
        System.out.println("\n> press enter to return...");
        scanner.nextLine(); // consume leftover newline
        scanner.nextLine(); // wait for user
    }
}