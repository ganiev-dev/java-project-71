package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Stylish implements Format {

    @Override
    public String formatView(Map<String, List<Object>> map) {
        final int count2 = 2;
        final int count4 = 4;

        StringBuilder resultStr = new StringBuilder("{\n");
        for (var entry : map.entrySet()) {
            var key = entry.getKey();
            var keyDiff = entry.getValue().getFirst().toString();
            var value = entry.getValue();

            switch (keyDiff) {
                case "equal":
                    resultStr.append(" ".repeat(count4) + key + ": " + value.get(1) + "\n");
                    break;
                case "removed":
                    resultStr.append(" ".repeat(count2) + "- " + key  + ": " +  value.get(1) + "\n");
                    break;
                case "added":
                    resultStr.append(" ".repeat(count2) + "+ " + key  + ": " +  value.get(1) + "\n");
                    break;
                case "updated":
                    resultStr.append(" ".repeat(count2) + "- " + key  + ": " +  value.get(1) + "\n");
                    resultStr.append(" ".repeat(count2) + "+ " + key  + ": " +  value.get(2) + "\n");
                    break;
                default:
                    throw new RuntimeException("Неизвестный тип : " + keyDiff);
            }
        }
        resultStr.append("}");
        return resultStr.toString();
    }

}
