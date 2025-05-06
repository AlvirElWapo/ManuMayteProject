
package logic;

public class LinkedList {
    private LL_Node head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(int value) {
        LL_Node newLL_Node = new LL_Node(value);
        if (head == null) {
            head = newLL_Node;
        } else {
            LL_Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newLL_Node;
        }
        size++;
    }

    public boolean remove(int value) {
        if (head == null) return false;

        if (head.data == value) {
            head = head.next;
            size--;
            return true;
        }

        LL_Node current = head;
        while (current.next != null && current.next.data != value)
            current = current.next;

        if (current.next == null) return false;

        current.next = current.next.next;
        size--;
        return true;
    }

    public void printList() {
        LL_Node current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public int size() {
        return size;
    }
}
