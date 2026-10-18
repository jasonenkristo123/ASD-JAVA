class QueueArray {
    Object queue[];
    int max_size;
    int front;
    int rear;
    int size;

    QueueArray(int capacity) {
        this.max_size = capacity;
        this.queue = new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    boolean isFull() {
        return this.size == max_size;
    }

    boolean isEmpty() {
        return this.size == 0;
    }

    void enqueue(Object value) {
        if (isFull()) {
            System.out.println("Queue sudah full");
            return;
        }

        queue[rear] = value;
        rear = (rear + 1) % max_size;
        size++;
        System.out.println("Enqueue: " + value);
    }

    void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue sudah kosong");
            return;
        }

        Object item = queue[front];
        front = (front + 1) % max_size;
        size--;
        System.out.println("Dequeue: " + item);
    }

    void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
            return;
        }

        System.out.print("Queue: ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % max_size;
            System.out.print(queue[index] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueArray queue = new QueueArray(5);

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