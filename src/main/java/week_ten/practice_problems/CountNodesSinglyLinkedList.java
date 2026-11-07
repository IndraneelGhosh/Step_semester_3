package week_ten.practice_problems;

/**
 * Problem 7: Count Nodes in Singly Linked List
 *
 * Task:
 * Implement static int countNodes(Node head) that returns the total number of nodes.
 * An empty list returns 0.
 *
 * Complexity:
 * - Time: O(N)
 * - Space: O(1)
 */
public class CountNodesSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static int countNodes(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static void main(String[] args) {
        // Test Case 1: 5 -> 10 -> 15
        Node head1 = new Node(5);
        head1.next = new Node(10);
        head1.next.next = new Node(15);
        System.out.println("Count: " + countNodes(head1)); // Expected: 3

        // Test Case 2: head = null
        System.out.println("Count: " + countNodes(null));  // Expected: 0
    }
}