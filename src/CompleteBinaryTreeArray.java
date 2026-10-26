public class CompleteBinaryTreeArray {
    private int[] tree;
    private int kapasitas;
    private int size;

    public CompleteBinaryTreeArray(int kapasitas) {
        this.kapasitas = kapasitas;
        this.tree = new int[kapasitas];
        this.size = 0;
    }

    public void sisipData(int data) {
        if (size >= kapasitas) {
            System.out.println("Pohon sudah penuh!");
            return;
        }
        tree[size] = data;
        size++;
    }

    public void preorderTraversal() {
        preorder(0);
        System.out.println();
    }

    private void preorder(int index) {
        if (index >= size) return;
        System.out.print(tree[index] + " ");
        preorder(2 * index + 1);
        preorder(2 * index + 2);
    }

    public void inorderTraversal() {
        inorder(0);
        System.out.println();
    }

    private void inorder(int index) {
        if (index >= size) return;
        inorder(2 * index + 1); 
        System.out.print(tree[index] + " ");
        inorder(2 * index + 2);
    }

    public void postorderTraversal() {
        postorder(0);
        System.out.println();
    }

    private void postorder(int index) {
        if (index >= size) return;
        postorder(2 * index + 1); 
        postorder(2 * index + 2);
        System.out.print(tree[index] + " ");
    }

    public void cetakArray() {
        System.out.print("Elemen Array: ");
        for (int i = 0; i < size; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CompleteBinaryTreeArray tree = new CompleteBinaryTreeArray(10);

        int[] dataMasukan = {10, 20, 30, 40, 50, 60, 70};
        System.out.println("Menyisipkan data ke dalam Complete Binary Tree...");
        for (int data : dataMasukan) {
            tree.sisipData(data);
        }

        System.out.println();
        tree.cetakArray();

        System.out.print("\nPreorder Traversal  : ");
        tree.preorderTraversal();

        System.out.print("Inorder Traversal   : ");
        tree.inorderTraversal();

        System.out.print("Postorder Traversal : ");
        tree.postorderTraversal();
    }
}