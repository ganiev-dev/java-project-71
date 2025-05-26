import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static hexlet.code.Diffs.getDifferents;
import static hexlet.code.Diffs.toPrint;
import static hexlet.code.Parse.parse;
import static hexlet.code.formatters.FormatterFactory.getFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ComplStrTest {
    @ParameterizedTest
    @CsvSource({
        "src/test/resources/fake.json",
        "|empty_value"
    })
    void parseNonExistedFileTest() {
        assertThrows(NoSuchFileException.class, () -> {
            parse("nonExistedPath");
        });
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/testMap.json"
    })
    void parseCreateMapTest(String path) throws Exception {
        Map<String, Object> expected = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        expected.put("string", "Some value");
        expected.put("integer", 200);
        expected.put("boolean", true);
        expected.put("array", list);
        expected.put("null", null);

        var actual = parse(path);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1tree.json, src/test/resources/file2tree.json"
    })
    void diffsToMapTest(String path1, String path2) throws Exception {
        Map<String, Object> expected = new LinkedHashMap<>();
        expected.put("array", Arrays.asList("changed", "[1, 2, 3, 4]", "[2, 7, 8, 6]"));
        expected.put("boolean", Arrays.asList("equal", "true"));
        expected.put("integer", Arrays.asList("equal", "200"));
        expected.put("null", Arrays.asList("deleted", "null"));
        expected.put("obj1", Arrays.asList("added", "{nestedKey=value, isNested=true}")); // JSON-строка
        expected.put("string", Arrays.asList("changed", "Some value", "Another value"));
        System.out.println(expected);

        var actual = getDifferents(path1, path2);
        assertEquals(expected, actual);
    }
    @Test
    void stylishTest() {
        Map<String, ArrayList<String>> inputMap = new LinkedHashMap<>();
        inputMap.put("array", new ArrayList<>(Arrays.asList("changed", "[1, 2, 3, 4]", "[2, 7, 8, 6]")));
        inputMap.put("boolean", new ArrayList<>(Arrays.asList("equal", "true")));

        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("  - array", "[1, 2, 3, 4]");
        expected.put("  + array", "[2, 7, 8, 6]");
        expected.put("    boolean", "true");

        var formatter = getFormatter("stylish");
        Map<String, String> actual = formatter.processDiffMap(inputMap);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1tree.json, src/test/resources/file2tree.json"
    })
    void diffsOutputTest(String path1, String path2) throws Exception {
        // Первый вывод
        Map<String, ArrayList<String>> diffMap = new LinkedHashMap<>();
        diffMap.put("array", new ArrayList<>(Arrays.asList("changed", "[1, 2, 3, 4]", "[2, 7, 8, 6]")));
        diffMap.put("boolean", new ArrayList<>(Arrays.asList("equal", "true")));
        var formatter = getFormatter("stylish");
        var formatedResult = formatter.processDiffMap(diffMap);

        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out1));

        toPrint(formatedResult);
        String actual = out1.toString();

        // Второй вывод (ожидаемый)
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out2));

        System.out.println("{");
        System.out.println("  - array: [1, 2, 3, 4]");
        System.out.println("  + array: [2, 7, 8, 6]");
        System.out.println("    boolean: true");
        System.out.println("}");

        String expected = out2.toString();

        assertEquals(expected, actual);
    }
}
