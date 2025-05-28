package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Map;

public class Stylish implements Format {

    @Override
    public String formatView(Map<String, ArrayList<Object>> map) {
        StringBuilder resultStr = new StringBuilder("{\n");
        for (var entry : map.entrySet()) {
            var key = entry.getKey();
            var keyDiff = entry.getValue().getFirst().toString();
            var value = entry.getValue();

            switch (keyDiff) {
                case "equal":
                    resultStr.append(" ".repeat(4) + key + ": " + value.get(1) + "\n");
                    break;
                case "removed":
                    resultStr.append(" ".repeat(2) + "- " + key  + ": " +  value.get(1) + "\n");
                    break;
                case "added":
                    resultStr.append(" ".repeat(2) + "+ " + key  + ": " +  value.get(1) + "\n");
                    break;
                case "updated":
                    resultStr.append(" ".repeat(2) + "- " + key  + ": " +  value.get(1) + "\n");
                    resultStr.append(" ".repeat(2) + "+ " + key  + ": " +  value.get(2) + "\n");
                    break;
                default:
                    throw new RuntimeException("Неизвестный тип : " + keyDiff);
            }
        }
        resultStr.append("}");
        return resultStr.toString();
    }

}
