import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {
    private static String resultCalculate;
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
        resultCalculate = readFixture("resultCalc.txt");
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }
    String getTestStr(String filepath1, String filepath2) throws Exception {
        return Differ.generate(filepath1, filepath2);
    }
    String getTestStr(String filepath1, String filepath2, String formatter) throws Exception {
        return Differ.generate(filepath1, filepath2, formatter);
    }

    @Test
    void parseNonExistedFileTest() {
        assertThrows(NoSuchFileException.class, () -> {
            var result = readFixture("none existed file path");
            parse(result, "stylish");
        });
    }

    @Test
    void diffsJsonCalcTest() throws Exception {
        var expected = resultCalculate;
        var actual = getTestStr("file1tree.json", "file2tree.json");
        assertEquals(expected, actual);
    }

    @Test
    public void jsonToDefaultTest() throws Exception {
        var actual = getTestStr("file1tree.json", "file2tree.json");
        assertEquals(resultStylish, actual);
    }

    @Test
    void jsonToStylishTest() throws Exception {
        var actual = getTestStr("file1tree.json", "file2tree.json", "stylish");
        assertEquals(resultStylish, actual);
    }
    @Test
    void jsonToPlainTest() throws Exception {
        var actual = getTestStr("file1tree.json", "file2tree.json", "plain");
        assertEquals(resultPlain, actual);
    }

    @Test
    void jsonToJsonTest() throws Exception {
        var actual = getTestStr("file1tree.json", "file2tree.json", "json");
        assertEquals(resultJson, actual);
    }

    @Test
    public void ymlToDefaultTest() throws Exception {
        var actual = getTestStr("file1tree.yml", "file2tree.yml");
        assertEquals(resultStylish, actual);
    }

    @Test
    public void ymlToStylishTest() throws Exception {
        var actual = getTestStr("file1tree.yml", "file2tree.yml", "stylish");
        assertEquals(resultStylish, actual);
    }
    @Test
    void ymlToPlainTest() throws Exception {
        var actual = getTestStr("file1tree.yml", "file2tree.yml", "plain");
        assertEquals(resultPlain, actual);
    }

    @Test
    void ymlToJsonTest() throws Exception {
        var actual = getTestStr("file1tree.yml", "file2tree.yml", "json");
        assertEquals(resultJson, actual);
    }
}

