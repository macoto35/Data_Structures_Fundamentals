package hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NameToPhoneNumberTest {
    NameToPhoneNumber hash;

    @BeforeEach
    public void setUp() {
        this.hash = new NameToPhoneNumber();
    }

    @Test
    public void get() {
        this.hash.add("Emma", "999-9999-9999-9999");
        this.hash.add("Harry", "000-0000-0000-0000");
        this.hash.add("Mery", "123-4567-8901-2345");
        this.hash.add("Harry", "100-0000-0000-0000");

        assertEquals("999-9999-9999-9999", this.hash.get("Emma"));
        assertEquals("100-0000-0000-0000", this.hash.get("Harry"));
        assertEquals("123-4567-8901-2345", this.hash.get("Mery"));
        assertNull(this.hash.get("Ron"));
    }
}
