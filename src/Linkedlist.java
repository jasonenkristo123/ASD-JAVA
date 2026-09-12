
class Node {
    Object data;
    Node next;

    public Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

class SingleLinkedlist {
    Node head, tail = null;
    int size = 0;

    public SingleLinkedlist() {
        head = null;
        tail = null;
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

        while (temp != null) {
            if ((temp.data.equals(key)) && (temp == head)) {
                addFirst(data);
                return;
            } else if (temp.next.data == key) {
                newNode.next = temp.next;
                temp.next = newNode;
            }
            temp = temp.next;
        }

        size++;
    }

    void addAfter(Object key, Object data) {
        Node temp = head;
        Node newNode = new Node(data);

        if (isEmpty()) {
            System.out.println("Kosong");
            return;
        } 

        while (temp != null) {
            if (temp.data.equals(key) && (temp == head)) {
                addLast(data);
                return;
            } else if (temp.data.equals(key)) {
                newNode.next = temp.next;
                temp.next = newNode;
            }

            temp = temp.next;
        }
        size++;
    }

    void deleteFirst() {
        if (isEmpty()) {
            System.out.println("Kosong");
            return;
        }
        head = head.next;
        size--;
    }
    
    void deleteLast() {
        if (isEmpty()) {
            System.out.println("Kosong");
            return;
        }

        if (head == tail) {
            head = tail = null;
        } else {
            Node temp = head;
            while (temp.next != tail) {
                temp = temp.next;
            } 

            tail = temp;
            tail.next = null;
        }
        size--;
    }

    void delete(Object key) {
        if (isEmpty()) {
            System.out.println("Kosong");
            return;
        }

        if (head.data.equals(key)) {
            head = head.next;
        } else {
            Node temp = head;
            
            if (temp.next == null) {
                System.out.println("Data tidak ditemukan");
                return;
            }

            while (temp.data != null && temp.next.data != key) {
                temp = temp.next;
            }

            temp.next = temp.next.next;

        }
        size--;
    }

    void showList() {
        if (isEmpty()) {
            System.out.println("kosong");
            return;
        }

        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + ", ");
            temp = temp.next;
        }

        System.out.println();
    }
}

class Main {
    public static void main(String[] args) {
        SingleLinkedlist list = new SingleLinkedlist();
        list.addFirst(10);
        list.addLast(20);
        list.addLast(40);
        list.addLast(50);

        list.showList();
        list.addBefore(10, 100);
        list.showList();

        list.addAfter(10, 200);
        list.showList();

        list.deleteFirst();
        list.deleteLast();
        list.showList();
    }
}
