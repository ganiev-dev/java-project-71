package hexlet.code.formatters;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Map;

public interface Format {
    String formatView(Map<String, ArrayList<Object>> map) throws JsonProcessingException;
}
