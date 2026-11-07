package week_ten.practice_problems;

/**
 * Problem 3: Remove First K Nodes from Singly Linked List
 *
 * Task:
 * Implement static Node removeFirstKNodes(Node head, int k) that removes the first k nodes.
 * If k is >= total length, the entire list becomes empty (null).
 * Return the new head. Assume k >= 0.
 *
 * Complexity:
 * - Time: O(min(k, N))
 * - Space: O(1)
 */
public class RemoveFirstKNodesSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node removeFirstKNodes(Node head, int k) {
        if (head == null || k <= 0) {
            return head;
        }

        Node current = head;
        for (int i = 0; i < k && current != null; i++) {
            current = current.next;
        }

        return current;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("(empty list)");
            return;
        }
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
        // Test Case 1: head = 1 -> 2 -> 3 -> 4 -> 5, k = 2
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        Node res1 = removeFirstKNodes(head1, 2);
        printList(res1); // Expected: 3 -> 4 -> 5

        // Test Case 2: head = 1 -> 2, k = 5
        Node head2 = new Node(1);
        head2.next = new Node(2);

        Node res2 = removeFirstKNodes(head2, 5);
        printList(res2); // Expected: (empty list)
    }
}