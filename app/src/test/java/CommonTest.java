import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static hexlet.code.Diffs.getDifferents;
import static hexlet.code.Diffs.toPrint;
import static hexlet.code.Parse.getData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommonTest {
    @Test
    void testNonExistedFile() {
        assertThrows(NoSuchFileException.class, () -> {
            getData("nonExistedPath");
        });
    }

    @Test
    void testNonExistedFile2() {
        assertThrows(NoSuchFileException.class, () -> {
            getDifferents("nonExistedPath", "src/test/resources/file2.json");
        });
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1.json, src/test/resources/file2.json",
        "src/test/resources/file1.yaml, src/test/resources/file2.yaml"
    })
    void testHashmapFromFile(String path1, String path2) throws Exception {
        Map<String, Object> expected = new HashMap<>();
        expected.put("host", "hexlet.io");
        expected.put("timeout", 50);
        expected.put("proxy", "123.234.53.22");
        expected.put("follow", false);

        var actual = getData(path1);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1.json, src/test/resources/file2.json",
        "src/test/resources/file1.yaml, src/test/resources/file2.yaml"
    })
    void testDiffsTwoMap(String path1, String path2) throws Exception {
        Map<String, Object> expected = new LinkedHashMap<>();
        expected.put("- follow", false);
        expected.put("  host", "hexlet.io");
        expected.put("- proxy", "123.234.53.22");
        expected.put("- timeout", 50);
        expected.put("+ timeout", 20);
        expected.put("+ verbose", true);
        System.out.println(expected);

        var actual = getDifferents(path1, path2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1.json, src/test/resources/file2.json",
        "src/test/resources/file1.yaml, src/test/resources/file2.yaml"
    })
    void testDiffsOutput(String path1, String path2) throws Exception {
        // Первый вывод
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out1));
        toPrint(getDifferents(path1, path2));
        String actual = out1.toString();

        // Второй вывод (ожидаемый)
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out2));
        System.out.println("- follow: false");
        System.out.println("  host: hexlet.io");
        System.out.println("- proxy: 123.234.53.22");
        System.out.println("- timeout: 50");
        System.out.println("+ timeout: 20");
        System.out.println("+ verbose: true");

        String expected = out2.toString();

        assertEquals(expected, actual);
    }

}
