package binarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    public Node root;

    public List<Integer> toString(Node node, List<Integer> result) {
        if (node == null)
            return result;

        this.toString(node.left, result);
        result.add(node.key);
        this.toString(node.right, result);

        return result;
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
            return this.leftDescendant(node.right);
        else
            return this.rightAncestor(node.key, node.parent);
    }

    private Node leftDescendant(Node node) {
        if (node.left != null)
            return this.leftDescendant(node.left);
        return node;
    }

    private Node rightAncestor(int key, Node node) {
        if (node == null)
            return null;

        if (key < node.key)
            return node;
        else
            return this.rightAncestor(key, node.parent);
    }

    public Integer[] rangeSearch(int st, int ed, Node root) {
        List<Integer> result = new ArrayList<Integer>();

        Node node = this.find(st, root);
        while(node != null && node.key <= ed) {
            if (node.key >= st)
                result.add(node.key);
            node = this.next(node);
        }

        return result.toArray(new Integer[result.size()]);
    }

    public Node insert(int key, Node root) {
        if (root == null)
            return new Node(key, null);

        Node node = this.find(key, root);
        Node newNode = new Node(key, node);

        if (key < node.key)
            node.left = newNode;
        else
            node.right = newNode;

        return root;
    }

    public void remove(Node node) {
        if (node.right == null) {
            // if single node tree
            if (node.left == null) {
                this.root = null;
                return;
            }

            // level up node.left and remove node
            node.left.parent = node.parent;

            if (node.parent != null) {
                if (node.parent.left.key == node.key) {
                    node.parent.left = node.left;
                } else {
                    node.parent.right = node.left;
                }
            } else {
                this.root = node.left;
            }
        } else {
            // find left most node
            Node next = this.next(node);

            // assign key: node <- next
            node.key = next.key;

            // level up next.right and remove next
            if (next.right != null)
                next.right.parent = next.parent;

            if (next.parent.left != null && next.parent.left.key == next.key)
                next.parent.left = next.right;

            if (next.parent.right != null && next.parent.right.key == next.key)
                next.parent.right = next.right;
        }
    }
}
