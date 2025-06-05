package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static hexlet.code.Differ.getFileFormat;

public class Parse {
    public static Map<String, Object> parse(String fileContent, String filename) throws
            IOException, IllegalStateException {
        var format = getFileFormat(filename);
        ObjectMapper mapper = switch (format) {
            case "json" -> new ObjectMapper();
            case "yaml" -> new YAMLMapper();
            case "yml" -> new YAMLMapper();
            default -> throw new IllegalStateException("Формат файла не поддерживается: " + format);
        };

        return mapper.readValue(fileContent,
            new TypeReference<HashMap<String, Object>>() { }); //Распарсиваем в мапу и возвращаем
    }

}

