class Stack {
    int[] tumpukan = new int[graf.MAX];
    int top;
    int popped;

    public Stack() {
        top = -1;
        popped = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == tumpukan.length - 1;
    }

    public void push(int data) {
        if (!isFull()) {
            tumpukan[++top] = data;
        } else {
            System.out.println("Stack penuh!");
        }
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack kosong");
        }

        popped = tumpukan[top];

        tumpukan[top--] = -1;

        return popped;
    }
}

class Queue {
    int []queue = new int[graf.MAX];
    int front;
    int back;
    int removedData;

    Queue() {
        this.front = 0;
        this.back = -1;
        this.removedData = -1;
    }

    public boolean isEmpty() {
        return back == -1;
    }

    public boolean isFull() {
        return back == queue.length - 1;
    }

    public void enqueue(int indeksSimpul) {
        if (!isFull()) {
            queue[++back] = indeksSimpul; 
        } else {
            System.out.println("Queue full");
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("kosong mas");
            return -1;
        }

        removedData = queue[front];
        queue[front] = -1;

        for (int i = 0; i < queue.length - 1; i++) {
            queue[i] = queue[i + 1];
        }

        back--;
        if (back < 0) {
            back = -1;
            front = 0;
        }

        return removedData;
    } 
}


public class graf {
    static int MAX = 5;
    
    private class Vertex {
        char namaSimpul;
        boolean sudahDikunjungi;

        public Vertex(char Nama) {
            this.namaSimpul = Nama;
            this.sudahDikunjungi = false;
        }
    }

    Vertex[] daftarSimpul = new Vertex[MAX];
    int[][] AdjMatrix = new int[MAX][MAX];
    int jumlahSimpulSaatIni;
    Stack stackDFS;
    Queue queueBFS;

    public graf() {
        jumlahSimpulSaatIni = 0;
        for (int i = 0; i < MAX; i++) {
            daftarSimpul[i] = null;
            for (int j = 0; j < MAX; j++) {
                AdjMatrix[i][j] = 0;
            }
        }

        stackDFS = new Stack();
        queueBFS = new Queue();
    }

    public boolean haveVertex(char hurufCari) {
        for (int i = 0; i < jumlahSimpulSaatIni; i++) {
            if (daftarSimpul[i].namaSimpul == hurufCari) {
                return true;
            }
        }

        return false;
    }

    public int getVertex(char hurufCari) {
        for (int i = 0; i < jumlahSimpulSaatIni; i++) {
            if (daftarSimpul[i].namaSimpul == hurufCari) return i;
        }

        return -1;
    }   

    void addVertex(char hurufBaru) {
        if (jumlahSimpulSaatIni == MAX) {
            System.out.println("udah full");
            return;
        }

        daftarSimpul[jumlahSimpulSaatIni++] = new Vertex(hurufBaru);
    }

    void addAdj(char hurufAsal, char hurufTujuan, int bobot) {
        if (jumlahSimpulSaatIni <= MAX) {
            if (!haveVertex(hurufAsal)) {
                addVertex(hurufAsal);
            }

            if (!haveVertex(hurufTujuan)) {
                addVertex(hurufTujuan);
            }
        } else {
            System.out.println("Kepenuhan mas");
        }

        int indeksAsal = getVertex(hurufAsal);
        int indeksTujuan = getVertex(hurufTujuan);

        AdjMatrix[indeksAsal][indeksTujuan] = bobot;
        AdjMatrix[indeksTujuan][indeksAsal] = bobot;
    }

    int getUnvisited(int indeksSimpul) {
        for (int i = 0; i < jumlahSimpulSaatIni; i++) {
            if (AdjMatrix[indeksSimpul][i] > 0 && daftarSimpul[i].sudahDikunjungi == false) {
                return i;
            }
        }

        return -1;
    }

    public void bfs() {
        daftarSimpul[0].sudahDikunjungi = true;
        System.out.println(daftarSimpul[0].namaSimpul + " -> ");

        queueBFS.enqueue(0);
        int indeksTetangga;

        while (!queueBFS.isEmpty()) {
            int simpulSaatIni = queueBFS.dequeue();

            indeksTetangga = getUnvisited(simpulSaatIni);
            
            while (indeksTetangga != -1) {
                daftarSimpul[indeksTetangga].sudahDikunjungi = true;
                System.out.println(daftarSimpul[indeksTetangga].namaSimpul + " -> ");
                queueBFS.enqueue(indeksTetangga);

                indeksTetangga = getUnvisited(simpulSaatIni);
            }
        }
        System.out.println();

        for (int i = 0; i < jumlahSimpulSaatIni; i++) {
            daftarSimpul[i].sudahDikunjungi = false;
        }
    }

