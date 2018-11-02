package binarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {
    public void rotateRight(Node node) {
        Node p = node.parent;
        Node l = node.left;
        Node r = l.right;

        if (p != null) {
            if (p.left != null && p.left.key == node.key)
                p.left = l;
            else if (p.right != null && p.right.key == node.key)
                p.right = l;
        }
        l.parent = p;

        l.right = node;
        node.parent = l;

        node.left = r;
        if (r != null)
            r.parent = node;
    }

    public void rotateLeft(Node node) {
        Node p = node.parent;
        Node r = node.right;
        Node l = r.left;

        if (p != null) {
            if (p.left != null && p.left.key == node.key)
                p.left = r;
            else if (p.right != null && p.right.key == node.key)
                p.right = r;
        }
        r.parent = p;

        r.left = node;
        node.parent = r;

        node.right = l;
        if (l != null)
            l.parent = node;
    }

    public Node find(int key, Node node) {
        if (key == node.key)
            return node;

        if (key > node.key) {
            if (node.right == null)
                return node;
            return this.find(key, node.right);
        } else {
            if (node.left == null)
                return node;
            return this.find(key, node.left);
        }
    }

    public Node insert(int key, Node root) {
        if (root == null)
            return new Node(key, null);

        Node node = this.find(key, root);
        Node newNode = new Node(key, node);

        if (key > node.key)
            node.right = newNode;
        else
            node.left = newNode;

        return root;
    }

    public Node avlInsert(int key, Node root) {
        root = this.insert(key, root);
        Node node = this.find(key, root);
        this.rebalance(node);

        return root;
    }

    public void rebalance(Node node) {
        if (this.getHeight(node.left) > this.getHeight(node.right) + 1)
            this.rebalanceRight(node);
        if (this.getHeight(node.right) > this.getHeight(node.left) + 1)
            this.rebalanceLeft(node);

        this.adjustHeight(node);

        Node p = node.parent;
        if (p != null)
            this.rebalance(p);
    }

    public void rebalanceRight(Node node) {
        Node l = node.left;
        if (this.getHeight(l.right) > this.getHeight(l.left))
            this.rotateLeft(l);
        this.rotateRight(node);
    }

    public void rebalanceLeft(Node node) {
        Node r = node.right;
        if (this.getHeight(r.left) > this.getHeight(r.right))
            this.rotateRight(r);
        this.rotateLeft(node);
    }

    public int getHeight(Node node) {
        return node == null? 0 : node.height;
    }

    public int adjustHeight(Node node) {
        if (node == null)
            return 0;
        return node.height = Math.max(this.adjustHeight(node.left), this.adjustHeight(node.right)) + 1;
    }

    public Node getRoot(Node node) {
        if (node.parent != null)
            return this.getRoot(node.parent);
        return node;
    }

    public void bfsPrint(Node node) {
        Queue<Node> q = new LinkedList();
        q.add(node);

        while(true) {
            Node tmp = q.poll();

            if(tmp == null)
                break;

            System.out.printf("%d(%d) ", tmp.key, tmp.height);

            if (tmp.left != null)
                q.add(tmp.left);
            if (tmp.right != null)
                q.add(tmp.right);
        }
        System.out.println();
    }

    public Node avlRemove(Node node) {
        Node p = this.remove(node);

        if (p == null)
            return null;

        this.rebalance(p);

        return this.getRoot(p);
    }

    public Node remove(Node node) {
        if (node.right == null) {
            Node p = node.parent;
            Node l = node.left;

            if (l != null)
                l.parent = p;
            else
                return null;

            if (p != null) {
                if (p.left != null && p.left.key == node.key)
                    p.left = l;
                if (p.right != null && p.right.key == node.key)
                    p.right = l;

                return p;
            }
            return l;
        } else {
            Node next = this.next(node);
            Node p = next.parent;
            Node r = next.right;

            node.key = next.key;

            if (r != null)
                r.parent = p;

            if (p.left != null && p.left.key == next.key)
                p.left = r;
            if (p.right != null && p.right.key == next.key)
                p.right = r;

            return p;
        }
    }

    public Node next(Node node) {
        if (node.right != null)
            return this.leftDescendant(node.right);
        else
            return this.rightAncestor(node.key, node.parent);
    }

    public Node leftDescendant(Node node) {
        if (node.left != null)
            return this.leftDescendant(node.left);
        return node;
    }

    public Node rightAncestor(int key, Node node) {
        if (node.key > key) {
            return node;
        } else {
            if (node.parent == null)
                return null;

            return this.rightAncestor(key, node.parent);
        }
    }
}
