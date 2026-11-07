package week_ten.practice_problems;

/**
 * Problem 9: Insert at End of Circular Singly Linked List
 *
 * Task:
 * Implement static Node insertAtEndCircular(Node head, int value) that inserts a new node
 * at the logical end of a circular singly linked list (its next points to head).
 * Return the (possibly new) head of the list. Handle initially empty list.
 *
 * Complexity:
 * - Time: O(N) to traverse to the end of the circular list.
 * - Space: O(1) auxiliary space.
 */
public class InsertAtEndCircularSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node insertAtEndCircular(Node head, int value) {
        Node newNode = new Node(value);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }

        Node current = head;
        while (current.next != head) {
            current = current.next;
        }

        current.next = newNode;
        newNode.next = head;

        return head;
    }

    public static void printCircular(Node head) {
        if (head == null) {
            System.out.println("(empty)");
            return;
        }
        Node curr = head;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(curr.data);
            sb.append(" -> ");
            curr = curr.next;
        } while (curr != head);
        sb.append("(back to ").append(head.data).append(")");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        // Test Case 1: head = 1 -> 2 -> 3 -> (back to 1), value = 4
        Node head1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        head1.next = n2;
        n2.next = n3;
        n3.next = head1;

        head1 = insertAtEndCircular(head1, 4);
        printCircular(head1); // Expected: 1 -> 2 -> 3 -> 4 -> (back to 1)

        // Test Case 2: head = null, value = 9
        Node head2 = insertAtEndCircular(null, 9);
        printCircular(head2); // Expected: 9 -> (back to 9)
    }
}