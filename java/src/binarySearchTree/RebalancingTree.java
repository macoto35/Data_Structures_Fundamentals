package binarySearchTree;

public class RebalancingTree {

    public Node getRoot(Node node) {
        while(node.parent != null)
            node = node.parent;

        return node;
    }

    public void rotateRight(Node node) {
        Node p = node.parent;
        Node b = node.left;
        Node e = b.right;

        if (p != null) {
            if (p.left != null && p.left.key == node.key)
                p.left = b;
            else if (p.right != null && p.right.key == node.key)
                p.right = b;
        }
        b.parent = p;

        b.right = node;
        node.parent = b;

        node.left = e;
        if (e != null)
            e.parent = node;
    }

    public void rotateLeft(Node node) {
        Node p = node.parent;
        Node c = node.right;
        Node d = c.left;

        if (p != null) {
            if (p.left != null && p.left.key == node.key)
                p.left = c;
            else if (p.right != null && p.right.key == node.key)
                p.right = c;
        }
        c.parent = p;

        c.left = node;
        node.parent = c;

        node.right = d;
        if (d != null)
            d.parent = node;
    }

}
