package hexlet.code;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseTest {

    @Test
    public void createdMap() throws Exception {
        var expected = new HashMap<>();
        var actual = Parse.getData("src/test/resources/file.json");
        assertEquals(expected, actual);
    }
}
