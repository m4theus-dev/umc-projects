// IMPORTS
import java.util.ArrayList;

public class InventoryManager {
    // dynamic product list
    private ArrayList<Product> inventory;

    public InventoryManager() {
        inventory = new ArrayList<>(); // creates the empty list
    }

    // add product function
    public void addProduct(Product p) {
        try {
            if (p == null) {
                throw new NullPointerException("Product is null!");
            }
            inventory.add(p);
            System.out.println("> Product added!");
        } catch (NullPointerException e) {
            System.out.println("> Error: Cannot add null product!");
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
                System.out.println("> " + i + " | " + p.name + " | Qty: " + p.quantity + " | Price: $" + p.price + " | ID: " + p.id);
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
                    System.out.println(p.name + " | Qty: " + p.quantity);
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
}