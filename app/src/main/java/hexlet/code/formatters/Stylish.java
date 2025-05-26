package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Stylish implements Formatter {

    @Override
    public Map<String, String> processDiffMap(Map<String, ArrayList<String>> map) {
        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        for (var entry : map.entrySet()) {
            var key = entry.getKey();
            var keyDiff = entry.getValue().getFirst();
            var value = entry.getValue();

            switch (keyDiff) {
                case "equal":
                    result.put(" ".repeat(4) + key, value.get(1));
                    break;
                case "deleted":
                    result.put(" ".repeat(2) + "- " + key, value.get(1));
                    break;
                case "added":
                    result.put(" ".repeat(2) + "+ " + key, value.get(1));
                    break;
                case "changed":
                    result.put(" ".repeat(2) + "- " + key, value.get(1));
                    result.put(" ".repeat(2) + "+ " + key, value.get(2));
                    break;
                default:
                    System.out.println("Я никогда не произойду");
            }
        }
        return result;
    }

}
