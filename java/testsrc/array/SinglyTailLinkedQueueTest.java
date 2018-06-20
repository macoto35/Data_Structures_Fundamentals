package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyTailLinkedQueueTest {

    SinglyTailLinkedQueue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new SinglyTailLinkedQueue<String>();
    }

    @Test
    void enQueue() {
        queue.enQueue("first");
        assertEquals("[first]", queue.toString());

        queue.enQueue("second");
        assertEquals("[first,second]", queue.toString());
    }

    @Test
    void deQueue() {
        assertNull(queue.deQueue());

        queue.enQueue("first");
        queue.enQueue("second");

        assertEquals("first", queue.deQueue());
        assertEquals("second", queue.deQueue());
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
}