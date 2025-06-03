package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parse {
    public static Map<String, Object> parse(BufferedReader reader, String format) throws
            IOException, IllegalStateException {
        ObjectMapper mapper = switch (format) {
            case "json" -> new ObjectMapper();
            case "yaml" -> new YAMLMapper();
            case "yml" -> new YAMLMapper();
            default -> throw new IllegalStateException("Формат файла не поддерживается: " + format);
        };
        return mapper.readValue(reader,
            new TypeReference<HashMap<String, Object>>() { }); //Распарсиваем в мапу и возвращаем
    }
}

