package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Map;

public class Plain implements Format {
    @Override
    public String formatView(Map<String, ArrayList<Object>> map) {

        StringBuilder resultStr = new StringBuilder();

        for (var entry : map.entrySet()) {
            var key = formatValue(entry.getKey());
            var keyDiff = entry.getValue().getFirst().toString();
            var value = entry.getValue();

            switch (keyDiff) {
                case "equal":
                    continue;
                case "removed":
                    resultStr.append("Property "
                            + key
                            + " was removed\n");
                    break;
                case "added":
                    resultStr.append("Property "
                            + key
                            + " was added with value: "
                            + formatValue(value.get(1)) + "\n");
                    break;
                case "updated":
                    resultStr.append("Property "
                            + key + " was updated. From "
                            + formatValue(value.get(1)) + " to "
                            +  formatValue(value.get(2)) + "\n");
                    break;
                default:
                    throw new RuntimeException("Неизвестный тип : " + keyDiff);
            }
        }
        resultStr.deleteCharAt(resultStr.length() - 1);
        return resultStr.toString();

    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }

        var classname = value.getClass().getName();
        if (classname.equals("java.util.ArrayList")
                || classname.equals("java.util.LinkedHashMap")
                || value.getClass().isArray()) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }

}

