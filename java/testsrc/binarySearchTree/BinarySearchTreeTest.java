package binarySearchTree;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {
    BinarySearchTree tree;

    @BeforeEach
    public void setUp() {
        tree = new BinarySearchTree();
    }

    /*
             7
        4        13
      1   6    10  15
    */
    private Node getRoot() {
        Node root = new Node(7, null);
        Node left = root.left = new Node(4, root);
        Node right = root.right = new Node(13, root);
        left.left = new Node(1, left);
        left.right = new Node(6, left);
        right.left = new Node(10, right);
        right.right = new Node(15, right);

        return root;
    }

    @Test
    public void find() {
        Node root = this.getRoot();

        assertEquals(7, this.tree.find(7, root).key);
        assertEquals(13, this.tree.find(13, root).key);
        assertEquals(6, this.tree.find(6, root).key);
        assertEquals(10, this.tree.find(10, root).key);
        assertEquals(1, this.tree.find(0, root).key);
        assertEquals(1, this.tree.find(2, root).key);
        assertEquals(10, this.tree.find(8, root).key);
        assertEquals(15, this.tree.find(16, root).key);
    }

    @Test
    public void next() {
        Node root = this.getRoot();

        assertEquals(4, this.tree.next(this.tree.find(1, root)).key);
        assertEquals(7, this.tree.next(this.tree.find(6, root)).key);
        assertEquals(6, this.tree.next(this.tree.find(4, root)).key);
        assertEquals(10, this.tree.next(this.tree.find(7, root)).key);
        assertNull(this.tree.next(this.tree.find(15, root)));
    }

    @Test
    public void rangeSearch() {
        Node root = this.getRoot();
        assertArrayEquals(new Integer[]{6,7,10}, this.tree.rangeSearch(5, 12, root));
        assertArrayEquals(new Integer[]{1}, this.tree.rangeSearch(1,3, root));
        assertArrayEquals(new Integer[]{}, this.tree.rangeSearch(16,20, root));
    }

    @Test
    public void insert() {
        /*
                7
            4       13
         1    6   10   15
          3
        */
        Node root = this.tree.insert(7, null);
        root = this.tree.insert(4, root);
        root = this.tree.insert(13, root);
        root = this.tree.insert(1, root);
        root = this.tree.insert(6, root);
        root = this.tree.insert(10, root);
        root = this.tree.insert(15, root);
        root = this.tree.insert(3, root);

        assertEquals(7, root.key);
        assertEquals(4, root.left.key);
        assertEquals(13, root.right.key);
        assertEquals(1, root.left.left.key);
        assertEquals(6, root.left.right.key);
        assertEquals(10, root.right.left.key);
        assertEquals(15, root.right.right.key);
        assertEquals(3, root.left.left.right.key);

        List<Integer> result = new ArrayList();
        this.tree.toString(root, result);
        assertEquals("1,3,4,6,7,10,13,15", result.stream().map(i -> i.toString()).collect(Collectors.joining(",")));
    }

    @Test
    public void delete() {
        Node root = this.tree.insert(7, null);
        root = this.tree.insert(4, root);
        root = this.tree.insert(13, root);
        root = this.tree.insert(1, root);
        root = this.tree.insert(6, root);
        root = this.tree.insert(10, root);
        root = this.tree.insert(15, root);
        root = this.tree.insert(3, root);
        this.tree.root = root;

        for (int i = 0 ; i < 8 ; i++) {
            // this.tree.root = this.tree.remove(this.tree.root);
            this.tree.remove(this.tree.root);
            List<Integer> result = new ArrayList<Integer>();
            this.tree.toString(this.tree.root, result);
            System.out.println("result: " + result.stream().map(val -> val.toString()).collect(Collectors.joining(",")));
        }
    }
}