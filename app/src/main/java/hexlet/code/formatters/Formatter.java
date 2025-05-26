package hexlet.code.formatters;
import java.util.ArrayList;
import java.util.Map;

public interface Formatter {
    Map<String, String> processDiffMap(Map<String, ArrayList<String>> map);
}
