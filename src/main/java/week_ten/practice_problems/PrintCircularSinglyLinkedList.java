package week_ten.practice_problems;

/**
 * Problem 1: Print Circular Singly Linked List
 *
 * Task:
 * Implement static void printCircular(Node head) for a circular singly linked list.
 * The function should print the data of each node exactly once, in the order of traversal,
 * separated by a space. After printing all elements, it should print a newline.
 * Terminate after visiting all nodes in one full cycle, without relying on a null check.
 * Handle an empty list by printing only a newline.
 *
 * Complexity:
 * - Time: O(N)
 * - Space: O(1)
 */
public class PrintCircularSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void printCircular(Node head) {
        if (head == null) {
            System.out.println();
            return;
        }

        Node current = head;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != head);

        System.out.println();
    }

    public static void main(String[] args) {
        // Test Case 1: 10 -> 20 -> 30 -> (back to 10)
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;

        System.out.print("Output 1: ");
        printCircular(n1); // Expected: 10 20 30

        // Test Case 2: empty list
        System.out.print("Output 2 (empty): ");
        printCircular(null); // Expected: blank line
    }
}