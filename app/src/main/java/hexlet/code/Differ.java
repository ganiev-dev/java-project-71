package hexlet.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Calculatediff.calculateDiff;
import static hexlet.code.formatters.Formatter.getFormatter;

public class Differ {
    public static String generate(String path1, String path2, String formatterType) throws Exception {

        try (BufferedReader file1 = getReader(path1);
             BufferedReader file2 = getReader(path2)) {
            var file1ParseResult = Parse.parse(file1, getFileFormat(path1));
            var file2ParseResult = Parse.parse(file2, getFileFormat(path2));
            var diff = calculateDiff(file1ParseResult, file2ParseResult);
            return getFormatter(formatterType).formatView(diff);
        }
    }
    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2,  "stylish");
    }

    public static BufferedReader getReader(String pathOfFile) throws IOException {
        return Files.newBufferedReader(Paths.get(pathOfFile));
    }

    public static String getFileFormat(String path) {
        Path pathOfFile = Paths.get(path);
        String fileName = pathOfFile.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        return dotIndex > 0 ? fileName.substring(dotIndex + 1) : "";
    }
}
