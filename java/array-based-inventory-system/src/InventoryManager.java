// IMPORTS
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class InventoryManager {
    // dynamic product list
    private ArrayList<Product> inventory;

    public InventoryManager() {
        inventory = new ArrayList<>(); // creates the empty list
    }

    // private method to clear inventory
    private void clearInventory() {
        inventory.clear();
    }

    // add product
    public void addProduct(Product p, int id) {
        try {
            // check if product is null
            if (p == null) {
                throw new NullPointerException("product is null");
            }
            // check for commas in string
            if (p.name != null && p.name.contains(",")) {
                throw new IllegalArgumentException("product name cannot contain commas");
            }
            // validate id
            if (id < 0) {
                throw new IllegalArgumentException("id cannot be negative");
            }
            if (String.valueOf(id).length() > 8) {
                throw new IllegalArgumentException("id cannot have more than 8 digits");
            }
            // check for duplicate id in inventory
            for (Product existing : inventory) {
                if (existing.id == id) {
                    throw new IllegalArgumentException("id already exists in inventory");
                }
            }

            // set the product's id
            p.id = id;

            // add product to inventory
            inventory.add(p);
            System.out.println("> Product added successfully!");

        } catch (NullPointerException e) {
            System.out.println("> Error: Cannot add null product!");
        } catch (IllegalArgumentException e) {
            System.out.println("> Invalid product ID: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("> Unexpected error while adding product: " + e.getMessage());
        }
    }

    // remove product by id / index
    public void removeProduct(int index) {
        try {
            Product removed = inventory.remove(index);
            System.out.println("> Removed: " + removed.name);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("> Invalid index! No product removed.");
        } catch (Exception e) {
            System.out.println("> Unexpected error while removing product: " + e.getMessage());
        }
    }

    // list all products
    public void listProducts() {
        try {
            if (inventory.isEmpty()) {
                System.out.println("> Inventory is empty!");
                return;
            }
            System.out.println("> Inventory List:");
            for (int i = 0; i < inventory.size(); i++) {
                Product p = inventory.get(i);
                System.out.println("> " + i + " | " + p.name + " | Quantity: " + p.quantity + " | Price: $" + p.price + " | ID: " + p.id);
            }
        } catch (Exception e) {
            System.out.println("> Unexpected error while listing products: " + e.getMessage());
        }
    }

    // update product quantity on stock
    public void setStock(int index, int newQuantity) {
        try {
            Product p = inventory.get(index);
            if (newQuantity < 0) {
                throw new IllegalArgumentException("Quantity cannot be negative!");
            }
            int oldQty = p.quantity;
            p.quantity = newQuantity;
            System.out.println("> Updated " + p.name + " quantity from " + oldQty + " to " + newQuantity);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("> Invalid index! No product updated.");
        } catch (IllegalArgumentException e) {
            System.out.println("> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("> Unexpected error while updating stock: " + e.getMessage());
        }
    }

    public void addStock(int index, int addQuantity) {
        try {
            Product p = inventory.get(index);
            int oldQty = p.quantity;
            int newQty = oldQty + addQuantity;
            if ((p.quantity + addQuantity) < 0) {
                throw new IllegalArgumentException("Quantity cannot be negative!");
            }
            p.quantity = newQty;
            System.out.println("> Updated " + p.name + " quantity from " + oldQty + " to " + newQty);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("> Invalid index! No product updated.");
        } catch (IllegalArgumentException e) {
            System.out.println("> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("> Unexpected error while updating stock: " + e.getMessage());
        }
    }

    // check products low on stock
    public void checkLowStock(int threshold) {
        try {
            if (threshold < 0) {
                throw new IllegalArgumentException("Threshold cannot be negative!");
            }
            System.out.println("> Low Stock Products (threshold: " + threshold + "):");
            boolean found = false;
            for (Product p : inventory) {
                if (p.quantity <= threshold) {
                    System.out.println(p.name + " | Quantity: " + p.quantity);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("> No products below threshold.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("> Unexpected error while checking low stock: " + e.getMessage());
        }
    }

    public void editProduct(int index, Scanner scanner) {
        try {
            // check if index is valid
            if (index < 0 || index >= inventory.size()) {
                System.out.println("> Invalid index!");
                return;
            }

            Product p = inventory.get(index);

            scanner.nextLine(); // consume leftover newline

            // edit name
            System.out.print("Enter new product name (leave blank to keep current: " + p.name + "): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                p.name = newName; // update name
            }

            // edit price
            System.out.print("Enter new product price (leave blank to keep current: " + p.price + "): ");
            String priceInput = scanner.nextLine().trim();
            if (!priceInput.isEmpty()) {
                try {
                    double newPrice = Double.parseDouble(priceInput);
                    if (newPrice < 0) {
                        System.out.println("> Price cannot be negative, keeping previous value.");
                    } else {
                        p.price = newPrice;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("> Invalid input, keeping previous price.");
                }
            }

            // edit quantity
            System.out.print("Enter new quantity (leave blank to keep current: " + p.quantity + "): ");
            String qtyInput = scanner.nextLine().trim();
            if (!qtyInput.isEmpty()) {
                try {
                    int newQty = Integer.parseInt(qtyInput);
                    if (newQty < 0) {
                        System.out.println("> Quantity cannot be negative, keeping previous value.");
                    } else {
                        p.quantity = newQty;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("> Invalid input, keeping previous quantity.");
                }
            }

            System.out.println("> Product updated successfully!");

        } catch (Exception e) {
            System.out.println("> Unexpected error while editing product: " + e.getMessage());
        }
    }

    // import .csv files
    public void importCSV(String filename) {
        try {
            ArrayList<Product> products = CSVReader.readFile(filename);

            clearInventory();

            // adding products in the inventory
            for (Product p : products) {
                addProduct(p, p.id);
            }

            System.out.println("> Import completed.");

        } catch (Exception e) {

            System.out.println("> Error importing file: " + e.getMessage());
        }
    }

    // export .csv file
    public void exportCSV(String path) {
        try {
            // placeholder for CSV export logic
            System.out.println("> Exporting inventory to CSV: " + path);
            System.out.println("> (CSV export not implemented yet)");

        } catch (Exception e) {
            System.out.println("> Unexpected error while exporting CSV: " + e.getMessage());
        }
    }
}