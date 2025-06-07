package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.List;
import java.util.Map;

public final class Json implements Format {
    @Override
    public String formatView(Map<String, List<Object>> map) throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper().writer(new DefaultPrettyPrinter());
        return writer.writeValueAsString(map);
    }
}
