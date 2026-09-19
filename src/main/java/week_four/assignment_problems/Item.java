package week_four.assignment_problems;

/**
 * A2. this Keyword for Canteen Inventory — Batch Restock
 *
 * Scenario:
 * Every Friday evening, the campus canteen restocks the same fixed quantity of
 * every item on the shelf at once, ahead of the weekend rush.
 *
 * Task:
 * - Define a class Item with fields String itemName and int stock.
 * - Write a constructor Item(String itemName, int stock) resolving clash with this.
 * - Add restock(int stock) that adds the parameter to this.stock.
 * - Loop through array of 4 items, call restock(20), and print each item's name and final stock.
 */
public class Item {
    String itemName;
    int stock;

    public Item(String itemName, int stock) {
        this.itemName = itemName;
        this.stock = stock;
    }

    public void restock(int stock) {
        this.stock += stock;
    }

    public void printFinalStock() {
        System.out.println(this.itemName + " | Final Stock: " + this.stock);
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item("Samosa", 15),
            new Item("Tea Powder", 40),
            new Item("Bread", 8),
            new Item("Biscuit Packs", 25)
        };

        for (Item item : items) {
            item.restock(20);
            item.printFinalStock();
        }
    }
}