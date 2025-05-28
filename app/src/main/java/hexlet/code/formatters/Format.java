package hexlet.code.formatters;
import java.util.ArrayList;
import java.util.Map;

public interface Format {
    String processDiffMap(Map<String, ArrayList<Object>> map);
}
