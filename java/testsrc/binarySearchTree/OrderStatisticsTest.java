package binarySearchTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStatisticsTest {

    OrderStatistics stat;

    @BeforeEach
    public void setUp() {
        stat = new OrderStatistics();
    }

    @Test
    public void find() {
        Node root = new Node(4, null, 2, 3);
        root.left = new Node(3, root, 1, 1);
        root.right = new Node(5, root, 1, 1);

        assertEquals(4, this.stat.find(4, root).key);
        assertEquals(3, this.stat.find(3, root).key);
        assertEquals(5, this.stat.find(5, root).key);
        assertEquals(3, this.stat.find(1, root).key);
        assertEquals(5, this.stat.find(6, root).key);
    }

    @Test
    public void insert() {
        Node root = this.stat.insert(1, null);
        root = this.stat.insert(2, root);
        root = this.stat.insert(3, root);

        assertEquals("1,2,3", this.stat.bfsPrint(root, "key"));
    }
    
    @Test
    public void avlInsert() {
        Node root = this.stat.avlInsert(1, null);
        root = this.stat.avlInsert(2, root);
        root = this.stat.avlInsert(3, root);
        root = this.stat.avlInsert(4, root);
        root = this.stat.avlInsert(5, root);
        root = this.stat.avlInsert(6, root);
        assertEquals("4,2,5,1,3,6", this.stat.bfsPrint(root, "key"));
        assertEquals("3,2,2,1,1,1", this.stat.bfsPrint(root, "height"));
        assertEquals("6,3,2,1,1,1", this.stat.bfsPrint(root, "size"));
    
        root = this.stat.avlInsert(6, null);
        root = this.stat.avlInsert(5, root);
        root = this.stat.avlInsert(4, root);
        root = this.stat.avlInsert(3, root);
        root = this.stat.avlInsert(2, root);
        root = this.stat.avlInsert(1, root);
        assertEquals("3,2,5,1,4,6", this.stat.bfsPrint(root, "key"));
        assertEquals("3,2,2,1,1,1", this.stat.bfsPrint(root, "height"));
        assertEquals("6,2,3,1,1,1", this.stat.bfsPrint(root, "size"));
    
        root = new Node(3, null, 4, 4);
        root.right = new Node(8, root, 3, 3);
        root.right.left = new Node(6, root.right, 2, 2);
        root.right.left.right = new Node(7, root.right.left, 1, 1);
        Node result = this.stat.rebalance(root);
        assertEquals("6,3,8,7", this.stat.bfsPrint(result, "key"));
        assertEquals("3,1,2,1", this.stat.bfsPrint(result, "height"));
        assertEquals("4,1,2,1", this.stat.bfsPrint(result, "size"));
    
        root = new Node(8, null, 4, 4);
        root.left = new Node(2, root, 3, 3);
        root.left.right = new Node(7, root.left, 2, 2);
        root.left.right.left = new Node(5, root.left.right, 1, 1);
        result = this.stat.rebalance(root);
        assertEquals("7,2,8,5", this.stat.bfsPrint(result, "key"));
        assertEquals("3,2,1,1", this.stat.bfsPrint(result, "height"));
        assertEquals("4,2,1,1", this.stat.bfsPrint(result, "size"));
    }

    @Test
    public void orderStatistic() {
        Node root = new Node(15, null, 5, 9);
        Node left = root.left = new Node(2, root, 4, 6);
        Node right = root.right = new Node(16, root, 2, 2);
        left.left = new Node(1, left, 1, 1);
        Node lr = left.right = new Node(4, left, 3, 4);
        right.right = new Node(17, right, 1, 1);
        lr.left = new Node(3, lr, 1, 1);
        Node lrr = lr.right = new Node(7, lr, 2, 2);
        lrr.left = new Node(6, lrr, 1, 1);

        assertEquals(1, this.stat.orderStatistic(root, 1).key);
        assertEquals(2, this.stat.orderStatistic(root, 2).key);
        assertEquals(3, this.stat.orderStatistic(root, 3).key);
        assertEquals(4, this.stat.orderStatistic(root, 4).key);
        assertEquals(6, this.stat.orderStatistic(root, 5).key);
        assertEquals(7, this.stat.orderStatistic(root, 6).key);
        assertEquals(15, this.stat.orderStatistic(root, 7).key);
        assertEquals(16, this.stat.orderStatistic(root, 8).key);
        assertEquals(17, this.stat.orderStatistic(root, 9).key);

        assertEquals(1, this.stat.reverseOrderStatistic(root, 1));
        assertEquals(2, this.stat.reverseOrderStatistic(root, 2));
        assertEquals(3, this.stat.reverseOrderStatistic(root, 3));
        assertEquals(4, this.stat.reverseOrderStatistic(root, 4));
        assertEquals(5, this.stat.reverseOrderStatistic(root, 6));
        assertEquals(6, this.stat.reverseOrderStatistic(root, 7));
        assertEquals(7, this.stat.reverseOrderStatistic(root, 15));
        assertEquals(8, this.stat.reverseOrderStatistic(root, 16));
        assertEquals(9, this.stat.reverseOrderStatistic(root, 17));
    }
}
