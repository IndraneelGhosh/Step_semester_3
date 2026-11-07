package week_ten.practice_problems;

/**
 * Problem 8: Check if Singly Linked List is Empty
 *
 * Task:
 * Implement static boolean checkIfEmpty(Node head) that returns true if empty, false otherwise.
 *
 * Complexity:
 * - Time: O(1)
 * - Space: O(1)
 */
public class CheckIfSinglyLinkedListIsEmpty {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static boolean checkIfEmpty(Node head) {
        return head == null;
    }

    public static void main(String[] args) {
        System.out.println("Empty (null): " + checkIfEmpty(null));        // Expected: true
        System.out.println("Empty (node): " + checkIfEmpty(new Node(8))); // Expected: false
    }
}