package week_ten;

/**
 * Problem 1: Priority Token Insertion
 *
 * Task:
 * Implement a custom singly linked list supporting:
 * - addFirst(int token): adds at the front (priority)
 * - addLast(int token): adds at the end (normal)
 * - insertAt(int index, int token): inserts at 0-based index
 *
 * Complexity:
 * - addFirst: O(1)
 * - addLast:  O(1) (with tail reference) or O(N) (without tail)
 * - insertAt: O(index) <= O(N)
 * - Space:    O(1) auxiliary space per insertion
 */
public class PriorityTokenInsertion {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public PriorityTokenInsertion() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(int token) {
        Node newNode = new Node(token);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(int token) {
        Node newNode = new Node(token);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void insertAt(int index, int token) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }

        if (index == 0) {
            addFirst(token);
            return;
        }
        if (index == size) {
            addLast(token);
            return;
        }

        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        Node newNode = new Node(token);
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    public void printList() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        PriorityTokenInsertion list = new PriorityTokenInsertion();

        // start = 101 -> 102 -> 103
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        // addFirst(100), addLast(104), insertAt(2, 150)
        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        // Expected Output: 100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null
        System.out.print("Result: ");
        list.printList();
    }
}