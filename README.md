# Java CLI Sandwich Ordering App – DELI-cious

This is a simple command-line sandwich ordering system built in Java. It allows users to build multiple custom sandwiches, select chips and drinks, and generates an itemized receipt stored with a timestamp. The interface is clean and beginner-friendly — guiding users with readable options and straightforward prompts.

---

## 🌱 Project Purpose & Background

This project was built for Year Up United’s Learn to Code Academy Capstone (2 of 3) as a point-of-sale style system for a growing sandwich shop.  
From the start, the goal was to simulate a full-stack ordering flow while practicing clean object-oriented Java.

The design focuses on flexibility and structure:
- Orders can include multiple sandwiches, chips, and drinks
- Each sandwich can be fully customized (bread, size, toppings, toasting)
- Toppings have pricing logic (premium vs. regular, extra charges)
- All receipts are printed to screen and saved as a timestamped `.txt` file

It’s built entirely with core Java — no frameworks, just clean OOP.

---

## 🧭 Menu Walkthrough

When the program starts, the user is greeted with a home screen:

    Home Screen 
    1) New Order
    2) Signature Sandwich
    0) Exit

From here:

- **New Order** launches an order screen where the user can:

      1) Add Sandwich
      2) Add Chip
      3) Add Drink
      4) Checkout
      0) Cancel Order

  - Sandwich builder includes: size → bread → toasting → toppings (with extras)

- **Signature Sandwich** offers a curated list of sandwich templates that auto-checkout
- **Checkout** prints a full itemized receipt (subtotal + tax) and saves it as a `.txt` file

---

## 📂 File Structure

- MenuScreen.java – User interface logic (menus, prompts, input loop)
- Sandwich.java – Base sandwich object
- SignatureSandwich.java – Inherits from Sandwich and stores name/description
- Topping.java – Represents individual toppings (meat, cheese, regular, sauce)
- Chip.java – Represents chip selections
- Drink.java – Represents drink selections with size-based pricing
- Order.java – Holds lists of sandwiches, chips, and drinks
- ReceiptGenerator.java – Handles printing and saving receipt to file
- SignatureMenu.java – Stores predefined sandwich templates
- Main.java – Entry point

---

## 🚀 Features

- 🥪 Multi-sandwich orders: Build as many custom sandwiches as you like
- 🔥 Toasting, bread, and topping logic built into each sandwich
- 💸 Automatic pricing: Size-based sandwich price, premium topping fees, and drink/chip pricing
- 📄 Receipts saved as timestamped files:

      src/main/resources/receipt/YYYYMMDD-HHMMSS.txt

- 🧠 Clean output:

      8" white sandwich (Toasted)    $7.00
      Toppings:
       - Turkey             $2.00
       - Cheddar (extra)    $2.10
      Chips: Classic        $1.50
      Drink: Large Soda     $3.50
      Subtotal:             $14.60
      Tax (9.5%):           $1.39
      Total:                $15.99

---

## 🧠 Core Java Concepts Used

| Concept              | Example from Code                      |
|----------------------|----------------------------------------|
| Inheritance          | SignatureSandwich extends Sandwich     |
| Encapsulation        | All fields private + getters/setters   |
| Polymorphism         | List<Sandwich> holds base + extended   |
| File I/O             | FileWriter in ReceiptGenerator         |
| Scanner Input        | Reused in all CLI menus                |
| Control Flow Loops   | While/switch combo for navigation      |

---

## 📌 Highlights

- calculatePrice() in Sandwich adds up base + all toppings
- Order tracks sandwich, chip, and drink lists independently
- ReceiptGenerator separates print vs. file output cleanly
- Signature sandwiches loaded once via static SignatureMenu

---

## 🖥️ How to Run

1. Open the project in IntelliJ or any Java IDE  
2. Run Main.java  
3. Follow the on-screen prompts to build your order and checkout

---

## 🧪 How to Test

This project includes a full suite of JUnit 5 unit tests:

    mvn test

Covers:
- Topping pricing logic
- Sandwich base & toppings total
- Chip/Drink pricing
- Order subtotal calculation

---

## 🗂 Example Signature Sandwiches

- The Classic Turkey Delight – Turkey, Swiss, Tomato, Honey Mustard on Toasted Wheat  
- The Spicy Roast Beast – Roast Beef, Pepper Jack (extra), Jalapeños, Chipotle on Toasted Wrap  
- The Salamifornian Delight – Salami, Provolone, Guacamole, Vinaigrette on Rye  
- The Saucy Sammy – All sauces on a wrap  
- The Veggie Crunch – Lettuce, Tomato, Cucumber, Mushroom, Guacamole on Toasted Wheat  
