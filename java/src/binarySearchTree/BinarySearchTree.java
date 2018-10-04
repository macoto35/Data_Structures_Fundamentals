package binarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    public Node root;

    public List<Integer> toString(Node node, List<Integer> arr) {
        if (node == null)
            return arr;

        this.toString(node.left, arr);
        arr.add(node.key);
        this.toString(node.right, arr);

        return arr;
    }

    public Node find(int key, Node node) {
        if (node.key == key)
            return node;

        if (node.key > key) {
            if (node.left == null)
                return node;
            return this.find(key, node.left);
        } else {
            if (node.right == null)
                return node;
            return this.find(key, node.right);
        }
    }

    public Node next(Node node) {
        if (node.right != null)
            return this.leftDescendant(node.key, node.right);
        else
            return this.rightAncestor(node.key, node.parent);
    }

    public Node leftDescendant(int key, Node node) {
        if (node.left != null)
            return leftDescendant(key, node.left);
        return node;
    }

    public Node rightAncestor(int key, Node node) {
        if (key < node.key)
            return node;

        if (node.parent == null)
            return null;
        else
            return this.rightAncestor(key, node.parent);
    }

    public Integer[] rangeSearch(int st, int ed, Node node) {
        List<Integer> result = new ArrayList<Integer>();

        Node tmp = this.find(st, node);
        while(tmp != null && tmp.key <= ed) {
            if (tmp.key >= st)
                result.add(tmp.key);
            tmp = this.next(tmp);
        }

        return result.toArray(new Integer[result.size()]);
    }

    public void insert(int key, Node node) {
        Node parent = this.find(key, node);
        Node newNode = new Node(key, parent);

        if (parent.key > key)
            parent.left = newNode;
        else
            parent.right = newNode;
    }

    public Node remove(Node node) {
        if (node.right == null) {
            Node left = node.left;

            if (left != null)
                left.parent = null;

            return left;
        } else {
            Node next = this.next(node);
            node.key = next.key;

            if (next.parent.left != null && next.parent.left.key == next.key)
                next.parent.left = next.right;
            if (next.parent.right != null && next.parent.right.key == next.key)
                next.parent.right = next.right;

            if (next.right != null) {
                next.right.parent = next.parent;
            } else {
                next.parent = null;
            }

            return node;
        }
    }
}
