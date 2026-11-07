package week_ten;

/**
 * Problem 4: Train Coaches Both Ways
 *
 * Task:
 * Maintain a train as a doubly linked list with head and tail references.
 * Implement addLast, remove(String name), printForward, and printBackward.
 *
 * Complexity:
 * - Finding the coach: O(N) linear search.
 * - Unlinking once found: O(1) pointer updates on neighbors (node.prev.next and node.next.prev).
 * - Space Complexity: O(1) auxiliary space.
 */
public class TrainCoachesBothWays {

    static class Node {
        String name;
        Node prev;
        Node next;

        public Node(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;

    public TrainCoachesBothWays() {
        this.head = null;
        this.tail = null;
    }

    public void addLast(String name) {
        Node newNode = new Node(name);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public boolean remove(String name) {
        Node curr = head;
        while (curr != null) {
            if (curr.name.equals(name)) {
                // Fix left neighbor or head
                if (curr == head) {
                    head = curr.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }
                } else {
                    curr.prev.next = curr.next;
                }

                // Fix right neighbor or tail
                if (curr == tail) {
                    tail = curr.prev;
                    if (tail != null) {
                        tail.next = null;
                    } else {
                        head = null;
                    }
                } else {
                    curr.next.prev = curr.prev;
                }

                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public void printForward() {
        System.out.print("Forward: ");
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.name);
            if (curr.next != null) {
                System.out.print(" <-> ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    public void printBackward() {
        System.out.print("Backward: ");
        Node curr = tail;
        while (curr != null) {
            System.out.print(curr.name);
            if (curr.prev != null) {
                System.out.print(" <-> ");
            }
            curr = curr.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TrainCoachesBothWays train = new TrainCoachesBothWays();

        // coaches = Engine, A1, B1, B2, Guard
        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        // remove B1
        train.remove("B1");

        // Expected Output:
        // Forward: Engine <-> A1 <-> B2 <-> Guard
        // Backward: Guard <-> B2 <-> A1 <-> Engine
        train.printForward();
        train.printBackward();
    }
}