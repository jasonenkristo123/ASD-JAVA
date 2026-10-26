import java.util.Random;

public class TreeLinked {
    private Node root;

    TreeLinked() {
        root = null;
    }

    public void sisipDtNode(int dtSisip) {
        if (root == null) {
            root = new Node(dtSisip);
        } else {
            root.sisipDt(dtSisip);
        }
    }

    public void preorderTraversal() {
        preorder(root);
    }

    private void preorder(Node node) {
        if (node == null) {
            return;
        }

        System.out.printf("%d ", node.data);
        preorder(node.nodeKiri);
        preorder(node.nodeKanan);
    }

    public void inorderTraversal() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.nodeKiri);
        System.out.printf("%d ", node.data);
        inorder(node.nodeKanan);
    }

    public void postorderTraversal() {
        postorder(root);
    }

    private void postorder(Node node) {
        if (node == null) {
            return;
        }

        postorder(node.nodeKiri);
        postorder(node.nodeKanan);
        System.out.printf("%d ", node.data);
    }

    public int hitungNode() {
        return hitungNode(root);
    }

    private int hitungNode(Node node) {
        if (node == null) {
            return 0;
        }
        
        if (node.nodeKiri == null && node.nodeKanan == null) return 1;
        return 1 + hitungNode(node.nodeKiri) + hitungNode(node.nodeKanan);
    }

    public int hitungDaun() {
        return hitungDaun(root);
    }

    private int hitungDaun(Node node) {
        if (node == null) return 0;
        if (node.nodeKiri == null && node.nodeKanan == null) return 1;
        return hitungDaun(node.nodeKiri) + hitungDaun(node.nodeKanan);
    }

    public int hitungTinggi() {
        return hitungTinggi(root);
    }

    private int hitungTinggi(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(hitungTinggi(node.nodeKiri), hitungTinggi(node.nodeKanan));
    }

    public int panjang(TreeLinked tree) {
        if (tree == null || tree.root == null) return 0;
        return hitungTinggi(tree.root);
    }

    public static void main(String args[]) {
        TreeLinked tree = new TreeLinked();
        Random randomNumber = new Random();

        System.out.println("sisip nilai data berikut: ");
        for (int i = 1; i <= 10; i++) {
            int nilai = randomNumber.nextInt(100);
            System.out.print(nilai + " ");
            tree.sisipDtNode(nilai);
        }

        System.out.println("\n\nPreorder traversal");
        tree.preorderTraversal();
        System.out.println("\n\nInorder traversal");
        tree.inorderTraversal();
        System.out.println("\n\nPostorder traversal");
        tree.postorderTraversal();

        System.out.println("\n\n--- Hasil Soal Latihan ---");
        System.out.println("1. Jumlah seluruh node : " + tree.hitungNode());
        System.out.println("2. Jumlah daun (leaf)  : " + tree.hitungDaun());
        System.out.println("3. Tinggi pohon        : " + tree.hitungTinggi());
        System.out.println("4. Jumlah level pohon  : " + tree.panjang(tree));
    }
}

class Node {
    int data;
    Node nodeKiri;
    Node nodeKanan;

    public Node(int dt) {
        data = dt;
        nodeKiri = nodeKanan = null;
    }

    public void sisipDt(int dtSisip) {
        if (dtSisip < data) {
            if (nodeKiri == null) {
                nodeKiri = new Node(dtSisip);
            } else {
                nodeKiri.sisipDt(dtSisip);
            }
        } else if (dtSisip > data) {
            if (nodeKanan == null) {
                nodeKanan = new Node(dtSisip);
            } else {
                nodeKanan.sisipDt(dtSisip);
            }
        }
    }
}




