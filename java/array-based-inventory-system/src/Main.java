// array-based inventory system
// v1.73
// @m4theus-dev

// IMPORTS
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.IOException;

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
            System.out.println("> - Array-Based Inventory System (v1.73) - <");
            System.out.println("> - Dev: m4theus-dev - <");

            // display pages
            System.out.println("\n> * PAGES *\n");
            System.out.println("> 1 | Stock Management");
            System.out.println("> 2 | Product Management");
            System.out.println("> 3 | Inventory Monitoring");
            System.out.println("> 4 | Files");
            System.out.println("> 5 | Exit");

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

                            // confirm action
                            if (confirmAction(scanner, "adjust the stock")) {
                                inventoryManager.addStock(changeIndex, addQty);
                                waitForEnter(scanner);
                            } else {
                                System.out.println("> Action cancelled.");
                            }
                            break;

                        case 2: // set stock quantity
                            inventoryManager.listProducts();
                            int setIndex = readInt(scanner, "\nEnter product index to set: ");
                            int setQty = readInt(scanner, "Enter new quantity: ");

                            // confirm action
                            if (confirmAction(scanner, "set the stock")) {
                                inventoryManager.setStock(setIndex, setQty);
                                waitForEnter(scanner);
                            } else {
                                System.out.println("> Action cancelled.");
                            }
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
                    System.out.println("> 2 | Edit Product");
                    System.out.println("> 3 | Remove Product");
                    System.out.println("> 4 | Back to Main Menu");

                    index2 = readInt(scanner, "\nInput: ");

                    switch (index2) {
                        case 1: // add product
                            int productId = readInt(scanner, "\nEnter the product ID: ");
                            String productName = readString(scanner, "Enter the product's name: ");
                            double productPrice = readDouble(scanner, "Enter the product's price: ");
                            int productQty = readInt(scanner, "Enter initial quantity: ");

                            // confirm action
                            if (confirmAction(scanner, "add the product")) {
                                Product newProduct = new Product(productName, productPrice, productQty);
                                inventoryManager.addProduct(newProduct, productId);
                                waitForEnter(scanner);
                            } else {
                                System.out.println("> Action cancelled.");
                            }
                            break;

                        case 2: // edit product
                            int productIndex = readInt(scanner, "\nEnter product index to edit: ");
                            // confirm action
                            if (confirmAction(scanner, "edit the product")) {
                                inventoryManager.editProduct(productIndex, scanner);
                                waitForEnter(scanner);
                            } else {
                                System.out.println("> Action cancelled.");
                            }
                            break;

                        case 3: // remove product
                            inventoryManager.listProducts();
                            int removeIndex = readInt(scanner, "\nEnter product index to remove: ");

                            // confirm action
                            if (confirmAction(scanner, "remove the product")) {
                                inventoryManager.removeProduct(removeIndex);
                                waitForEnter(scanner);
                            } else {
                                System.out.println("> Action cancelled.");
                            }
                            break;

                        case 4: // back to main menu
                            break;

                        default:
                            System.out.println("> invalid option! returning to main menu.");
                    }
                    break;

                case 3:
                    // inventory monitoring page
                    System.out.println("\n> - Inventory Monitoring - <");
                    System.out.println("\n> * OPTIONS *\n");
                    System.out.println("> 1 | List all products");
                    System.out.println("> 2 | Check low stock");
                    System.out.println("> 3 | Back to Main Menu");

                    index2 = readInt(scanner, "\nInput: ");

                    switch (index2) {
                        case 1:
                            inventoryManager.listProducts();
                            waitForEnter(scanner);
                            break;

                        case 2:
                            int threshold = readInt(scanner, "Enter stock threshold: ");
                            inventoryManager.checkLowStock(threshold);
                            waitForEnter(scanner);
                            break;

                        case 3:
                            break;

                        default:
                            System.out.println("> Invalid option! Returning to main menu.");
                    }
                    break;

                case 4:
                    // file management page
                    System.out.println("\n> - Files - <");
                    System.out.println("\n> * OPTIONS *\n");
                    System.out.println("> 1 | Import save file");
                    System.out.println("> 2 | Export save file");
                    System.out.println("> 3 | List save files");
                    System.out.println("> 4 | Delete save file(s)");
                    System.out.println("> 5 | Back to Main Menu");

                    index2 = readInt(scanner, "\nInput: ");

                    switch (index2) {
                        case 1:
                            // import save file

                            // get list of save files from csvreader
                            File[] saves = CSVReader.listSaveFiles();

                            // check if there are no save files
                            if (saves == null || saves.length == 0) {
                                System.out.println("> No save files found.");
                                waitForEnter(scanner);
                                break;
                            }

                            // show list of files
                            System.out.println("\n> Available save files:");

                            for (int i = 0; i < saves.length; i++) {
                                System.out.println("> " + i + " | " + saves[i].getName());
                            }

                            int fileIndex = readInt(scanner, "\nSelect file index: ");

                            // validate index
                            if (fileIndex < 0 || fileIndex >= saves.length) {
                                System.out.println("> Invalid file index.");
                                waitForEnter(scanner);
                                break;
                            }

                            // confirm import
                            if (confirmAction(scanner, "import this file")) {

                                inventoryManager.importCSV(saves[fileIndex].getName());

                            } else {
                                System.out.println("> Action cancelled.");
                            }

                            waitForEnter(scanner);
                            break;

                        case 2:
                            // export save file

                            // ask for file name
                            System.out.print("Enter file name (leave blank for automatic name): ");
                            scanner.nextLine(); // consume newline
                            String filename = scanner.nextLine().trim();

                            if (confirmAction(scanner, "export the inventory")) {

                                inventoryManager.exportCSV(filename, scanner);

                            } else {
                                System.out.println("> Action cancelled.");
                            }

                            waitForEnter(scanner);
                            break;

                        case 3:
                            // list save files

                            File[] saveList = CSVReader.listSaveFiles();

                            if (saveList == null || saveList.length == 0) {

                                System.out.println("> No save files found.");

                            } else {

                                System.out.println("\n> Save files:");

                                for (int i = 0; i < saveList.length; i++) {
                                    System.out.println("> " + i + " | " + saveList[i].getName());
                                }
                            }

                            waitForEnter(scanner);
                            break;

                        case 4:
                            // delete save file

                            File[] files = CSVReader.listSaveFiles();

                            // check if there are no save files
                            if (files == null || files.length == 0) {
                                System.out.println("> No save files to delete.");
                                waitForEnter(scanner);
                                break;
                            }

                            // show list of save files
                            System.out.println("\n> Save files:");

                            for (int i = 0; i < files.length; i++) {
                                System.out.println("> " + i + " | " + files[i].getName());
                            }

                            // ask user which file to delete (-1 means delete all)
                            int deleteIndex = readInt(scanner, "\nSelect file index to delete (-1 to delete all): ");

                            // validate index
                            if (deleteIndex < -1 || deleteIndex >= files.length) {
                                System.out.println("> Invalid index.");
                                waitForEnter(scanner);
                                break;
                            }

                            // if user chose -1, delete all files
                            if (deleteIndex == -1) {

                                // extreme confirmation warning
                                System.out.println("\n> WARNING: This will permanently delete ALL save files.");
                                System.out.print("> Type 'confirm' to proceed: ");

                                scanner.nextLine(); // consume newline
                                String confirm = scanner.nextLine();

                                if (confirm.equalsIgnoreCase("confirm")) {

                                    int deletedCount = 0;

                                    // iterate through every file and delete it
                                    for (File f : files) {

                                        if (f.delete()) {
                                            deletedCount++;
                                        }
                                    }

                                    System.out.println("> " + deletedCount + " file(s) deleted.");

                                } else {

                                    System.out.println("> Operation cancelled.");

                                }

                                waitForEnter(scanner);
                                break;
                            }

                            // normal single file delete
                            if (confirmAction(scanner, "delete this file")) {

                                if (files[deleteIndex].delete()) {
                                    System.out.println("> File deleted.");
                                } else {
                                    System.out.println("> Failed to delete file.");
                                }

                            } else {

                                System.out.println("> Action cancelled.");

                            }

                            waitForEnter(scanner);
                            break;

                        case 5:
                            // back to main menu
                            break;
                        default:
                            System.out.println("> Invalid option! Returning to main menu.");
                    }
                    break;

                case 5:
                    System.out.println("\n> - Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("\n> - invalid option. try again.");
            }
        }
    }

    // -----------------------------
    // confirm action before modifying inventory
    // -----------------------------
    public static boolean confirmAction(Scanner scanner, String actionDescription) {
        System.out.print("Do you want to " + actionDescription + "? (Y/N): ");
        scanner.nextLine(); // consume leftover newline
        String input = scanner.nextLine().trim();
        return input.equalsIgnoreCase("Y");
    }

    // -----------------------------
    // safe input methods
    // -----------------------------
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("> Invalid input! Please enter a number.");
                scanner.next();
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
                scanner.next();
            }
        }
    }

    public static String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        scanner.nextLine(); // consume leftover newline
        return scanner.nextLine().trim();
    }

    public static void waitForEnter(Scanner scanner) {
        System.out.println("\n> Press enter to return...");
        scanner.nextLine();
        scanner.nextLine();
    }
}