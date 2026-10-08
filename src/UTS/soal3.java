package UTS;

public class soal3 {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.lakukanAksi("KETIK", "Halo");
        editor.lakukanAksi("KETIK", " Dunia");
        editor.lakukanAksi("HAPUS", "Dunia");

        editor.undo();
        editor.undo();

        editor.redo();

        editor.lakukanAksi("KETIK", "!!!");

        editor.redo();

        editor.undo();
        editor.undo();
        editor.undo();
        editor.undo();
    }
}

class AksiText {
    String tipeAksi;
    String konten;

    public AksiText(String tipeAksi, String konten) {
        this.tipeAksi = tipeAksi;
        this.konten = konten;
    }
}

class StackNode {
    AksiText data;
    StackNode next;

    public StackNode(AksiText data) {
        this.data = data;
        this.next = null;
    }
}

class ActionStack {
    private StackNode top;
    private int size;

    ActionStack() {
        this.top = null;
        this.size = 0;
    }

    public void push(AksiText aksi) {
        StackNode baru = new StackNode(aksi);
        baru.next = top;
        top = baru;
        size++;
    }

    public AksiText pop() {
        if (isEmpty()) {
            return null;
        }
        AksiText aksi = top.data;
        top = top.next;
        size--;
        return aksi;
    }

    public AksiText peek() {
        return isEmpty() ? null : top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        top = null;
        size = 0;
    }
}

class TextEditor {
    private ActionStack undoStack = new ActionStack();
    private ActionStack redoStack = new ActionStack();

    public void lakukanAksi(String tipe, String konten) {
        undoStack.push(new AksiText(tipe, konten));
        redoStack.clear();
        System.out.println("Aksi dilakukan: " + tipe + " \"" + konten + "\"");
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Tidak ada aksi yang bisa di-undo.");
            return;
        }
        AksiText aksi = undoStack.pop();
        System.out.println("Undo: membatalkan " + aksi.tipeAksi + " \"" + aksi.konten + "\"");
        redoStack.push(aksi);
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Tidak ada aksi yang bisa di-redo.");
            return;
        }
        AksiText aksi = redoStack.pop();
        System.out.println("Redo: menerapkan kembali " + aksi.tipeAksi + " \"" + aksi.konten + "\"");
        undoStack.push(aksi);
    }
}