package hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicHashTableTest {
    DynamicHashTable dh;

    @BeforeEach
    public void setUp() {
        this.dh = new DynamicHashTable();
    }

    @Test
    public void rehash() {
        for(int i = 0 ; i < 30; i++) {
            int lengthName = ThreadLocalRandom.current().nextInt(5, 10);
            String name = "";
            for(int j = 0 ; j < lengthName; j++)
                name += (char) ThreadLocalRandom.current().nextInt(97, 122);

            this.dh.add(name, "" + ThreadLocalRandom.current().nextLong(0, 999999999999999L));
        }

        assertEquals(30, this.dh.size());
    }
}
