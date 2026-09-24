public class CircularArrayQueue {
    int front, rear, size, itemCount;
    Object[] queue;

    public CircularArrayQueue(int size) {
        this.size = size;
        this.queue = new Object[size];
        this.front = 0;
        this.rear = -1;
        this.itemCount = 0;
    }

    void ArrayDoubling() {
        Object[] oldArray = queue;
        Object[] newQueue = new Object[size * 2];

        for (int i = 0; i < oldArray.length; i++) {
            newQueue[i] = oldArray[i];
        }

        this.queue = newQueue;
        this.front = 0;
        this.rear = itemCount - 1;
        this.size = size * 2;
    }

    boolean isFull() {
        return itemCount == size;
    }

    boolean isEmpty() {
        return itemCount == 0;
    }

    Object getFront() {
        if (itemCount == 0) {
            System.out.println("Queue is empty");
            return null;
        }
        return queue[front];
    }

    void makeEmpty() {
        front = 0;
        rear = -1;
        itemCount = 0;
    }

    void enqueue(Object data) {
        if (isFull()) {
            ArrayDoubling();
        }

        rear += 1;
        if (rear == size) {
            rear = 0;
        }

        queue[rear] = data;
        itemCount++;
    }

    void dequeue() {
        if (itemCount == 0) {
            System.out.println("Queue is empty");
            return;
        }

        queue[front] = null; 
        front = (front + 1) % size; 
        itemCount--;
    }

    void Display() {
        if (itemCount == 0) {
            System.out.println("Queue is empty");
            return;
        }

        for (int i = 0; i < itemCount; i++) {
            int index = (front + i) % size;
            System.out.print(queue[index] + " ");
        }
        System.out.println();
    }
}

class Main {
    public static void main(String[] args) {
        CircularArrayQueue queue = new CircularArrayQueue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(2);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.dequeue();
        queue.enqueue(50);
        queue.enqueue(111);
        queue.enqueue(222);
        queue.enqueue(500);
        queue.makeEmpty();

        queue.Display();
    }
}
