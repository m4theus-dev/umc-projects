// array-based inventory system
// v1.0
// @m4theus-dev

// IMPORTS

import java.util.Scanner;
import java.util.InputMismatchException;

// code

public class Main {
    public static void main(String[] args) {
        // defining scanner
        Scanner scanner = new Scanner(System.in);

        // constant text-based interface
        while (true) {
            System.out.println("> - Array-Based Inventory System (v1.0) - <");
            System.out.println("> - Dev: m4theus-dev - <");

            System.out.println("\n> * PAGES *\n");

            System.out.println("> 1 | Stock Management");
            System.out.println("> 2 | Product Management");
            System.out.println("> 3 | Inventory Monitoring");
            System.out.println("> 4 | Exit");

            System.out.print("\nInput: ");

            try {
                int index1 = scanner.nextInt();

                switch (index1) {
                    case 1:
                        System.out.println("\n> - Stock Management - <");

                        System.out.println("\n> * OPTIONS *\n");

                        System.out.println("> 1 | Change Stock");
                        System.out.println("> 2 | Set Stock Quantity");
                        System.out.println("> 3 | Exit");

                    case 2:
                        System.out.println("\n> - Stock Management - <");

                        System.out.println("\n> * OPTIONS *\n");

                        System.out.println("> 1 | Change Stock");
                        System.out.println("> 2 | Set Stock Quantity");
                        System.out.println("> 3 | Exit");

                    case 3:
                        System.out.println("\n> - Stock Management - <");

                        System.out.println("\n> * OPTIONS *\n");

                        System.out.println("> 1 | Change Stock");
                        System.out.println("> 2 | Set Stock Quantity");
                        System.out.println("> 3 | Exit");

                    case 4:
                        System.out.println("\n> - Exiting...");
                        return;

                    default:
                        System.out.println("\n> - Invalid option. Try again.");
                }


            } catch (InputMismatchException e) {
                System.out.println("\n> Please enter a number.");
                scanner.next(); // cleans the invalid input.
            }
        }
    }
}