
//FIFO


package logic;

public class Queue<T> {
    private QueueNode<T> front;
    private QueueNode<T> rear;
    private int size;

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(T value) {
        QueueNode<T> newNode = new QueueNode<>(value);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) return null;

        T value = front.data;
        front = front.next;

        if (front == null) rear = null; // queue became empty
        size--;
        return value;
    }

    public T peek() {
        return isEmpty() ? null : front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void printQueue() {
        System.out.print("Queue: ");
        QueueNode<T> current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}







