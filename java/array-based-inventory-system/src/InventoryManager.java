// IMPORTS
import java.util.ArrayList;

public class InventoryManager {
    // Lista dinâmica de produtos
    private ArrayList<Product> inventory;

    public InventoryManager() {
        inventory = new ArrayList<>(); // cria a lista vazia
    }

    // Adicionar um produto
    public void addProduct(Product p) {
        inventory.add(p);
        System.out.println("> Product added!");
    }

    // Remover produto pelo índice
    public void removeProduct(int index) {
        if (index >= 0 && index < inventory.size()) {
            Product removed = inventory.remove(index);
            System.out.println("> Removed: " + removed.name);
        } else {
            System.out.println("> Invalid index!");
        }
    }

    // Listar todos os produtos
    public void listProducts() {
        if (inventory.isEmpty()) {
            System.out.println("> Inventory is empty!");
            return;
        }
        System.out.println("> Inventory List:");
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            System.out.println(i + " | " + p.name + " | Qty: " + p.quantity + " | Price: $" + p.price);
        }
    }

    // Atualizar quantidade de um produto
    public void updateStock(int index, int newQuantity) {
        if (index >= 0 && index < inventory.size()) {
            Product p = inventory.get(index);
            p.quantity = newQuantity;
            System.out.println("> Updated " + p.name + " quantity to " + newQuantity);
        } else {
            System.out.println("> Invalid index!");
        }
    }

    // Verificar produtos com estoque baixo
    public void checkLowStock(int threshold) {
        System.out.println("> Low Stock Products (threshold: " + threshold + "):");
        for (Product p : inventory) {
            if (p.quantity <= threshold) {
                System.out.println(p.name + " | Qty: " + p.quantity);
            }
        }
    }
}