import java.util.Scanner;

public class UndoRedoStack {
}

class Node {
    String data;
    Node next;

    public Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedListStack {
    Node top, head, tail;

    LinkedListStack() {
        this.head = this.tail = this.top = null;
    }

    boolean isEmpty() {
        return this.top == null;
    }

    public void push(String data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = top = newNode;
        } else {
            newNode.next = head;
            head = newNode;
            top = head;
        }
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }

        String data = top.data;
        head = head.next;
        top = head;

        if (head == null) {
            tail = null;
        }

        return data;
    }

    public void clear() {
        head = null;
        tail = null;
        top = null;
    }

    public void printStack() {
        Node current = top;
        if (current == null) {
            System.out.println("[ Kosong ]");
            return;
        }
        System.out.print("[ TOP -> ");
        while (current != null) {
            System.out.print("\"" + current.data + "\"" + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println(" ]");
    }
}
 class TextEditorInteractive {
    private LinkedListStack undoStack;
    private LinkedListStack redoStack;

    public TextEditorInteractive() {
        undoStack = new LinkedListStack();
        redoStack = new LinkedListStack();
    }

    public void write(String text) {
        undoStack.push(text);
        redoStack.clear();
        System.out.println("-> Menambahkan: \"" + text + "\"");
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("-> Gagal Undo: Stack kosong.");
            return;
        }
        String lastAction = undoStack.pop();
        redoStack.push(lastAction);
        System.out.println("-> Undo: Membatalkan \"" + lastAction + "\"");
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("-> Gagal Redo: Stack kosong.");
            return;
        }
        String restoredAction = redoStack.pop();
        undoStack.push(restoredAction);
        System.out.println("-> Redo: Mengembalikan \"" + restoredAction + "\"");
    }

    public void displayStatus() {
        System.out.println("\n-------------------------------------------");
        System.out.print("Undo Stack : ");
        undoStack.printStack();
        System.out.print("Redo Stack : ");
        redoStack.printStack();
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {
        TextEditorInteractive editor = new TextEditorInteractive();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Text Editor Undo Redo");

        while (running) {
            editor.displayStatus();
            System.out.println("PILIH MENU:");
            System.out.println("1. Ketik Teks (Write)");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Keluar");
            System.out.print("Pilihan Anda (1-4): ");

            String inputPilihan = scanner.nextLine();

            switch (inputPilihan) {
                case "1":
                    System.out.print("Masukkan kata/kalimat: ");
                    String text = scanner.nextLine();
                    editor.write(text);
                    break;
                case "2":
                    editor.undo();
                    break;
                case "3":
                    editor.redo();
                    break;
                case "4":
                    running = false;
                    System.out.println("\nProgram selesai. Terima kasih!");
                    break;
                default:
                    System.out.println("-> Pilihan tidak valid! Masukkan angka 1-4.");
            }
        }

        scanner.close();
    }
}
