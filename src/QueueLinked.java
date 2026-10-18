
class Node {
    Node next, prev;
    Object data;
    
    Node (Object data) {
        this.data = data;
        this.next = this.prev = null;
    }
}


public class QueueLinked {   
    Node head, tail;
    int size;

    QueueLinked() {
        this.head = this.tail = null;
        this.size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void addFirst(Object data) {
        Node newNode = new Node(data);
        
        if (isEmpty()) {    
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        size++;
    }

    void removeLast() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
            return;
        }

        if (tail == head) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
    }

    void enqueue(Object data) {
        addFirst(data);
    }

    void dequeue() {
        removeLast();
    }

    void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
            return;
        }

        System.out.print("Queue: ");
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueLinked queue = new QueueLinked();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        queue.printQueue();

        queue.dequeue();
        queue.dequeue();

        queue.printQueue();

        queue.enqueue(60);
        queue.enqueue(70);

        queue.printQueue();
    }
}
