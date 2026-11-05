import java.util.Scanner;

public class AVLTree {

    // Struktur Node direpresentasikan sebagai static inner class
    static class Node {
        int key;
        Node left;
        Node right;
        int height;

        Node(int value) {
            this.key = value;
            this.left = null;
            this.right = null;
            this.height = 1; // Node baru selalu dimulai dari leaf (height = 1)
        }
    }

    // Fungsi pembantu untuk menentukan nilai maksimum dari dua integer
    static int getMax(int a, int b) {
        return (a > b) ? a : b;
    }

    // Mengambil nilai height dari node secara aman (menangani null pointer)
    static int getHeight(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    // Menghitung Balance Factor dari suatu node (Height Left Subtree - Height Right Subtree)
    static int getBalanceFactor(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // Memperbarui height node berdasarkan height dari child-nya
    static void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + getMax(getHeight(node.left), getHeight(node.right));
        }
    }

    // -------------------------------------------------------------
    // OPERASI ROTASI (ROTATION)
    // -------------------------------------------------------------

    // Right Rotate pada subtree dengan root y
    static Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Proses rotasi
        x.right = y;
        y.left = T2;

        // Update height setelah rotasi
        updateHeight(y);
        updateHeight(x);

        // Kembalikan root baru
        return x;
    }

    // Left Rotate pada subtree dengan root x
    static Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Proses rotasi
        y.left = x;
        x.right = T2;

        // Update height setelah rotasi
        updateHeight(x);
        updateHeight(y);

        // Kembalikan root baru
        return y;
    }

    // -------------------------------------------------------------
    // INSERTION & REBALANCING
    // -------------------------------------------------------------

    static Node insert(Node node, int key) {
        // 1. Lakukan BST insertion standar
        if (node == null) return new Node(key);

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            System.out.println("Nilai " + key + " sudah ada dalam tree! Dibatalkan.");
            return node;
        }

        // 2. Update height dari ancestor node ini
        updateHeight(node);

        // 3. Hitung Balance Factor untuk mengecek apakah node ini unbalanced
        int balance = getBalanceFactor(node);

        // 4. Jika node tidak seimbang (unbalanced), tangani 4 kasus rotasi:

        // Kasus 1: Left Left (LL Case)
        if (balance > 1 && key < node.left.key) {
            System.out.println("--> Melakukan Right Rotation pada node " + node.key);
            return rotateRight(node);
        }

        // Kasus 2: Right Right (RR Case)
        if (balance < -1 && key > node.right.key) {
            System.out.println("--> Melakukan Left Rotation pada node " + node.key);
            return rotateLeft(node);
        }

        // Kasus 3: Left Right (LR Case)
        if (balance > 1 && key > node.left.key) {
            System.out.println("--> Melakukan Left-Right Rotation pada node " + node.key);
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Kasus 4: Right Left (RL Case)
        if (balance < -1 && key < node.right.key) {
            System.out.println("--> Melakukan Right-Left Rotation pada node " + node.key);
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // -------------------------------------------------------------
    // FUNGSI VISUALISASI TREE
    // Mencetak struktur secara menyamping: Root di kiri, Right Child di atas, Left Child di bawah
    // -------------------------------------------------------------
    static void printTree(Node root, int space, int COUNT) {
        if (root == null) return;

        space += COUNT;

        // Cetak right child terlebih dahulu (posisi atas)
        printTree(root.right, space, COUNT);

        // Cetak node saat ini
        System.out.println();
        for (int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        System.out.println("[" + root.key + "] (h=" + root.height + ")");

        // Cetak left child (posisi bawah)
        printTree(root.left, space, COUNT);
    }

    // Helper wrapper untuk memanggil printTree
    static void display(Node root) {
        printTree(root, 0, 6);
    }

    // -------------------------------------------------------------
    // MENU UTAMA INTERAKTIF
    // -------------------------------------------------------------
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node root = null;
        int choice;

        System.out.println("=== Program Interaktif AVL Tree (Java) ===");

        do {
            System.out.println("\n------------------------------");
            System.out.println("1. Insert Nilai");
            System.out.println("2. Tampilkan Struktur Tree");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nilai integer: ");
                    int val = scanner.nextInt();
                    root = insert(root, val);
                    System.out.println("\nStruktur AVL Tree setelah insertion:");
                    display(root);
                    break;

                case 2:
                    if (root == null) {
                        System.out.println("AVL Tree masih kosong.");
                    } else {
                        System.out.println("\nStruktur AVL Tree Saat Ini:");
                        display(root);
                    }
                    break;

                case 3:
                    System.out.println("Keluar dari program.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        } while (choice != 3);

        scanner.close();
    }
}