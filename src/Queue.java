
class Node {
    Node next;
    Object data;

    public Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

public class Queue {
    Node head, tail;
    int size;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void enqueue(Object data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        head = head.next;
        size--;
    }

    void getFront() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        System.out.println(head.data);
    }

    void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}

class Main {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);  
        queue.enqueue(3);
        queue.printQueue(); 

        queue.getFront();
        queue.dequeue();
        queue.printQueue();

        int arr[] = {4, 5, 6};
        System.out.print(arr[-1]);


    }
}
