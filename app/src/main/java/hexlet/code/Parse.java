package hexlet.code;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parse {
    public static Map<String, Object> getData(String filepath) throws Exception {
        var path = Paths.get(filepath); //Получаем объект Path из пути в параметрах
        var reader = Files.newBufferedReader(path); //Создаем новый ридер из файла
        ObjectMapper objectMapper = new ObjectMapper(); //маппер джексона
        return objectMapper.readValue(reader,
                new TypeReference<Map<String, Object>>() { }); //Распарсиваем в мапу и возвращаем
    }
}
