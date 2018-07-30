package priorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class nAryHeapTest {

    public nAryHeap heap;

    @BeforeEach
    public void setUp() {
        // heap = new nAryHeap(3);
        heap = new nAryHeap(5);
    }

    @Test
    public void insert() {
        this.heap.insert(0);
        this.heap.insert(1);
        this.heap.insert(2);
        this.heap.insert(3);
        this.heap.insert(4);
        this.heap.insert(5);
        this.heap.insert(6);
        this.heap.insert(7);
        this.heap.insert(8);
        this.heap.insert(9);

        // assertEquals("9,5,8,2,0,3,4,1,6,7,", this.heap.toString());
        assertEquals("9,8,1,2,3,4,0,5,6,7,", this.heap.toString());
    }

    @Test
    public void extractMax() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.heap.extractMax());

        this.heap.insert(0);
        this.heap.insert(1);
        this.heap.insert(2);
        this.heap.insert(3);
        this.heap.insert(4);
        this.heap.insert(5);
        this.heap.insert(6);
        this.heap.insert(7);
        this.heap.insert(8);
        this.heap.insert(9);

        assertEquals(9, this.heap.extractMax());
        assertEquals(8, this.heap.extractMax());
        assertEquals(7, this.heap.extractMax());
        assertEquals(6, this.heap.extractMax());
        assertEquals(5, this.heap.extractMax());
        assertEquals(4, this.heap.extractMax());
        assertEquals(3, this.heap.extractMax());
        assertEquals(2, this.heap.extractMax());
        assertEquals(1, this.heap.extractMax());
        assertEquals(0, this.heap.extractMax());

        assertThrows(IndexOutOfBoundsException.class, () -> this.heap.extractMax());
    }

    @Test
    public void remove() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.heap.remove(0));

        this.heap.insert(0);
        this.heap.insert(1);
        this.heap.insert(2);
        this.heap.insert(3);
        this.heap.insert(4);
        this.heap.insert(5);
        this.heap.insert(6);
        this.heap.insert(7);
        this.heap.insert(8);
        this.heap.insert(9);

        this.heap.remove(2);
        // assertEquals("9,5,7,2,0,3,4,1,6,", this.heap.toString());
        assertEquals("9,8,7,2,3,4,0,5,6,", this.heap.toString());
    }

    @Test
    public void changePriority() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.heap.changePriority(0, 0));

        this.heap.insert(0);
        this.heap.insert(1);
        this.heap.insert(2);
        this.heap.insert(3);
        this.heap.insert(4);
        this.heap.insert(5);
        this.heap.insert(6);
        this.heap.insert(7);
        this.heap.insert(8);
        this.heap.insert(9);

        this.heap.changePriority(11, 2);
        // assertEquals("11,5,9,2,0,3,4,1,6,7,", this.heap.toString());
        assertEquals("11,8,9,2,3,4,0,5,6,7,", this.heap.toString());
    }
}
