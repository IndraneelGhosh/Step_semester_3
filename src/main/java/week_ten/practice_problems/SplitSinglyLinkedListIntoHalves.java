package week_ten.practice_problems;

/**
 * Problem 5: Split Singly Linked List into Two Halves
 *
 * Task:
 * Implement static Node splitListInHalf(Node head) that splits a singly linked list into two halves.
 * If the list has an odd number of nodes, the first half contains the extra middle node.
 * Return the head of the second half. First half is properly terminated (last node's next is null).
 * Use fast and slow pointer technique.
 *
 * Complexity:
 * - Time: O(N) - single pass with fast and slow pointers.
 * - Space: O(1) - auxiliary space (modifies links in-place).
 */
public class SplitSinglyLinkedListIntoHalves {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node splitListInHalf(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        // Move fast by 2 and slow by 1
        // When fast reaches end (fast.next == null for odd, fast.next.next == null for even),
        // slow is at the end of the first half
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalfHead = slow.next;
        slow.next = null; // Terminate first half

        return secondHalfHead;
    }

    public static String listToString(Node head) {
        if (head == null) return "(empty)";
        Node curr = head;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Test Case 1: Odd length (5 nodes: 1 -> 2 -> 3 -> 4 -> 5)
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        Node second1 = splitListInHalf(head1);
        System.out.println("First half: " + listToString(head1) + " Returned: " + listToString(second1));
        // Expected: First half: 1 -> 2 -> 3 Returned: 4 -> 5

        // Test Case 2: Even length (4 nodes: 1 -> 2 -> 3 -> 4)
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);

        Node second2 = splitListInHalf(head2);
        System.out.println("First half: " + listToString(head2) + " Returned: " + listToString(second2));
        // Expected: First half: 1 -> 2 Returned: 3 -> 4
    }
}