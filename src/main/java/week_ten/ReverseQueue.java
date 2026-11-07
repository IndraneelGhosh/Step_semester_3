package week_ten;

/**
 * Problem 3: Reverse the Queue
 *
 * Task:
 * Reverse a singly linked list in place without allocating any new nodes.
 *
 * Complexity:
 * - Time Complexity: O(N) single linear pass.
 * - Additional Space Complexity: O(1) in-place pointer reversal using three references.
 */
public class ReverseQueue {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node reverse(Node head) {
        Node prev = null;
        Node curr = head;
        Node next = null;

        while (curr != null) {
            next = curr.next; // Save next node
            curr.next = prev; // Reverse current node's pointer
            prev = curr;      // Move prev one step forward
            curr = next;      // Move curr one step forward
        }

        return prev; // New head of the reversed list
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
        // Sample 1: queue = 1 -> 2 -> 3 -> 4
        Node q1 = createList(new int[]{1, 2, 3, 4});
        q1 = reverse(q1);
        System.out.print("Sample 1 (Expected 4 -> 3 -> 2 -> 1 -> null): ");
        printList(q1);

        // Sample 2: queue = 9
        Node q2 = createList(new int[]{9});
        q2 = reverse(q2);
        System.out.print("Sample 2 (Expected 9 -> null): ");
        printList(q2);
    }
}