package hexlet.code.formatters;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Map;

public interface Format {
    String formatView(Map<String, List<Object>> map) throws JsonProcessingException;
}
