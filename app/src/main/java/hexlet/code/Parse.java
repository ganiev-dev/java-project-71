package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parse {
    public static Map<String, Object> parse(String fileContent, String mapper) throws
            IOException, IllegalStateException {

        ObjectMapper typeOfmapper = switch (mapper) {
            case "json" -> new ObjectMapper();
            case "yaml" -> new YAMLMapper();
            case "yml" -> new YAMLMapper();
            default -> throw new IllegalStateException("Illegal type of mapper: " + mapper);
        };

        return typeOfmapper.readValue(fileContent,
            new TypeReference<HashMap<String, Object>>() { }); //Распарсиваем в мапу и возвращаем
    }
}

