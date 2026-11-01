class Node {
    int data;
    int height;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.height = 1;
        this.left = this.right = null;
    }
}

public class AVLTri {
    Node root;

    AVLTri() {
        this.root = null;
    }

    int getHeight(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    int getBalance(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node getMinValue(Node node) {
        if (node == null) return null;
        Node temp = node;

        while (temp.left != null) {
            temp = temp.left;
        }

        return temp;
    }

    Node rightRotate(Node rootLama) {
        Node rootBaru = rootLama.left;
        Node subTree = rootBaru.right;

        rootBaru.right = rootLama;
        rootLama.left = subTree;

        rootLama.height = Math.max(getHeight(rootLama.left), getHeight(rootLama.right)) + 1;
        rootBaru.height = Math.max(getHeight(rootBaru.left), getHeight(rootBaru.right)) + 1;
        
        return rootBaru;
    }

    Node leftRotate(Node rootLama) {
        Node rootBaru = rootLama.right;
        Node subtree = rootBaru.left;

        rootBaru.left = rootLama;
        rootLama.right = subtree;

        rootLama.height = Math.max(getHeight(rootLama.left), getHeight(rootLama.right)) + 1;
        rootBaru.height = Math.max(getHeight(rootBaru.left), getHeight(rootBaru.right)) + 1;
        
        return rootBaru;
    }

    Node rebalance(Node current, int data) {
        current.height = Math.max(getHeight(current.left), getHeight(current.right)) + 1;
        int balance = getBalance(current);

        if (balance > 1 && data < current.left.data) {
            return rightRotate(current);
        } 
        if (balance < -1 && data > current.right.data) {
            return leftRotate(current);
        } 
        
        if (balance > 1 && data > current.left.data) {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }

        if (balance < -1 && data < current.right.data) {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        return current;
    }

    void insert(int data) {
        root = insertRecursive(root, data);
    }

    private Node insertRecursive(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (data < root.data) {
            root.left = insertRecursive(root.left, data);
        } else if (data > root.data) {
            root.right = insertRecursive(root.right, data);
        } else {
            return root;
        }

        return rebalance(root, data);
    }
    
    void delete(int data) {
        root = deleteRecursive(root, data);
    }

    private Node deleteRecursive(Node root, int data) {
        if (root == null) {
            return root;
        }

        if (data < root.data) {
            root.left = deleteRecursive(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRecursive(root.right, data);
        } else {
            if (root.left == null || root.right == null) {
                Node childPenerus = (root.left != null) ? root.left : root.right;

                if (childPenerus == null) {
                    root = null;
                } else {
                    root = childPenerus;
                }
            } else {
                Node successor = getMinValue(root.right);
                root.data = successor.data;
                root.right = deleteRecursive(root.right, successor.data);
            }
        }
        
        return rebalance(root, data);
    }

    void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    void preorder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    void postorder(Node node) {
        if (node == null) {
            return;
        }

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    
    public static void main(String[] args) {
        AVLTri tree = new AVLTri();

        tree.insert(30);
        tree.insert(20);
        tree.insert(10);
        tree.insert(40);
        tree.insert(50);
        tree.insert(60);
        tree.insert(70);

        tree.inorder(tree.root);
    }
}