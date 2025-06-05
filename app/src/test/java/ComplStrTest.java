import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Parse.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ComplStrTest {
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
    void diffsYmlCalcTest() throws Exception {
        var expected = resultCalculate;
        var actual = getTestStr("file1.yml", "file2.yml");
        assertEquals(expected, actual);
    }

    @Test
    public void stylishTest() throws Exception {
        var actual = getTestStr("file1tree.json", "file2tree.json", "stylish");
        assertEquals(resultStylish, actual);
    }

    @Test
    void plainTest() throws Exception {
        var actual = getTestStr("file1tree.json", "file2tree.json", "plain");
        assertEquals(resultPlain, actual);
    }

    @Test
    void jsonTest() throws Exception {
        var actual = getTestStr("file1tree.json", "file2tree.json", "json");
        assertEquals(resultJson, actual);
    }
}

