package week_ten;

/**
 * Problem 5: Round-Robin Game Turns
 *
 * Task:
 * Players take turns in a fixed circle modeled as a circular singly linked list
 * with a tail reference (where tail.next is head).
 * Implement addLast(String name) and printTurns(int turns).
 *
 * Complexity:
 * - Adding a player (addLast): O(1) with tail reference.
 * - Printing turns: O(T) where T is the number of turns.
 * - Space Complexity: O(1) auxiliary space beyond player nodes.
 */
public class RoundRobinGameTurns {

    static class Node {
        String name;
        Node next;

        public Node(String name) {
            this.name = name;
            this.next = null;
        }
    }

    private Node tail;

    public RoundRobinGameTurns() {
        this.tail = null;
    }

    public void addLast(String name) {
        Node newNode = new Node(name);
        if (tail == null) {
            tail = newNode;
            tail.next = tail; // Points to itself
        } else {
            newNode.next = tail.next; // New node points to head
            tail.next = newNode;      // Old tail points to new node
            tail = newNode;           // New node becomes tail
        }
    }

    public void printTurns(int turns) {
        if (tail == null || turns <= 0) {
            return;
        }

        Node curr = tail.next; // head of circular list
        for (int i = 0; i < turns; i++) {
            System.out.print(curr.name);
            if (i < turns - 1) {
                System.out.print(" ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RoundRobinGameTurns game = new RoundRobinGameTurns();

        // players = Asha, Ravi, Neha
        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        // turns = 7
        // Expected Output: Asha Ravi Neha Asha Ravi Neha Asha
        System.out.print("Turns Output: ");
        game.printTurns(7);
    }
}