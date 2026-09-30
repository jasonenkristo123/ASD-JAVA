public class Stack {
    
}


class Node {
    Node next;
    Object data;

    public Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

class Stacks {
    Node top;
    Node head, tail;
    int size;

    public Stacks() {
        this.top = null;
        this.tail = null;
        this.head = null;
        this.size = 0;
    }

    void push(Object data) {
        Node newNode = new Node(data);

        if (size == 0) {
            head = tail = top = newNode;
        } else {
            newNode.next = head;
            head = newNode;
            top = newNode;
        }
        size++;
    }

    void pop() {
        if (size == 0) {
            System.out.println("data kosong bos");
            return;
        } else {
            head = head.next;
            top = head;
        }
        size--;
    }

    void peek() {
        if (size == 0) {
            System.out.println("data kosong bos");
            return;
        } else {
            System.out.println(top.data);
        }
    }

    void display() {
        if (size == 0) {
            System.out.println("data kosong bos");
            return;
        } else {
            Node temp = head;
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
        }
    }

    int size() {
        return size;
    }

    public static void main(String[] args) {
        Stacks stack = new Stacks();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();
        stack.peek();
        stack.pop();
        stack.display();
    }
}