class Node {
    Object data;
    Node right;
    Node left;

    Node(Object data) {
        this.data = data;
        this.right = this.left = null;
    }
}

class BinaryTree {
    Node root;
    

    BinaryTree() {
        this.root = null;
    }
}