package binarySearchTree;

public class Node {
    public int key;

    public Node parent;

    public Node left;

    public Node right;

    public int height = 1;

    public Node(int key, Node parent) {
        this.key = key;
        this.parent = parent;
    }

    public Node(int key, Node parent, int height) {
        this.key = key;
        this.parent = parent;
        this.height = height;
    }
}
