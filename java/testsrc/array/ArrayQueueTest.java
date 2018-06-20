package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

    ArrayQueue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new ArrayQueue<String>();
    }

    @Test
    void enQueue() {
        queue.enQueue("first");
        assertEquals("[first]", queue.toString());

        queue.enQueue("second");
        assertEquals("[first,second]", queue.toString());

        queue.enQueue("third");
        queue.enQueue("fourth");

        assertThrows(IllegalStateException.class, () -> queue.enQueue("fifth"));
    }

    @Test
    void empty() {
        assertTrue(queue.empty());

        queue.enQueue("first");
        queue.enQueue("second");
        assertFalse(queue.empty());

        queue.deQueue();
        queue.deQueue();
        assertTrue(queue.empty());
    }

    @Test
    void deQueue() {
        assertNull(queue.deQueue());

        queue.enQueue("first");
        queue.enQueue("second");
        queue.enQueue("third");

        assertEquals("first", queue.deQueue());
        assertEquals("second", queue.deQueue());

        queue.enQueue("fourth");
        queue.enQueue("fifth");
        queue.enQueue("sixth");

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        assertNull(queue.deQueue());
    }
}