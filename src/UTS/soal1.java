package UTS;

public class soal1 {
    public static void main(String[] args) {
        PlaylistADT playlist = new SinglyLinkedListPlaylist();

        playlist.tambahLagu(1, "Lagu A", "Penyanyi X", 210);
        playlist.tambahLagu(2, "Lagu B", "Penyanyi Y", 185);

        playlist.tampilkanPlaylist();
        playlist.hapusLagu(2);
        playlist.tampilkanPlaylist();
        playlist.putarSelanjutnya();
        playlist.tampilkanPlaylist();
        playlist.putarSelanjutnya();
        playlist.tampilkanPlaylist();
    }
}

interface PlaylistADT {
    void tambahLagu(int id, String judul, String penyanyi, int durasi);

    void hapusLagu(int id);

    void putarSelanjutnya();

    void tampilkanPlaylist();
}

class LaguNode {
    int id;
    String judul;
    String penyanyi;
    int durasiDetik;
    LaguNode next;

    public LaguNode(int id, String judul, String penyanyi, int durasiDetik) {
        this.id = id;
        this.judul = judul;
        this.penyanyi = penyanyi;
        this.durasiDetik = durasiDetik;
        this.next = null;
    }
}

class SinglyLinkedListPlaylist implements PlaylistADT {
    LaguNode head, tail;
    int size;

    SinglyLinkedListPlaylist() {
        this.head = this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void tambahLagu(int id, String judul, String penyanyi, int durasiDetik) {
        LaguNode newNode = new LaguNode(id, judul, penyanyi, durasiDetik);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void hapusLagu(int id) {
        if (isEmpty()) {
            System.out.println("Playlist masih kosong");
            return;
        }

        if (head.id == id) {
            head = head.next;
            if (head == null) {
                tail = null;
            }

            size--;
            System.out.println("Lagu dengan id " + id + " dihapus. ");
            return;
        }

        LaguNode prev = head;
        LaguNode curr = head.next;

        while (curr != null && curr.id != id) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == null) {
            System.out.println("Lagu dengan id " + id + " tidak ditemukan.");
            return;
        }

        prev.next = curr.next;
        if (curr == tail) {
            tail = prev;
        }

        size--;
        System.out.println("Lagu dengan id " + id + " dihapus.");
    }

    @Override
    public void putarSelanjutnya() {
        if (head == null) {
            System.out.println("Playlist kosong, tidak ada lagu untuk diputar.");
            return;
        }

        System.out.println("Memutar: " + head.judul + " - " + head.penyanyi
                + " (" + head.durasiDetik + " detik)");

        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
    }

    @Override
    public void tampilkanPlaylist() {
        if (head == null) {
            System.out.println("Playlist kosong.");
            return;
        }

        LaguNode curr = head;
        int no = 1;
        while (curr != null) {
            System.out.println(no + ". [" + curr.id + "] " + curr.judul
                    + " - " + curr.penyanyi + " (" + curr.durasiDetik + " detik)");
            curr = curr.next;
            no++;
        }
        System.out.println("Total lagu: " + size);
    }
}
