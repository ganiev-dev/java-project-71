package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static hexlet.code.Calculatediff.calculateDiff;
import static hexlet.code.formatters.Formatter.getFormatter;
import static hexlet.code.Parse.parse;

public class Differ {
    public static String generate(String path1, String path2, String formatterType) throws Exception {

        var content1 = readFile(path1);
        var content2 = readFile(path2);
        var mapper = getFileFormat(path1);
        if (!getFileFormat(path1).equals(getFileFormat(path2))) {
            throw new IllegalArgumentException("Diff types of file");
        }

        var fileParseResult1 = parse(content1, mapper);
        var fileParseResult2 = parse(content2, mapper);

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
