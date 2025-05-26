package hexlet.code;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Comparator;


public class Diffs {
    public static Map<String, ArrayList<String>> getDifferents(String path1, String path2) throws Exception {
        var file1 = Parse.parse(path1);
        var file2 = Parse.parse(path2);
        var diffmap = new HashMap<String, ArrayList<String>>();

        // Первая мапа
        for (var entry : file1.entrySet()) {
            var key = entry.getKey();
            var value1 = Objects.toString(entry.getValue(), "null");
            var value2 = file2.containsKey(key)
                    ? Objects.toString(file2.get(key), "null")
                    : null;

            if (!file2.containsKey(key)) {
                diffmap.put(key, newArrList("deleted", value1));
            } else if (value1.equals(value2)) {
                diffmap.put(key, newArrList("equal", value1));
            } else {
                diffmap.put(key, newArrList("changed", value1, value2));
            }
        }

        // Вторая мапа
        for (var entry : file2.entrySet()) {
            var key = entry.getKey();
            if (!file1.containsKey(key)) {
                var value = Objects.toString(entry.getValue(), "null");
                diffmap.put(key, newArrList("added", value));
            }
        }

        return diffmap.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

    }
    public static void toPrint(Map<String, String> map) {
        StringBuilder str = new StringBuilder();
        for (var entry: map.entrySet()) {
            str.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        str.insert(0, "{\n");
        str.insert(str.length(), "}");

        System.out.println(str);
    }
    public static ArrayList<String> newArrList(String... str) {
        return new ArrayList<String>(Arrays.asList(str));
    }
}
