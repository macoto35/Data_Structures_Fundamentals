package binarySearchTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RebalancingTreeTest {

    RebalancingTree tree;

    @BeforeEach
    public void setUp() {
        tree = new RebalancingTree();
    }

    @Test
    public void rotateRight1() {
        Node root = new Node(12, null);
        Node left = root.left = new Node(7, root);
        root.right = new Node(15, root);
        left.left = new Node(5, left);
        left.right = new Node(9, left);

        this.tree.rotateRight(root);
        root = this.tree.getRoot(root);

        assertEquals(7, root.key);
        assertEquals(5, root.left.key);
        assertEquals(12, root.right.key);
        assertEquals(9, root.right.left.key);
        assertEquals(15, root.right.right.key);
    }

    @Test
    public void rotateRight2() {
        Node root = new Node(12, null);
        Node left = root.left = new Node(7, root);
        left.left = new Node(5, left);

        this.tree.rotateRight(root);
        root = this.tree.getRoot(root);

        assertEquals(7, root.key);
        assertEquals(5, root.left.key);
        assertEquals(12, root.right.key);
    }

    @Test
    public void rotateRight3() {
        Node realRoot = new Node(20, null);
        Node root = realRoot.left = new Node(12, realRoot);
        Node left = root.left = new Node(7, root);
        root.right = new Node(15, root);
        left.left = new Node(5, left);
        left.right = new Node(9, left);

        this.tree.rotateRight(root);

        assertEquals(20, realRoot.key);
        assertEquals(7, realRoot.left.key);
        assertEquals(5, realRoot.left.left.key);
        assertEquals(12, realRoot.left.right.key);
        assertEquals(9, realRoot.left.right.left.key);
        assertEquals(15, realRoot.left.right.right.key);
    }

    @Test
    public void rotateLeft1() {
        Node root = new Node(12, null);
        root.left = new Node(7, root);
        Node right = root.right = new Node(17, root);
        right.left = new Node(14, right);
        right.right = new Node(20, right);

        this.tree.rotateLeft(root);
        root = this.tree.getRoot(root);

        assertEquals(17, root.key);
        assertEquals(12, root.left.key);
        assertEquals(20, root.right.key);
        assertEquals(7, root.left.left.key);
        assertEquals(14, root.left.right.key);
    }

    @Test
    public void rotateLeft2() {
        Node root = new Node(12, null);
        Node right = root.right = new Node(17, root);
        right.right = new Node(20, right);

        this.tree.rotateLeft(root);
        root = this.tree.getRoot(root);

        assertEquals(17, root.key);
        assertEquals(12, root.left.key);
        assertEquals(20, root.right.key);
    }

    @Test
    public void rotateLeft3() {
        Node realRoot = new Node(6, null);
        Node root = realRoot.right = new Node(12, realRoot);
        root.left = new Node(7, root);
        Node right = root.right = new Node(17, root);
        right.left = new Node(14, right);
        right.right = new Node(20, right);

        this.tree.rotateLeft(root);

        assertEquals(6, realRoot.key);
        assertEquals(17, realRoot.right.key);
        assertEquals(12, realRoot.right.left.key);
        assertEquals(20, realRoot.right.right.key);
        assertEquals(7, realRoot.right.left.left.key);
        assertEquals(14, realRoot.right.left.right.key);
    }
}
