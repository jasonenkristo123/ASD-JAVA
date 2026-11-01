class Node {
    int data;
    int tinggi;
    Node pKiri;
    Node pKanan;
    Node pInduk;

    public Node(int dt, int tg, Node pKi, Node pKa, Node pI) {
        this.data = dt;
        this.tinggi = tg;
        this.pKiri = pKi;
        this.pKanan = pKa;
        this.pInduk = pI;
    }
}

public class AVLT {
    private Node root;

    public AVLT() {
        root = null;
    }

    public boolean cariDt(int dt) {
        Node temp = root;
        while (temp != null) {
            if (dt == temp.data) return true;
            else if (dt < temp.data) temp = temp.pKiri;
            else temp = temp.pKanan;
        }
        return false;
    }

    public int tinggi() {
        return tinggi(root);
    }

    private int tinggi(Node node) {
        if (node == null) return 0;
        else return node.tinggi;
    }

    public int jumlahNode() {
        return jumlahNode(root);
    }

    private int jumlahNode(Node node) {
        if (node == null) return 0;
        else return 1 + jumlahNode(node.pKiri) + jumlahNode(node.pKanan);
    }

    public void inOrderTraversal() {
        inOrder(root);
    }

    private void inOrder(Node r) {
        if (r == null) return;
        inOrder(r.pKiri);
        System.out.printf("-%d", r.data);
        inOrder(r.pKanan);
    }

    public Node putarKiri(Node temp) {
        Node pKanan = temp.pKanan;
        Node parent = temp.pInduk;

        temp.pKanan = pKanan.pKiri;
        if (temp.pKanan != null) {
            temp.pKanan.pInduk = temp;
        }

        pKanan.pKiri = temp;
        temp.pInduk = pKanan;

        pKanan.pInduk = parent;
        if (parent == null) {
            root = pKanan;
        } else if (parent.pKiri == temp) {
            parent.pKiri = pKanan;
        } else {
            parent.pKanan = pKanan;
        }

        temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
        pKanan.tinggi = Math.max(tinggi(pKanan.pKiri), tinggi(pKanan.pKanan)) + 1;

        return pKanan;
    }

    public Node putarKanan(Node temp) {
        Node pKiri = temp.pKiri;
        Node parent = temp.pInduk;

        temp.pKiri = pKiri.pKanan;
        if (temp.pKiri != null) {
            temp.pKiri.pInduk = temp;
        }

        pKiri.pKanan = temp;
        temp.pInduk = pKiri;

        pKiri.pInduk = parent;
        if (parent == null) {
            root = pKiri;
        } else if (parent.pKiri == temp) {
            parent.pKiri = pKiri;
        } else {
            parent.pKanan = pKiri;
        }

        temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
        pKiri.tinggi = Math.max(tinggi(pKiri.pKiri), tinggi(pKiri.pKanan)) + 1;

        return pKiri;
    }

    public Node putarKiriKanan(Node temp) {
        temp.pKiri = putarKiri(temp.pKiri);
        return putarKanan(temp);
    }

    public Node putarKananKiri(Node temp) {
        temp.pKanan = putarKanan(temp.pKanan);
        return putarKiri(temp);
    }

    private void rebalance(Node temp) {
        while (temp != null) {
            int balance = tinggi(temp.pKiri) - tinggi(temp.pKanan);

            if (balance >= 2) {
                if (tinggi(temp.pKiri.pKiri) >= tinggi(temp.pKiri.pKanan)) {
                    temp = putarKanan(temp);
                } else {
                    temp = putarKiriKanan(temp);
                }
            } else if (balance <= -2) {
                if (tinggi(temp.pKanan.pKanan) >= tinggi(temp.pKanan.pKiri)) {
                    temp = putarKiri(temp);
                } else {
                    temp = putarKananKiri(temp);
                }
            } else {
                temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
            }

            temp = temp.pInduk;
        }
    }

    public boolean sisipDt(int dt) {
        if (root == null) {
            root = new Node(dt, 1, null, null, null);
            return true;
        }

        Node temp = root;
        Node prev = null;
        while (temp != null) {
            if (dt == temp.data) return false;
            else if (dt < temp.data) {
                prev = temp;
                temp = temp.pKiri;
            } else {
                prev = temp;
                temp = temp.pKanan;
            }
        }

        temp = new Node(dt, 1, null, null, prev);
        if (dt < prev.data) prev.pKiri = temp;
        else prev.pKanan = temp;

        rebalance(temp);
        return true;
    }

    public boolean hapusDt(int dt) {
        Node temp = root;

        while (temp != null && temp.data != dt) {
            if (dt < temp.data) temp = temp.pKiri;
            else temp = temp.pKanan;
        }

        if (temp == null) return false;

        if (temp.pKiri != null && temp.pKanan != null) {
            Node successor = temp.pKanan;
            while (successor.pKiri != null) {
                successor = successor.pKiri;
            }
            temp.data = successor.data;
            temp = successor;
        }

        Node anak = (temp.pKiri != null) ? temp.pKiri : temp.pKanan;
        Node parent = temp.pInduk;

        if (anak != null) {
            anak.pInduk = parent;
        }

        if (parent == null) {
            root = anak;
        } else if (parent.pKiri == temp) {
            parent.pKiri = anak;
        } else {
            parent.pKanan = anak;
        }

        rebalance(parent);

        return true;
    }

    public static void main(String[] args) {
        AVLT t = new AVLT();

        int[] data = {3, 4, 6, 5, 15, 10, 20, 17, 25};
        for (int val : data) {
            t.sisipDt(val);
            System.out.print("Sisip " + val + " \t: ");
            t.inOrderTraversal();
            System.out.println();
        }

        System.out.println();

        System.out.print("Hapus 5   : ");
        t.hapusDt(5);
        t.inOrderTraversal();
        System.out.println();

        System.out.print("Hapus 6   : ");
        t.hapusDt(6);
        t.inOrderTraversal();
        System.out.println();

        System.out.print("Hapus 15  : ");
        t.hapusDt(15);
        t.inOrderTraversal();
        System.out.println();
    }
}