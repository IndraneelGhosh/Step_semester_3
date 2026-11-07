package week_ten.practice_problems;

/**
 * Problem 4: Delete N-th Node from Singly Linked List
 *
 * Task:
 * Implement static Node deleteNthNode(Node head, int n) (1-based index).
 * Delete the node at the n-th position and return the head of the modified list.
 * 1 <= n <= length of list. Handle special case n = 1 (deleting the head).
 *
 * Complexity:
 * - Time: O(n) <= O(N)
 * - Space: O(1)
 */
public class DeleteNthNodeSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node deleteNthNode(Node head, int n) {
        if (head == null) {
            return null;
        }

        // Special case: deleting head
        if (n == 1) {
            return head.next;
        }

        // Traverse to (n-1)-th node
        Node current = head;
        for (int i = 0; i < n - 2 && current != null && current.next != null; i++) {
            current = current.next;
        }

        if (current != null && current.next != null) {
            current.next = current.next.next;
        }

        return head;
    }

    public static void printList(Node head) {
        Node curr = head;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        // Test Case 1: head = 10 -> 20 -> 30 -> 40, n = 3
        Node head1 = new Node(10);
        head1.next = new Node(20);
        head1.next.next = new Node(30);
        head1.next.next.next = new Node(40);

        head1 = deleteNthNode(head1, 3);
        printList(head1); // Expected: 10 -> 20 -> 40

        // Test Case 2: head = 10 -> 20 -> 30 -> 40, n = 1
        Node head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30);
        head2.next.next.next = new Node(40);

        head2 = deleteNthNode(head2, 1);
        printList(head2); // Expected: 20 -> 30 -> 40
    }
}