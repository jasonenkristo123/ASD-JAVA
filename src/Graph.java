public class Graph {

    private class Node {
        private int data;
        private Node next;

        public Node(int dt, Node n) {
            data = dt;
            next = n;
        }

        public int getDt() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node[] node;
    private int jNode;

    public Graph(int n) {
        jNode = n;
        node = new Node[jNode];
    }

    public void addAdj(int head, int adj) {
        Node n = new Node(adj, node[head]);
        node[head] = n;
    }

    public void cetak(String komentar) {
        System.out.println(komentar);
        for (int i = 0; i < jNode; i++) {
            System.out.print("[" + i + "]");
            Node n = node[i];
            while (n != null) {
                System.out.print("->" + n.getDt());
                n = n.getNext();
            }
            System.out.println();
        }
    }

    public int getEdgeKeluar(int targetNode) {
        if (targetNode < 0 || targetNode >= jNode) return 0;
        int count = 0;
        Node n = node[targetNode];
        while (n != null) {
            count++;
            n = n.getNext();
        }
        return count;
    }

    public int getEdgeMasuk(int targetNode) {
        if (targetNode < 0 || targetNode >= jNode) return 0;
        int count = 0;
        for (int i = 0; i < jNode; i++) {
            Node n = node[i];
            while (n != null) {
                if (n.getDt() == targetNode) {
                    count++;
                }
                n = n.getNext();
            }
        }
        return count;
    }

    public void infoEdge(int targetNode) {
        int masuk = getEdgeMasuk(targetNode);
        int keluar = getEdgeKeluar(targetNode);
        System.out.println("Node [" + targetNode + "] -> Edge Masuk (In-Degree): " + masuk
                + ", Edge Keluar (Out-Degree): " + keluar
                + ", Total Edge: " + (masuk + keluar));
    }

    public int getJumlahTetanggaKeluar(int targetNode) {
        if (targetNode < 0 || targetNode >= jNode) return 0;
        boolean[] sudahDihitung = new boolean[jNode];
        int count = 0;

        Node n = node[targetNode];
        while (n != null) {
            int tetangga = n.getDt();
            if (tetangga != targetNode && !sudahDihitung[tetangga]) {
                sudahDihitung[tetangga] = true;
                count++;
            }
            n = n.getNext();
        }
        return count;
    }

    public int getJumlahTetanggaTotal(int targetNode) {
        if (targetNode < 0 || targetNode >= jNode) return 0;
        boolean[] isTetangga = new boolean[jNode];

        Node n = node[targetNode];
        while (n != null) {
            if (n.getDt() != targetNode) {
                isTetangga[n.getDt()] = true;
            }
            n = n.getNext();
        }

        for (int i = 0; i < jNode; i++) {
            if (i == targetNode) continue;
            Node curr = node[i];
            while (curr != null) {
                if (curr.getDt() == targetNode) {
                    isTetangga[i] = true;
                }
                curr = curr.getNext();
            }
        }

        int count = 0;
        for (int i = 0; i < jNode; i++) {
            if (isTetangga[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addAdj(0, 3);
        g.addAdj(0, 1);
        g.addAdj(1, 4);
        g.addAdj(1, 2);
        g.addAdj(2, 4);
        g.addAdj(2, 1);
        g.addAdj(4, 3);

        g.cetak("Kondisi awal");

        System.out.println("\n=== Hasil Latihan 1: Jumlah Edge Masuk & Keluar ===");
        for (int i = 0; i < 5; i++) {
            g.infoEdge(i);
        }

        System.out.println("\n=== Hasil Latihan 2: Jumlah Tetangga Tiap Node ===");
        for (int i = 0; i < 5; i++) {
            System.out.println("Node [" + i + "] -> Tetangga Arah Keluar: "
                    + g.getJumlahTetanggaKeluar(i)
                    + " | Total Tetangga Terhubung (Unik): "
                    + g.getJumlahTetanggaTotal(i));
        }
    }
}