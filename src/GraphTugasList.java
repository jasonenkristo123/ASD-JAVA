public class GraphTugasList {
    private class Node {
        private int data;
        private int jarak;
        private Node next;

        public Node(int dt, int jrk, Node n) {
            data = dt;
            jarak = jrk;
            next = n;
        }

        public int getDt() {
            return data;
        }

        public int getJarak() {
            return jarak;
        }

        public Node getNext() {
            return next;
        }
    }

    private class Stack {
        private class StackNode {
            int data;
            StackNode next;
            StackNode(int d, StackNode n) {
                data = d;
                next = n;
            }
        }

        private StackNode top;

        public boolean isEmpty() {
            return top == null;
        }

        public void push(int dt) {
            top = new StackNode(dt, top);
        }

        public int pop() {
            if (isEmpty()) return -1;
            int val = top.data;
            top = top.next;
            return val;
        }
    }

    private class Queue {
        private class QueueNode {
            int data;
            QueueNode next;
            QueueNode(int d) {
                data = d;
            }
        }

        private QueueNode head, tail;

        public boolean isEmpty() {
            return head == null;
        }

        public void enqueue(int dt) {
            QueueNode baru = new QueueNode(dt);
            if (isEmpty()) {
                head = tail = baru;
            } else {
                tail.next = baru;
                tail = baru;
            }
        }

        public int dequeue() {
            if (isEmpty()) return -1;
            int val = head.data;
            head = head.next;
            if (head == null) tail = null;
            return val;
        }
    }

    private Node[] node;
    private int jNode;

    public GraphTugasList(int n) {
        jNode = n;
        node = new Node[jNode];
    }

    public void addAdj(int head, int adj, int jarak) {
        Node n = new Node(adj, jarak, node[head]);
        node[head] = n;
    }

    public void cetak(String komentar) {
        System.out.println(komentar);
        for (int i = 0; i < jNode; i++) {
            System.out.print("[" + i + "]");
            Node n = node[i];
            while (n != null) {
                System.out.print(" -> " + n.getDt() + "(jarak:" + n.getJarak() + ")");
                n = n.getNext();
            }
            System.out.println();
        }
    }

    public void bfs(int startNode) {
        boolean[] visited = new boolean[jNode];
        Queue q = new Queue();

        visited[startNode] = true;
        q.enqueue(startNode);

        System.out.print("Penelusuran BFS (mulai node " + startNode + "): ");
        while (!q.isEmpty()) {
            int curr = q.dequeue();
            System.out.print(curr + " ");

            Node n = node[curr];
            while (n != null) {
                int tetangga = n.getDt();
                if (!visited[tetangga]) {
                    visited[tetangga] = true;
                    q.enqueue(tetangga);
                }
                n = n.getNext();
            }
        }
        System.out.println();
    }

    public void dfs(int startNode) {
        boolean[] visited = new boolean[jNode];
        Stack s = new Stack();

        s.push(startNode);

        System.out.print("Penelusuran DFS (mulai node " + startNode + "): ");
        while (!s.isEmpty()) {
            int curr = s.pop();

            if (!visited[curr]) {
                visited[curr] = true;
                System.out.print(curr + " ");

                Node n = node[curr];
                while (n != null) {
                    int tetangga = n.getDt();
                    if (!visited[tetangga]) {
                        s.push(tetangga);
                    }
                    n = n.getNext();
                }
            }
        }
        System.out.println();
    }

    public void jarakTerpendek(int asal, int tujuan) {
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[jNode];
        int[] prev = new int[jNode];
        boolean[] visited = new boolean[jNode];

        for (int i = 0; i < jNode; i++) {
            dist[i] = INF;
            prev[i] = -1;
        }
        dist[asal] = 0;

        for (int i = 0; i < jNode; i++) {
            int u = -1;
            int minDist = INF;
            for (int v = 0; v < jNode; v++) {
                if (!visited[v] && dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }

            if (u == -1 || u == tujuan) break;
            visited[u] = true;

            Node n = node[u];
            while (n != null) {
                int v = n.getDt();
                int bobot = n.getJarak();
                if (!visited[v] && dist[u] != INF && dist[u] + bobot < dist[v]) {
                    dist[v] = dist[u] + bobot;
                    prev[v] = u;
                }
                n = n.getNext();
            }
        }

        if (dist[tujuan] == INF) {
            System.out.println("Tidak ada jalur dari node " + asal + " ke node " + tujuan);
        } else {
            System.out.print("Jarak terpendek dari [" + asal + "] ke [" + tujuan + "] = " + dist[tujuan] + " | Rute: ");
            cetakRute(prev, tujuan);
            System.out.println();
        }
    }

    private void cetakRute(int[] prev, int current) {
        if (prev[current] != -1) {
            cetakRute(prev, prev[current]);
            System.out.print(" -> ");
        }
        System.out.print(current);
    }

    public static void main(String[] args) {
        GraphTugasList g = new GraphTugasList(5);

        g.addAdj(0, 3, 10);
        g.addAdj(0, 1, 2);
        g.addAdj(1, 4, 5);
        g.addAdj(1, 2, 3);
        g.addAdj(2, 4, 1);
        g.addAdj(2, 1, 3);
        g.addAdj(4, 3, 2);

        g.cetak("=== Representasi Adjacency List Berbobot (Tugas 3) ===");

        System.out.println("\n=== Penelusuran Graf BFS & DFS (Tugas 2) ===");
        g.bfs(0);
        g.dfs(0);

        System.out.println("\n=== Perhitungan Jarak Terpendek (Tugas 4) ===");
        g.jarakTerpendek(0, 3);
        g.jarakTerpendek(0, 4);
    }
}