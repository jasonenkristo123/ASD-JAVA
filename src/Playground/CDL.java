package Playground;

public class CDL {

}

class Node {
    Object data;
    Node next, prev;

    public Node(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class CircularLL {
    Node head, tail;
    int size;

    public CircularLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isExist(Object data) {
        if (isEmpty()) {
            return false;
        }

        Node temp = head;

        do {
            if (temp.data.equals(data)) {
                return true;
            }
            temp = temp.next;

        } while (temp != head);
        return false;
    }

    void addFirst(Object data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            head = tail = newNode;
            newNode.next = newNode.prev = newNode;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        }
        size++;
    }

    void addLast(Object data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            head = tail = newNode;
            newNode.next = newNode.prev = newNode;
        } else {
            newNode.prev = tail;
            newNode.next = head;
            tail.next = newNode;
            tail = newNode;
            head.prev = newNode;
        }
        size++;
    }

    void insertAfter(Object targetData, Object newData) {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        if (!isExist(targetData)) {
            System.out.println("Target data not found");
            return;
        }

        Node newNode = new Node(newData);
        Node temp = head;

        if (size == 1 && head.data.equals(targetData)) {
            addLast(newData);
            return;
        }

        do {
            if (temp.data.equals(targetData)) {
                if (temp == tail) {
                    addLast(newData);
                } else {
                    newNode.next = temp.next;
                    newNode.prev = temp;
                    temp.next.prev = newNode;
                    temp.next = newNode;
                }
                size++;
            }
            temp = temp.next;

        } while (temp != head);
    }

    void delete(Object targetData) {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        if (!isExist(targetData)) {
            System.out.println("Target data not found");
            return;
        }

        Node temp = head;

        if (size == 1 && head.data.equals(targetData)) {
            head = tail = null;
            size--;
            return;
        } else if (head.data.equals(targetData)) {
            head = head.next;
            head.prev = tail;
            tail.next = head;
            size--;
        } else if (tail.data.equals(targetData)) {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
            size--;
        } else {
            do {
                if (temp.data.equals(targetData)) {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                    size--;
                }
                temp = temp.next;
            } while (temp != head);
        }
    }

    void printForward() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        Node temp = head;

        do {
            if (temp == head) {
                System.out.print(temp.data);
            } else {
                System.out.print(" -> " + temp.data);
            }
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    void printBackward() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        Node temp = tail;

        do {
            if (temp == tail) {
                System.out.print(temp.data);
            } else {
                System.out.print(" -> " + temp.data);
            }
            temp = temp.prev;
        } while (temp != tail);
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLL circularList = new CircularLL();

        circularList.addFirst(1);
        circularList.addLast(2);
        circularList.addLast(3);
        circularList.insertAfter(2, 4);

        circularList.printForward();
        circularList.printBackward();
    }
}