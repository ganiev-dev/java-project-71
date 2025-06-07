package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Plain implements Format {
    @Override
    public String formatView(Map<String, List<Object>> map) {

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
                            + formatValue(value.get(2)) + "\n");
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
        } else if (value.getClass().getName().equals("java.util.ArrayList")
                || value.getClass().getName().equals("java.util.LinkedHashMap")
                || value.getClass().isArray()) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }
}

