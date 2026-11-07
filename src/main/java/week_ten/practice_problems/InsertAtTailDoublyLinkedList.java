package week_ten.practice_problems;

/**
 * Problem 2: Insert at Tail of Doubly Linked List (from Head)
 *
 * Task:
 * Implement static DNode insertAtTailDoubly(DNode head, int value) which inserts a new node
 * with the given value at the tail of a doubly linked list. Update all prev and next references.
 * Return the (possibly new) head of the list. Handle an empty list.
 *
 * Complexity:
 * - Time: O(N) to traverse to the tail
 * - Space: O(1) auxiliary space
 */
public class InsertAtTailDoublyLinkedList {

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

    public static DNode insertAtTailDoubly(DNode head, int value) {
        DNode newNode = new DNode(value);
        if (head == null) {
            return newNode;
        }

        DNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        newNode.prev = current;
        return head;
    }

    public static void printList(DNode head) {
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
        // Test Case 1: head = 1 <-> 2 <-> 3, value = 4
        DNode head = new DNode(1);
        DNode n2 = new DNode(2);
        DNode n3 = new DNode(3);
        head.next = n2; n2.prev = head;
        n2.next = n3; n3.prev = n2;

        head = insertAtTailDoubly(head, 4);
        printList(head); // Expected: 1 <-> 2 <-> 3 <-> 4

        // Test Case 2: head = null, value = 7
        DNode head2 = insertAtTailDoubly(null, 7);
        printList(head2); // Expected: 7
    }
}