package week_ten.practice_problems;

/**
 * Problem 6: Reverse First K Nodes of Singly Linked List
 *
 * Task:
 * Implement static Node reverseFirstKNodes(Node head, int k) that reverses only the first k nodes.
 * The remaining nodes (from k+1 onwards) remain in original relative order.
 * Return the new head. 1 <= k <= length of list.
 *
 * Complexity:
 * - Time: O(k)
 * - Space: O(1) auxiliary space (in-place link reversal)
 */
public class ReverseFirstKNodesSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node reverseFirstKNodes(Node head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        Node current = head;
        Node prev = null;
        Node next = null;
        int count = 0;

        // Reverse first k nodes
        while (current != null && count < k) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        // head was the original first node, now the tail of the reversed prefix
        // Connect it to the (k+1)-th node (current)
        head.next = current;

        // prev is the new head of the reversed prefix
        return prev;
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
        // Test Case 1: head = 1 -> 2 -> 3 -> 4 -> 5, k = 3
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        head1 = reverseFirstKNodes(head1, 3);
        printList(head1); // Expected: 3 -> 2 -> 1 -> 4 -> 5

        // Test Case 2: head = 1 -> 2 -> 3 -> 4 -> 5, k = 5
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);

        head2 = reverseFirstKNodes(head2, 5);
        printList(head2); // Expected: 5 -> 4 -> 3 -> 2 -> 1
    }
}