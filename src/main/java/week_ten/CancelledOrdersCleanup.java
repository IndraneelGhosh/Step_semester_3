package week_ten;

/**
 * Problem 2: Cancelled Orders Cleanup
 *
 * Task:
 * Remove all nodes with a given order code from a singly linked list and return the (possibly new) head.
 * Custom Node class with data and next fields; do not use java.util.LinkedList.
 *
 * Complexity:
 * - Time Complexity: O(N) single linear scan.
 * - Additional Space Complexity: O(1) in-place pointer updates.
 */
public class CancelledOrdersCleanup {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node removeAll(Node head, int code) {
        // Step 1: Remove matching nodes from the front
        while (head != null && head.data == code) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        // Step 2: Remove matching nodes from the rest of the list
        Node prev = head;
        Node curr = head.next;

        while (curr != null) {
            if (curr.data == code) {
                prev.next = curr.next; // skip matching node
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return head;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static Node createList(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new Node(values[i]);
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // Sample 1: orders = 5 -> 3 -> 5 -> 8 -> 5, code = 5
        Node s1 = createList(new int[]{5, 3, 5, 8, 5});
        s1 = removeAll(s1, 5);
        System.out.print("Sample 1 (Expected 3 -> 8 -> null): ");
        printList(s1);

        // Sample 2: orders = 5 -> 5 -> 5, code = 5
        Node s2 = createList(new int[]{5, 5, 5});
        s2 = removeAll(s2, 5);
        System.out.print("Sample 2 (Expected null): ");
        printList(s2);
    }
}