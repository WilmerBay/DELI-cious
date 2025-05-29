# Java Deli-cious models.Sandwich Builder App

This is a fully interactive, menu-driven Java application that lets users build their own custom sandwich or choose from a list of signature subs. It was built as part of a capstone project to demonstrate object-oriented design, inheritance, and clean user input handling via the console.

---

## 🥪 Project Purpose & Background

This application was built for Year Up United’s Learn to Code Academy Capstone (2 of 3). The goal was to create a console-based food ordering system that could:

- Let users fully customize a sandwich from scratch
- Include a set of predefined "Signature Sandwiches"
- Handle receipts and tax calculations automatically
- Demonstrate clean code structure and Java best practices

From inheritance to file handling, this project showcases a range of fundamental Java concepts used together in a polished final product.

---

## 🧭 Menu Walkthrough

When the program starts, the user sees a home screen:

```
=========================
Welcome to DELI-cious!
=========================
1) New Sandwich
2) Signature Sandwich
0) Exit
```

Each path leads to a guided experience.

### 1) Build Your Own Sandwich

The user chooses:
- **Size** (4", 8", 12")
- **Bread** (White, Wheat, Rye, Wrap)
- **Toasting option**
- **Toppings** (Meat, Cheese, Regular, Sauces – with extra option)
- **Chips** and **Drinks** (with prices)

Toppings can be added or removed during the process, and prices update dynamically.

### 2) Signature Sandwiches

A list of pre-built sandwiches is shown, like:
```
1) The Classic Turkey Delight - Turkey, Cheddar, Lettuce, Mayo (Toasted White, 8")
2) The Salamifornian Delight - Salami, Provolone, Tomato, Ranch (Rye, 8")
3) The Saucy Sammy - All Sauces on Wrap (4")
```

Once selected, the sandwich is paired with a default chip and drink combo, and the receipt is displayed and saved.

---

## 🧾 Receipt System

- Shows a detailed itemized breakdown (toppings + prices)
- Includes subtotal, tax (9.5%), and total
- Saves the receipt to a timestamped `.txt` file
- Still prints the receipt to the terminal for user confirmation

Example output:
```
======= DELI-cious Receipt =======
8" white sandwich
Toasted
Toppings:
 - Turkey              $1.00
 - Cheddar            $0.50
 - Lettuce            $0.00
 - Mayo               $0.00
Chips: Classic        $1.50
Drink: Soda           $1.50

Subtotal: $4.50
Tax (9.5%): $0.43
Total: $4.93
==================================
```

---

## 📂 File Structure

- `app.Main.java` – Launches the application
- `ui.MenuScreen.java` – Handles the home and builder menu logic
- `Sandwich.java` – The main sandwich object with toppings and price logic
- `SignatureSandwich.java` – Extends `Sandwich` for pre-built sub options
- `data.SignatureMenu.java` – Holds and returns a list of signature sandwiches
- `Order.java` – Represents a full order with sandwich, chip, drink
- `Topping.java` – Tracks topping type, name, and price logic
- `Chip.java` and `Drink.java` – Simple add-ons with fixed prices
- `persistence.ReceiptGenerator.java` – Prints and writes receipts with totals

---

## 🚀 Features

- 🧠 **User-friendly builder flow** with back and cancel options
- 🍖 **Custom toppings** by category, including "extra" charges
- 🥤 **Add-ons** like chips and drinks with pricing
- 🗃️ **Signature sandwich support** with inheritance
- 📄 **Receipts saved and printed** with tax and total calculation

---

## 🧠 Core Java Concepts Used

| Concept              | Example from Code                    |
|----------------------|--------------------------------------|
| Inheritance          | `SignatureSandwich extends Sandwich` |
| Composition          | `Order` contains `Sandwich`, `Chip`, `Drink` |
| File I/O             | `FileWriter`, saved receipt generation |
| Polymorphism         | Signature sandwiches treated as base `Sandwich` |
| Switch Expressions   | Simplified control flow in menus     |
| Input Validation     | Topping selection, number parsing |
| Custom Price Logic   | Prices vary by topping type & sandwich size and extras |

---

## 📌 Highlights

- Signature subs are **preloaded via inheritance** and housed in `data.SignatureMenu`
- Toppings feature **dynamic pricing** based on sandwich size and type
- The user is never “lost” — each flow loops or clearly exits
- Receipts show all item details for easy post-order confirmation

---

## 🖥️ How to Run

1. Open in IntelliJ or any Java IDE.
2. Run `app.Main.java`.
3. Choose an option and follow the prompts to build your sandwich or order a signature one.
4. Your receipt will print to the terminal and save to a `.txt` file automatically.

---
