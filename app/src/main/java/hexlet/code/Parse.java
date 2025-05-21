package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parse {
    public static Map<String, Object> getData(String filepath) throws Exception {
        var path = Paths.get(filepath); //Получаем объект Path из пути в параметрах
        var reader = Files.newBufferedReader(path); //Создаем новый ридер из файла
        var format = getFormat(filepath);

        ObjectMapper mapper = switch (format) {
            case "json" ->  new ObjectMapper();
            case "yaml" -> new YAMLMapper();
            default -> throw new NoSuchFileException("Формат файла не поддерживается");
        };

        return mapper.readValue(reader,
                new TypeReference<Map<String, Object>>() { }); //Распарсиваем в мапу и возвращаем
    }

    public static String getFormat(String path) {
        Path pathOfFile = Paths.get(path);
        String fileName = pathOfFile.getFileName().toString(); // "image.jpg"
        int dotIndex = fileName.lastIndexOf('.');
        return dotIndex > 0 ? fileName.substring(dotIndex + 1) : "";
    }
}
