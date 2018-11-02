package binarySearchTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    AVLTree tree;

    @BeforeEach
    public void setUp() {
        tree = new AVLTree();
    }

    @Test
    public void find() {
        Node root = new Node(13, null);
        Node left = root.left = new Node(10, root);
        Node right = root.right = new Node(15, root);
        left.left = new Node(5, left);
        left.right = new Node(11, left);
        right.left = new Node(14, right);
        right.right = new Node(16, right);

        assertEquals(13, this.tree.find(13, root).key);
        assertEquals(5, this.tree.find(5, root).key);
        assertEquals(16, this.tree.find(16, root).key);
        assertEquals(16, this.tree.find(17, root).key);
        assertEquals(5, this.tree.find(3, root).key);
    }

    @Test
    public void insert() {
/*
                  13
             10        15
          5     11        16
        4   6
*/
        Node root = this.tree.insert(13, null);
        root = this.tree.insert(10, root);
        root = this.tree.insert(15, root);
        root = this.tree.insert(5, root);
        root = this.tree.insert(11, root);
        root = this.tree.insert(16, root);
        root = this.tree.insert(4, root);
        root = this.tree.insert(6, root);

        assertEquals(13, root.key);
        assertEquals(10, root.left.key);
        assertEquals(15, root.right.key);
        assertEquals(5, root.left.left.key);
        assertEquals(11, root.left.right.key);
        assertEquals(16, root.right.right.key);
        assertEquals(4, root.left.left.left.key);
        assertEquals(6, root.left.left.right.key);
    }

    @Test
    public void avlInsertRightRotate() {
        Node root = this.tree.avlInsert(13, null);
        root = this.tree.avlInsert(10, root);
        root = this.tree.avlInsert(5, root);

        root = this.tree.getRoot(root);

        assertEquals(10, root.key);
        assertEquals(2, root.height);
        assertEquals(5, root.left.key);
        assertEquals(13, root.right.key);
    }

    @Test
    public void avlInsertLeftRotate() {
        Node root = this.tree.avlInsert(13, null);
        root = this.tree.avlInsert(15, root);
        root = this.tree.avlInsert(16, root);

        root = this.tree.getRoot(root);

        assertEquals(15, root.key);
        assertEquals(2, root.height);
        assertEquals(13, root.left.key);
        assertEquals(16, root.right.key);
    }

/*
         13
     10      15
   5   11      16
 4  6
     (7)
*/
    @Test
    public void avlInsertLeftRightRotate() {
        Node root = new Node(13, null, 4);
        Node left = root.left = new Node(10, root, 3);
        Node right = root.right = new Node(15, root, 2);
        Node le = left.left = new Node(5, left, 2);
        left.right = new Node(11, left, 1);
        right.right = new Node(16, right, 1);
        le.left = new Node(4, le, 1);
        le.right = new Node(6, le, 1);

        root = this.tree.avlInsert(7, root);

        root = this.tree.getRoot(root);
        System.out.print("avlInsertLeftRightRotate: ");
        tree.bfsPrint(root);

        assertEquals(6, root.left.key);
        assertEquals(5, root.left.left.key);
        assertNull(root.left.left.right);
        assertEquals(10, root.left.right.key);
        assertEquals(7, root.left.right.left.key);
    }

/*
         5
     2       7
   1   4   6   9
     3          16
              (15)
*/
    @Test
    public void avlInsertRightLeftRotate() {
        Node root = new Node(5, null, 4);
        Node left = root.left = new Node(2, root, 3);
        Node right = root.right = new Node(7, root, 3);
        left.left = new Node(1, left, 1);
        Node lr = left.right = new Node(4, left, 2);
        right.left = new Node(6, right, 1);
        Node rr = right.right = new Node(9, right, 2);
        lr.left = new Node(3, lr, 1);
        rr.right = new Node(16, rr, 1);

        root = this.tree.avlInsert(15, root);

        root = this.tree.getRoot(root);
        System.out.print("avlInsertRightLeftRotate: ");
        this.tree.bfsPrint(root);

        assertEquals(7, root.right.key);
        assertEquals(6, root.right.left.key);
        assertEquals(15, root.right.right.key);
        assertEquals(2, root.right.right.height);
        assertEquals(9, root.right.right.left.key);
        assertEquals(1, root.right.right.left.height);
        assertEquals(16, root.right.right.right.key);
    }

/*
         5
     2       7
   1   4   6   9
*/
    @Test
    public void avlRemove() {
        Node root = new Node(5, null, 3);
        Node left = root.left = new Node(2, root, 2);
        Node right = root.right = new Node(7, root, 2);
        left.left = new Node(1, left, 1);
        left.right = new Node(4, left, 1);
        right.left = new Node(6, right, 1);
        right.right = new Node(9, right, 1);

        for (int i = 0 ; i < 7; i++) {
            root = this.tree.avlRemove(root);
            System.out.print("avlRemove: ");
            this.tree.bfsPrint(root);
        }
    }
}
