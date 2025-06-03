import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Parse;
import hexlet.code.formatters.Stylish;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import static hexlet.code.Calculatediff.calculateDiff;
import static hexlet.code.Differ.getFileFormat;
import static hexlet.code.Differ.getReader;
import static hexlet.code.Parse.parse;
import static hexlet.code.formatters.Formatter.getFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ComplStrTest {
    private static String resultParse;
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }


    @BeforeAll
    public static void beforeAll() throws Exception {
        resultParse = readFixture("result_parse.txt");
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }

    static final int ONE = 1;
    static final int TWO = 2;
    static final int THREE = 3;
    static final int FOUR = 4;
    static final int FIVE = 5;
    static final int SIX = 6;
    static final int FORTYTWO = 42;
    static final int TWOHUNDRED = 200;

    @Test
    void parseNonExistedFileTest() {
        assertThrows(NoSuchFileException.class, () -> {
            BufferedReader test = getReader("none existed file path");
            parse(test, "stylish");
        });
    }

    @Test
    void parseCreateMapTest() throws Exception {
        Map<String, Object> expected = new LinkedHashMap<>();
        expected.put("array", List.of(1, 2, 3, 4));
        expected.put("boolean", true);
        expected.put("integer", 200);
        expected.put("null", null);
        expected.put("string", "Some value");

        var actual = resultParse;
        assertEquals(expected.toString(), actual);
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1tree.json, src/test/resources/file2tree.json"
    })
    void diffsToMapTest(String path1, String path2) throws Exception {
        Map<String, Object> inputMap = new LinkedHashMap<>();
        inputMap.put("array", Arrays.asList("updated", "[1, 2, 3, 4]", "[2, 7, 8, 6]"));
        inputMap.put("boolean", Arrays.asList("equal", "true"));
        inputMap.put("integer", Arrays.asList("equal", "200"));
        inputMap.put("null", Arrays.asList("removed", "null"));
        inputMap.put("obj1", Arrays.asList("added", "{nestedKey=value, isNested=true}")); // JSON-строка
        inputMap.put("string", Arrays.asList("updated", "Some value", "Another value"));
        var expected = inputMap.toString();
        Map<String, ArrayList<Object>> actual = null;
        try (BufferedReader file1 = getReader(path1);
             BufferedReader file2 = getReader(path2)) {
            var file1ParseResult = Parse.parse(file1, getFileFormat(path1));
            var file2ParseResult = Parse.parse(file2, getFileFormat(path2));
            actual = calculateDiff(file1ParseResult, file2ParseResult);
        }
        assertEquals(expected, actual.toString());
    }

    @Test
    public void stylishTest() {
        Map<String, ArrayList<Object>> inputMap = new LinkedHashMap<>();
        inputMap.put("unchanged", new ArrayList<>(Arrays.asList("equal", "value")));
        inputMap.put("removed", new ArrayList<>(Arrays.asList("removed", "oldValue")));
        inputMap.put("new", new ArrayList<>(Arrays.asList("added", "newValue")));
        inputMap.put("changed", new ArrayList<>(Arrays.asList("updated", "oldValue", "newValue")));
        inputMap.put("nested", new ArrayList<>(Arrays.asList("updated", Arrays.asList(ONE, TWO, THREE),
                Arrays.asList(FOUR, FIVE, SIX))));

        String expected = resultStylish;
        Stylish stylish = new Stylish();
        String actual = stylish.formatView(inputMap);
        assertEquals(expected, actual);
    }

    @Test
    void plainTest() throws JsonProcessingException {
        Map<String, ArrayList<Object>> input = new LinkedHashMap<>();
        input.put("unchanged", new ArrayList<>(List.of("equal", "value")));
        input.put("removed", new ArrayList<>(List.of("removed", "old")));
        input.put("added", new ArrayList<>(List.of("added", FORTYTWO)));
        input.put("updated", new ArrayList<>(List.of("updated", false, true)));

        var formatter = getFormatter("plain");
        assertEquals(resultPlain, formatter.formatView(input));
    }

    @Test
    void jsonTest() throws JsonProcessingException {
        Map<String, ArrayList<Object>> input = new LinkedHashMap<>();
        input.put("unchanged", new ArrayList<>(List.of("equal", "value")));
        input.put("removed", new ArrayList<>(List.of("removed", "old")));
        input.put("added", new ArrayList<>(List.of("added", FORTYTWO)));
        input.put("updated", new ArrayList<>(List.of("updated", false, true)));

        var formatter = getFormatter("json");
        var actual = formatter.formatView(input);
        assertEquals(resultJson, actual);
    }
}

