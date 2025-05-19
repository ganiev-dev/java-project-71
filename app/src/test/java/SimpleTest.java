import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;


import static hexlet.code.Parse.getData;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleTest {
    @Test
    void testSomething() {
        System.out.println("Test is running!");
    }

    @Test
    void create() {
        System.out.println("Test is running!");
    }

    @Test
    public void testMethodThrowsException() {
        assertThrows(NoSuchFileException.class, () -> {
            getData("nullPath");
        });
    }

}
