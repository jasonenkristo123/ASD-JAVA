package UTS;

public class soal4 {
    public static void main(String[] args) {
        BankQueue antrean = new BankQueue();

        antrean.enqueue(new Nasabah("R001", "Andi", "REGULER"));
        antrean.enqueue(new Nasabah("R002", "Budi", "REGULER"));
        antrean.enqueue(new Nasabah("P001", "Ibu Siti", "PRIORITAS"));
        antrean.enqueue(new Nasabah("R003", "Citra", "REGULER"));
        antrean.enqueue(new Nasabah("P002", "Pak Hasan", "PRIORITAS"));

        antrean.tampilkanAntrean();

        antrean.dequeue();
        antrean.dequeue();

        antrean.tampilkanAntrean();

        antrean.enqueue(new Nasabah("P003", "Ibu Ani", "PRIORITAS"));
        antrean.tampilkanAntrean();

        antrean.dequeue();
        antrean.dequeue();
        antrean.dequeue();
        antrean.dequeue();
        antrean.tampilkanAntrean();
    }
}

class Nasabah {
    String nomorAntrean;
    String nama;
    String tipeLayanan;   

    public Nasabah(String nomorAntrean, String nama, String tipeLayanan) {
        this.nomorAntrean = nomorAntrean;
        this.nama = nama;
        this.tipeLayanan = tipeLayanan;
    }
}

class NasabahNode {
    Nasabah data;
    NasabahNode next;

    public NasabahNode(Nasabah data) {
        this.data = data;
        this.next = null;
    }
}

class BankQueue {
    private NasabahNode head, tail;
    private int size;

    BankQueue() {
        this.head = this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(Nasabah n) {
        NasabahNode baru = new NasabahNode(n);

        if (head == null) {
            head = tail = baru;
            size++;
            System.out.println("Masuk antrean: " + n.nama + " (" + n.tipeLayanan + ")");
            return;
        }

        if (n.tipeLayanan.equals("PRIORITAS")) {
            if (!head.data.tipeLayanan.equals("PRIORITAS")) {
                baru.next = head;
                head = baru;
            } else {
                NasabahNode curr = head;
                while (curr.next != null && curr.next.data.tipeLayanan.equals("PRIORITAS")) {
                    curr = curr.next;
                }
                baru.next = curr.next;
                curr.next = baru;
                if (curr == tail) {
                    tail = baru; 
                }
            }
        } else {
            tail.next = baru;
            tail = baru;
        }

        size++;
        System.out.println("Masuk antrean: " + n.nama + " (" + n.tipeLayanan + ")");
    }

    public Nasabah dequeue() {
        if (isEmpty()) {
            System.out.println("Antrean kosong.");
            return null;
        }

        Nasabah dilayani = head.data;
        head = head.next;
        if (head == null) {
            tail = null; 
        }
        size--;

        System.out.println("Melayani: " + dilayani.nomorAntrean + " - "
                + dilayani.nama + " (" + dilayani.tipeLayanan + ")");
        return dilayani;
    }

    public void tampilkanAntrean() {
        if (isEmpty()) {
            System.out.println("Antrean kosong.");
            return;
        }

        NasabahNode curr = head;
        int no = 1;
        while (curr != null) {
            System.out.println(no + ". " + curr.data.nomorAntrean + " - "
                    + curr.data.nama + " (" + curr.data.tipeLayanan + ")");
            curr = curr.next;
            no++;
        }
        System.out.println("Total antrean: " + size);
    }
}