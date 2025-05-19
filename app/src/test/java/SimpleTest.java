import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Map;


import static hexlet.code.Parse.getData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleTest {
    private static String jsonPath1;
    private static String jsonPath2;

    @BeforeAll
    public static void init() {
        jsonPath1 = "src/test/resources/file1.json";
        jsonPath2 = "src/test/resources/file2.json";
    }

    @Test
    void testNonExistedFile() {
        assertThrows(NoSuchFileException.class, () -> {
            getData("nullPath");
        });
    }

    @Test
    void testHashmapFromFile() throws Exception {
        Map<String, Object> expected = new HashMap<>();
        expected.put("host", "hexlet.io");
        expected.put("timeout", 50);
        expected.put("proxy", "123.234.53.22");
        expected.put("follow", false);

        var actual = getData("src/test/resources/file1.json");
        assertEquals(expected, actual);
    }



}
