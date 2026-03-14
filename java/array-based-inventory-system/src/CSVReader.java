// IMPORTS
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVReader {
    // save folder where all files are
    private static final String SAVE_FOLDER = "saves/";

    // list save files inside saves folder
    public static File[] listSaveFiles() {

        File folder = new File(SAVE_FOLDER);

        // if it doesn't exist, creates the folder automatically
        if (!folder.exists()) {
            folder.mkdir();
        }

        return folder.listFiles((dir, name) -> name.endsWith(".csv"));
    }

    // reads a .csv and transforms it into a product list
    public static ArrayList<Product> readFile(String filename) throws IOException {

        ArrayList<Product> products = new ArrayList<>();

        File file = new File(SAVE_FOLDER + filename);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                // split lines into parts
                String[] parts = line.split(",");

                // read and store values
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);

                // add product with values
                Product p = new Product(name, price, quantity);
                p.id = id;

                products.add(p);
            }

        }

        return products;
    }

    // exports the product list to .csv
    public static void exportFile(String filename, ArrayList<Product> products, Scanner scanner) throws IOException {

        // if has empty name, creates an auto name
        if (filename == null || filename.trim().isEmpty()) {
            filename = generateDefaultName();
        }

        File file = new File(SAVE_FOLDER + filename + ".csv");

        if (file.exists()) {
            System.out.print("> File already exists. Overwrite? (y/n): ");
            String input = scanner.nextLine();

            if (!input.equalsIgnoreCase("y")) {
                System.out.println("> Export cancelled.");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // header line
            writer.write("id,name,price,quantity");
            writer.newLine();

            for (Product p : products) {
                // adding products for each line
                String safeName = p.name.replace("\"", "\"\"");

                String line = p.id + ",\"" + safeName + "\"," + p.price + "," + p.quantity;

                writer.write(line);
                writer.newLine();
            }
        }

        System.out.println("> File saved to: " + file.getName());
    }

    // generates auto name based on date and time
    private static String generateDefaultName() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");

        return LocalDateTime.now().format(formatter);
    }

}