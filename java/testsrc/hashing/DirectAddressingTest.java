package hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirectAddressingTest {

    DirectAddressing da;

    @BeforeEach
    public void setUp() {
        da = new DirectAddressing();
    }

    @Test
    public void getName() {
        da.setName("123-45-67", "Maria");
        da.setName("049-12-12", "Helen");
        da.setName("575-75-75", "Sarah");

        assertEquals("Maria", da.getName("123-45-67"));
        assertEquals("Helen", da.getName("049-12-12"));
        assertEquals("Sarah", da.getName("575-75-75"));
        assertNull(da.getName("444-44-44"));
    }
}
