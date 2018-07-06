package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckBracketsInTheCodeTest {

    CheckBracketsInTheCode check;

    @BeforeEach
    void setUp() {
        check = new CheckBracketsInTheCode();
    }

    @Test
    void run() {
        assertEquals("success", check.run("[]"));
        assertEquals("success", check.run("{}[]"));
        assertEquals("success", check.run("[()]"));
        assertEquals("success", check.run("(())"));
        assertEquals("success", check.run("{[]}()"));
        assertEquals("1", check.run("{"));
        assertEquals("3", check.run("{[}"));
        assertEquals("success", check.run("foo(bar);"));
        assertEquals("10", check.run("foo(bar[i);"));
        assertEquals("1", check.run("}"));
    }
}