package UTS;

public class soal2 {
    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();

        browser.kunjungi("https://google.com", "Google");
        browser.kunjungi("https://youtube.com", "YouTube");
        browser.kunjungi("https://github.com", "GitHub");
        browser.kunjungi("https://stackoverflow.com", "Stack Overflow");

        browser.cetakRiwayat();

        browser.back();
        browser.back();
        browser.cetakRiwayat();

        browser.forward();  
        browser.cetakRiwayat();

        browser.kunjungi("https://detik.com", "Detik");
        browser.cetakRiwayat();

        browser.forward();

        browser.back();
        browser.back();
        browser.back();
        browser.back();
        browser.cetakRiwayat();
    }
}

class NodeHalaman {
    String url;
    String judulWeb;
    NodeHalaman prev, next;

    public NodeHalaman(String url, String judulWeb) {
        this.url = url;
        this.judulWeb = judulWeb;
        this.prev = null;
        this.next = null;
    }
}

class BrowserHistory {
    NodeHalaman head, tail, current;

    BrowserHistory() {
        this.head = this.tail = this.current = null;
    }

    public void kunjungi(String url, String judulWeb) {
        NodeHalaman baru = new NodeHalaman(url, judulWeb);

        if (head == null) {
            head = tail = current = baru;
            return;
        }

        current.next = null;
        tail = current;

        baru.prev = current;
        current.next = baru;
        tail = baru;
        current = baru;
    }

    public void back() {
        if (current == null) {
            System.out.println("Riwayat kosong.");
        } else if (current.prev == null) {
            System.out.println("Tidak ada halaman sebelumnya.");
        } else {
            current = current.prev;
            System.out.println("Back ke: " + current.judulWeb);
        }
    }

    public void forward() {
        if (current == null) {
            System.out.println("Riwayat kosong.");
        } else if (current.next == null) {
            System.out.println("Tidak ada halaman berikutnya.");
        } else {
            current = current.next;
            System.out.println("Forward ke: " + current.judulWeb);
        }
    }

    public void cetakRiwayat() {
        if (head == null) {
            System.out.println("Riwayat kosong.");
            return;
        }

        NodeHalaman curr = head;
        int no = 1;
        while (curr != null) {
            String penanda = (curr == current) ? " [AKTIF]" : "";
            System.out.println(no + ". " + curr.judulWeb + " (" + curr.url + ")" + penanda);
            curr = curr.next;
            no++;
        }
    }
}