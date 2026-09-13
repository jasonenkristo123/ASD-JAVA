class Node {
    Object data;
    Node next;

    public Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head, tail;
    int size = 0;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    void addFirst(Object data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        size++;
    }

    void addLast(Object data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    void addBefore(Object key, Object data) {
        Node temp = head;
        Node newNode = new Node(data);

        if (isEmpty()) {
            System.out.println("tidak ada key");
        } else if (head.data.equals(key)) {
            addFirst(data);
            size++;
        } else {
            while (temp != null && !temp.next.equals(key)) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
            
            size++;
        }
    }

    void addAfter(Object key, Object data) {
        Node temp = head;
        Node newNode = new Node(data);

        if (isEmpty()) {
            System.out.println("No key");
        } else if (head.equals(key)) {
            addFirst(data);
            size++;
        } else {
            while (temp != null && !temp.data.equals(key)) {
                temp = temp.next;
            }

            newNode.next = temp.next;
            temp.next = newNode;

            if (temp.equals(tail)) {
                tail = newNode;
            }
            
            size++;
        }
    }

    void deleteFirst() {
        if (isEmpty()) {
            System.out.println("tidak ada data");
        }

        if (head.equals(tail)) {
            head = tail = null;
        } else {
            head = head.next;
        }
        size--;
    }
    
    void deleteLast() {
        if (isEmpty()) {
            System.out.println("Tidak ada data");
        } else if (head.equals(tail)) {
            head = tail = null;
            size--;
        } else {
            Node temp = head;

            while (!temp.next.equals(tail)) {
                temp = temp.next;
            }

            tail = temp;
            temp.next = null;

            size--;
        }
    }

    void Display() {
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
        LinkedList list = new LinkedList();

        list.addFirst(1);
        list.addFirst(2);
        list.addLast(3);
        list.addLast(4);

        list.Display();

        list.deleteFirst();
        list.Display();

        list.deleteLast();
        list.Display();
        list.addBefore(1, 5);
        list.Display();

        
    }
}