    public void dfs() {
        daftarSimpul[0].sudahDikunjungi = true;
        System.out.println(daftarSimpul[0].namaSimpul + " -> ");
        stackDFS.push(0);

        int indeksTetangga;
        
        while (!stackDFS.isEmpty()) {
            int simpulSaatIni = stackDFS.pop();

            indeksTetangga = getUnvisited(simpulSaatIni);
            
            while (indeksTetangga != -1) {
                daftarSimpul[indeksTetangga].sudahDikunjungi = true;
                System.out.println(daftarSimpul[indeksTetangga].namaSimpul + " -> ");
                stackDFS.push(simpulSaatIni);

                indeksTetangga = getUnvisited(simpulSaatIni);
            }
        }

        System.out.println();

        for (int i = 0; i < jumlahSimpulSaatIni; i++) {
            daftarSimpul[i].sudahDikunjungi = false;
        }
    }

    void jalurTerbaik(char hurufAsal, char hurufTujuan) {
        int indeksAsal = getVertex(hurufAsal);
        int indeksTujuan = getVertex(hurufTujuan);

        if (indeksAsal == -1 || indeksTujuan == -1) {
            System.out.println("not found");
            return;
        }

        int[] jarakTerpendek = new int[jumlahSimpulSaatIni];
        int[] simpulSebelumnya = new int[jumlahSimpulSaatIni];
        boolean[] sudahDikunci = new boolean[jumlahSimpulSaatIni];

        for (int i = 0; i < jumlahSimpulSaatIni; i++) {
            jarakTerpendek[i] = Integer.MAX_VALUE;
            sudahDikunci[i] = false;
            simpulSebelumnya[i] = -1;
        }

        jarakTerpendek[indeksAsal] = 0;

        for (int tahap = 0; tahap < jumlahSimpulSaatIni; tahap++) {
            int simpulTerdekat = -1;
            int nilaiJarakMinimum = Integer.MAX_VALUE;

            for (int v = 0; v < jumlahSimpulSaatIni; v++) {
                if (!sudahDikunci[v] && jarakTerpendek[v] <= nilaiJarakMinimum) {
                    nilaiJarakMinimum = jarakTerpendek[v];
                    simpulTerdekat = v;
                }
            }

            if (simpulTerdekat == -1 || simpulTerdekat == indeksTujuan) {
                break;
            }

            sudahDikunci[simpulTerdekat] = true;

            for (int tetangga = 0; tetangga < jumlahSimpulSaatIni; tetangga++) {
                if (AdjMatrix[simpulTerdekat][tetangga] > 0 && !sudahDikunci[tetangga]) {
                    int jarakBaru = jarakTerpendek[simpulTerdekat] + AdjMatrix[simpulTerdekat][tetangga];

                    if (jarakBaru < jarakTerpendek[tetangga]) {
                        jarakTerpendek[tetangga] = jarakBaru;
                        simpulSebelumnya[tetangga] = simpulTerdekat;
                    }
                }
            }
        }

        if (jarakTerpendek[indeksTujuan] == Integer.MAX_VALUE) {
            System.out.println("\nTidak ada jalur dari " + hurufAsal + " ke " + hurufTujuan);
        } else {
            System.out.println("\nJarak terbaik dari " + hurufAsal + " ke " + hurufTujuan + " adalah: " + jarakTerpendek[indeksTujuan]);

            int[] urutanJalurTerbaik = new int[MAX];
            int jumlahLangkah = 0;
            int posisiLacak = indeksTujuan;

            while (posisiLacak != -1) {
                urutanJalurTerbaik[jumlahLangkah] = posisiLacak;
                jumlahLangkah++;
                posisiLacak = simpulSebelumnya[posisiLacak];
            }

            System.out.print("Jalur: ");
            for (int i = jumlahLangkah - 1; i >= 0; i--) {
                System.out.print(daftarSimpul[urutanJalurTerbaik[i]].namaSimpul);
                if (i > 0) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
        
        for (int i = 0; i < jumlahSimpulSaatIni; i++) {
            daftarSimpul[i].sudahDikunjungi = false;
        }
    }

    void print() {
        System.out.print("\t");
        for (int i = 0; i < jumlahSimpulSaatIni; i++) {
            System.out.print(daftarSimpul[i].namaSimpul + "\t");
        }

        System.out.println();

        for (int i = 0; i < jumlahSimpulSaatIni; i++) {
            System.out.print(daftarSimpul[i].namaSimpul + "\t");
            for (int j = 0; j < jumlahSimpulSaatIni; j++) {
                System.out.print(AdjMatrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        graf g = new graf();

        g.addVertex('A'); // Masuk ke daftarSimpul[0]
        g.addVertex('B'); // Masuk ke daftarSimpul[1]
        g.addVertex('D'); // Masuk ke daftarSimpul[2]
        g.addVertex('E'); // Masuk ke daftarSimpul[3]
        g.addVertex('C'); // Masuk ke daftarSimpul[4]

        g.addAdj('A', 'B', 2);
        g.addAdj('B', 'C', 4);
        g.addAdj('B', 'D', 1);
        g.addAdj('C', 'E', 2);
        g.addAdj('C', 'A', 3);

        g.bfs();
        g.dfs();

        g.jalurTerbaik('A', 'E');
        g.jalurTerbaik('D', 'E');

        g.print();
    }
}