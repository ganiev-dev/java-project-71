package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Calculatediff.calculateDiff;
import static hexlet.code.formatters.Formatter.getFormatter;

public class Differ {
    public static String generate(String path1, String path2, String formatterType) throws Exception {

        var fileContent1 = readFile(path1);
        var fileContent2 = readFile(path2);

        var fileParseResult1 = Parse.parse(fileContent1, path1);
        var fileParseResult2 = Parse.parse(fileContent2, path2);

        var diff = calculateDiff(fileParseResult1, fileParseResult2);
        return getFormatter(formatterType).formatView(diff);

    }
    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2,  "stylish");
    }

    private static Path getFilePath(String fileName) {
        Path path = Paths.get(fileName);
        if (path.isAbsolute()) {
            return path.normalize();
        }
        return Paths.get("src", "main", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    public static String getFileFormat(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return dotIndex > 0 ? fileName.substring(dotIndex + 1) : "";
    }

    private static String readFile(String fileName) throws Exception {
        Path filePath = getFilePath(fileName);
        return Files.readString(filePath).trim();
    }


}
