package week_ten.practice_problems;

/**
 * Problem 10: Delete Last Node from Doubly Linked List (from Head)
 *
 * Task:
 * Implement static DNode deleteLastNodeDoubly(DNode head) that removes the last node.
 * Return the (possibly new) head.
 * Handle empty list, single-node list, and multi-node list. Assume only head is given.
 *
 * Complexity:
 * - Time: O(N) to traverse to the end of the doubly linked list.
 * - Space: O(1) auxiliary space.
 */
public class DeleteLastNodeDoublyLinkedList {

    public static class DNode {
        public int data;
        public DNode prev;
        public DNode next;

        public DNode(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public static DNode deleteLastNodeDoubly(DNode head) {
        // Empty list
        if (head == null) {
            return null;
        }

        // Single-node list
        if (head.next == null) {
            return null;
        }

        // Traverse to the last node
        DNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        // Detach last node from its predecessor
        DNode predecessor = current.prev;
        if (predecessor != null) {
            predecessor.next = null;
        }
        current.prev = null;

        return head;
    }

    public static void printList(DNode head) {
        if (head == null) {
            System.out.println("(empty list)");
            return;
        }
        DNode curr = head;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(" <-> ");
            }
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        // Test Case 1: head = 1 <-> 2 <-> 3
        DNode head1 = new DNode(1);
        DNode n2 = new DNode(2);
        DNode n3 = new DNode(3);
        head1.next = n2; n2.prev = head1;
        n2.next = n3; n3.prev = n2;

        head1 = deleteLastNodeDoubly(head1);
        printList(head1); // Expected: 1 <-> 2

        // Test Case 2: head = 5 (single node)
        DNode head2 = new DNode(5);
        head2 = deleteLastNodeDoubly(head2);
        printList(head2); // Expected: (empty list)
    }
}