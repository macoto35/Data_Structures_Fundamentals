package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    ArrayStack<String>  stack;

    @BeforeEach
    void setUp() {
        stack = new ArrayStack<String>();
    }

    @Test
    void push() {
        stack.push("first");
        assertEquals("[first]", stack.toString());

        stack.push("second");
        assertEquals("[first,second]", stack.toString());
    }

    @Test
    void top() {
        assertEquals(null, stack.top());

        stack.push("first");
        stack.push("second");
        assertEquals("second", stack.top());

        stack.pop();
        assertEquals("first", stack.top());
    }

    @Test
    void pop() {
        assertEquals(null, stack.pop());

        stack.push("first");
        stack.push("second");
        assertEquals("second", stack.pop());
    }

    @Test
    void empty() {
        assertEquals(true, stack.empty());

        stack.push("first");
        stack.push("second");
        assertEquals(false, stack.empty());

        stack.pop();
        stack.pop();
        assertEquals(true, stack.empty());
    }
}