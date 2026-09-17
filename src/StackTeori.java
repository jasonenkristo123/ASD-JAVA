// import java.util.EmptyStackException;

// public class StackTeori {    
// }

// class Node {
//     Object data;
//     Node next;

//     public Node(Object data) {
//         this.data = data;
//         this.next = null;
//     }

//     public Node(Object data, Node next) {
//         this.data = data;
//         this.next = next;
//     }
// }

// class Stack {
//     Node top, head, tail;

//     Stack() {
//         this.head = this.tail = this.top = null;
//     }

//     boolean isEmpty() {
//         return this.top == null;
//     }

//     void addLast(Node input) {
//         if (isEmpty()) {
//             head = tail = input;
//         } else {
//             tail.next = input;
//             tail = input;
//         }
//     }
//     void push(Node input) {
//         addLast(input);
//         top = tail;
//     }

//     void removeLast() {
//         Node temp = head;

//         if (tail == head) {
//             head = tail = null;
//         } else {
//             while (temp.next != tail) {
//                 temp = temp.next;
//             }

//             temp.next = null;
//             tail = temp;
//             temp = null;
//         }
//     }

//     Object pop() {
//         if (isEmpty()) {
//             throw new EmptyStackException();
//         }

//         Object tampungData = top.data;
//         removeLast();
//         top = tail;
//         return tampungData; 
//     }

//     void peek() {
//         if (isEmpty()) {
//             throw new EmptyStackException();
//         }

//         System.out.println(top.data);
//     }

//     void print() {
//         Node temp = head;

//         while (temp != null) {
//             System.out.print(temp.data + " -> ");
//             temp = temp.next;
//         }

//         System.out.println("null");
//     }
// }

// class Main {
//     public static void main(String[] args) {
//         Stack stack = new Stack();
//         stack.push(new Node(1));
//         stack.push(new Node(2));
//         stack.push(new Node(3));
//         stack.peek();
//         stack.pop();
//         stack.print();
//         stack.push(new Node(10));
//         stack.print();
//     }
// }
