package priorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    PriorityQueue pq;

    @BeforeEach
    public void setUp() {
        pq = new PriorityQueue();
    }

    /*@Test
    public void parent() {
        assertEquals(0, pq.parent(0));
        assertEquals(0, pq.parent(2));
    }

    @Test
    public void leftChild() {
        assertEquals(1, pq.leftChild(0));
        assertEquals(5, pq.leftChild(2));
    }

    @Test
    public void rightChild() {
        assertEquals(2, pq.rightChild(0));
        assertEquals(6, pq.rightChild(2));
    }

    @@Test
    public void shiftUp() {
        pq.shiftTest();
        pq.shiftUp(6);
        assertEquals("42,29,32,14,7,18,12,11,", pq.toString());
    }

    @Test
    public void shiftDown() {
        pq.shiftTest();
        pq.shiftDown(1);
        assertEquals("29,14,18,11,7,18,12,4,", pq.toString());
    }*/

    @Test
    public void insert() {
        pq.insert(42);
        pq.insert(29);
        pq.insert(18);
        pq.insert(14);
        pq.insert(7);
        pq.insert(18);
        pq.insert(12);
        pq.insert(11);
        assertEquals("42,29,18,14,7,18,12,11,", pq.toString());

        pq.insert(11);
        pq.insert(11);
        pq.insert(11);
        pq.insert(11);
        pq.insert(11);
        pq.insert(11);
        pq.insert(11);
        assertThrows(IndexOutOfBoundsException.class, () -> pq.insert(1));
    }

    @Test
    public void extractMax() {
        assertThrows(NullPointerException.class, () -> pq.extractMax());

        pq.insert(42);
        pq.insert(29);
        pq.insert(18);
        pq.insert(14);
        pq.insert(7);
        pq.insert(18);
        pq.insert(12);
        pq.insert(11);

        assertEquals(42, pq.extractMax());
        assertEquals("29,14,18,11,7,18,12,", pq.toString());
    }

    @Test
    public void remove() {
        assertThrows(NullPointerException.class, () -> pq.remove(1));

        pq.insert(42);
        pq.insert(29);
        pq.insert(18);
        pq.insert(14);
        pq.insert(7);
        pq.insert(18);
        pq.insert(12);
        pq.insert(11);

        pq.remove(5);
        assertEquals("42,29,18,14,7,11,12,", pq.toString());
    }

    @Test
    public void changePriority() {
        assertThrows(NullPointerException.class, () -> pq.changePrority(0, 1));

        pq.insert(42);
        pq.insert(29);
        pq.insert(18);
        pq.insert(14);
        pq.insert(7);
        pq.insert(18);
        pq.insert(12);
        pq.insert(11);

        pq.changePrority(7, 75);
        assertEquals("75,42,18,29,7,18,12,14,", pq.toString());

        pq.changePrority(1, 12);
        assertEquals("75,29,18,14,7,18,12,12,", pq.toString());

        assertThrows(NullPointerException.class, () -> pq.changePrority(8, 10));
    }
}
