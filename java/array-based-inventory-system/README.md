# ArrayList Based Inventory System
A simple inventory management system implemented in Java using ArrayList.  
This project is a console-based application designed to practice fundamental programming concepts and basic data structures.

## Features
* Add new products with unique ID (validated: non-negative, max 8 digits, no duplicates)
* Remove products
* Edit existing products (name, price, quantity, ID) with option to keep current values
* Adjust or set product quantity
* List all products in the inventory
* Inventory monitoring with low stock check (custom threshold)
* Simple command-line interface with multiple pages
* Safe user input handling (`readInt`, `readDouble`, `readString`)
* Wait-for-enter functionality before returning to menus
* Clear and descriptive inline comments

## Technologies
* Java
* IntelliJ IDEA

## Project Structure
```
src
└─ Main.java
└─ InventoryManager.java
└─ Product.java
```


## Key Improvements
* implemented **safe input methods** to avoid crashes from invalid user input
* centralized user input handling with `readInt`, `readDouble`, `readString`
* added **adjust stock quantity** feature (can add or subtract from current stock)
* added **edit product feature** allowing selective field updates
* added **product ID validation** (non-negative, max 8 digits, no duplicates)
* improved **menu system** with subpages for stock management, product management, and inventory monitoring
* added **press enter to return** prompts after viewing inventory or completing actions
* added **check low stock** feature with custom threshold
* added **clear, lowercase comments in english** throughout the code for readability
* refactored repeated try-catch blocks into safe input functions

## How to Run
Compile and run the program using Java:
```
javac Main.java
java Main
```
Or run directly using IntelliJ.

## Learning Goals
This project was created to practice:
* Java syntax and object-oriented programming
* ArrayList usage instead of arrays
* control flow and switch statements
* safe and robust user input handling
* designing a simple but organized console application
* adding comments and documenting code for clarity

## Future Improvements
* data persistence (saving and loading inventory from files)
* more advanced inventory reporting (low stock alerts, sorting)
* graphical user interface (frontend) for easier interaction
* integration with REST APIs for external data or inventory management
