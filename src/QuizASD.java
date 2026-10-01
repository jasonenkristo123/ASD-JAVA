public class QuizASD {
}

class Array {
    Object array[];
    int size;

    public Array(int ArraySize) {
        this.size = 0;
        this.array = new Object[ArraySize];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    boolean isFull() {
        return size == array.length;
    }

    void resizeArray() {
        Object newArray[] = new Object[size * 2];
        for(int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        this.array = newArray;
    }

    void tambahDepan(Object data) {
        if (isEmpty()) {
            array[0] = data;
            size++;
            return;
        }

        if (isFull()) {
            resizeArray();
        }

        for (int i = size; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = data;
        size++;
    }

    void tambahBelakang(Object data) {
        if (isFull()) {
            resizeArray();
        }

        array[size] = data;
        size++;
    }

    void hapusDepan() {
        if (isEmpty()) {
            System.out.println("Array is empty");
            return;
        }
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i+1];
        }
        size--;
    }

    void hapusBelakang() {
        if (isEmpty()) {
            System.out.println("Array is empty");
            return;
        }
        array[size-1] = null;
        size--;
    }

    void printArray() {
        if (isEmpty()) {
            System.out.println("Array Empty");
            return;
        } else {
            for (int i = 0; i < size; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Array arr = new Array(5);
        arr.tambahDepan(10);
        arr.tambahDepan(20);
        arr.tambahDepan(30);
        arr.tambahBelakang(40);
        arr.tambahBelakang(60);
        arr.tambahBelakang(70);
        arr.printArray();
        arr.hapusBelakang();
        arr.hapusDepan();
        arr.printArray();
    }
}
