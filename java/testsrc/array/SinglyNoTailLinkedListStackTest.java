package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyNoTailLinkedListStackTest {

    SinglyNoTailLinkedListStack<String> stack;

    @BeforeEach
    void setUp() {
        stack = new SinglyNoTailLinkedListStack<String>();
    }

    @Test
    void push() {
        stack.push("first");
        assertEquals("[first]", stack.toString());

        stack.push("second");
        assertEquals("[second,first]", stack.toString());
    }

    @Test
    void top() {
        assertEquals(null, stack.top());

        stack.push("first");
        assertEquals("first", stack.top());

        stack.push("second");
        assertEquals("second", stack.top());
    }

    @Test
    void pop() {
        assertNull(stack.pop());

        stack.push("first");
        stack.push("second");

        assertEquals("[second,first]", stack.toString());
        assertEquals("second", stack.pop());
        assertEquals("[first]", stack.toString());
        assertEquals("first", stack.pop());
        assertNull(stack.pop());
    }

    @Test
    void empty() {
        assertTrue(stack.empty());

        stack.push("first");
        stack.push("second");
        assertFalse(stack.empty());

        stack.pop();
        stack.pop();
        assertTrue(stack.empty());
    }
}