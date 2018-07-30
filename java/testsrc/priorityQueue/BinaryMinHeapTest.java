package priorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryMinHeapTest {
    BinaryMinHeap heap;

    @BeforeEach
    public void setUp() {
        heap = new BinaryMinHeap();
    }

    @Test
    public void insert() {
        heap.insert(0);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);

        assertEquals("0,1,2,3,4,5,", heap.toString());
    }

    @Test
    public void extractMin() {
        heap.insert(0);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);

        assertEquals(0, heap.extractMin());
        assertEquals(1, heap.extractMin());
        assertEquals(2, heap.extractMin());
        assertEquals(3, heap.extractMin());
        assertEquals(4, heap.extractMin());
        assertEquals(5, heap.extractMin());

        assertThrows(IndexOutOfBoundsException.class, () -> heap.extractMin());
    }

    @Test
    public void remove() {
        heap.insert(0);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);

        heap.remove(2);
        assertEquals("0,1,5,3,4,", heap.toString());

        heap.remove(2);
        assertEquals("0,1,4,3,", heap.toString());

        heap.remove(2);
        heap.remove(2);
        heap.remove(0);
        heap.remove(0);

        assertThrows(IndexOutOfBoundsException.class, () -> heap.remove(0));
    }

    @Test
    public void changePriority() {
        heap.insert(0);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);

        heap.changePriority(5,  1);
        assertEquals("0,1,1,3,4,2,", heap.toString());

        heap.changePriority(2,  7);
        assertEquals("0,1,2,3,4,7,", heap.toString());
    }
}
