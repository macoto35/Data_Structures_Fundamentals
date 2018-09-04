package hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberToNameTest {
    PhoneNumberToName hash;

    @BeforeEach
    public void setUp() {
        this.hash = new PhoneNumberToName();
    }

    @Test
    public void get() {
        this.hash.add("999-9999-9999-9999", "Emma");
        this.hash.add("000-0000-0000-0000", "Harry");
        this.hash.add("123-4567-8901-2345", "Mery");
        this.hash.add("000-0000-0000-0000", "Ron");

        assertEquals("Emma", this.hash.get("999-9999-9999-9999"));
        assertEquals("Ron", this.hash.get("000-0000-0000-0000"));
        assertEquals("Mery", this.hash.get("123-4567-8901-2345"));
        assertNull(this.hash.get("223-4567-8901-2345"));
    }
}
