package hexlet.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import static hexlet.code.formatters.Formatter.getFormatter;

public class Differ {
    public static String generate(String path1, String path2, String formatName) throws Exception {
        var diff = getDifferents(path1, path2);
        var formatter = getFormatter(formatName);
        return formatter.processDiffMap(diff);
    }

    public static Map<String, ArrayList<Object>> getDifferents(String path1, String path2) throws Exception {
        var file1 = Parse.parse(path1);
        var file2 = Parse.parse(path2);
        var diffmap = new HashMap<String, ArrayList<Object>>();

        // мапа1
        for (Map.Entry<String, Object> entry : file1.entrySet()) {
            String key = entry.getKey();
            Object value1 = entry.getValue();
            Object value2 = file2.get(key);

            if (!file2.containsKey(key)) {
                diffmap.put(key, new ArrayList<>(Arrays.asList("removed", value1)));
            } else if (Objects.deepEquals(value1, value2)) {
                diffmap.put(key, new ArrayList<>(Arrays.asList("equal", value1)));
            } else {
                diffmap.put(key, new ArrayList<>(Arrays.asList("updated", value1, value2)));
            }
        }

        // мапа2
        for (Map.Entry<String, Object> entry : file2.entrySet()) {
            String key = entry.getKey();
            if (!file1.containsKey(key)) {
                diffmap.put(key, new ArrayList<>(Arrays.asList("added", entry.getValue())));
            }
        }

        return diffmap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

    }
}
