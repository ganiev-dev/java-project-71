package hexlet.code;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Diffs {
    public static LinkedHashMap<String, Object> genDifferents(String path1, String path2) throws Exception {
        var file1 = Parse.getData(path1);
        var file2 = Parse.getData(path2);
        var diffmap = new HashMap<String, Object>();

        for (var entry: file1.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            if (!file2.containsKey(key)) { // Если ключа из 1 нет в 2
                diffmap.put("- " + key, value);
            } else if (value.equals(file2.get(key))) { // иначе Если значение из 1 равно значению из 2
                diffmap.put("  " + key, value);
            } else { // Иначе если значение из 1 не равно значению из 2
                diffmap.put("- " + key, value);
                diffmap.put("+ " + key, file2.get(key));
            }
        }

        for (var entry: file2.entrySet()) { // Если ключ из 2 не содержится в 1
            var key = entry.getKey();
            var value = entry.getValue();
            if (!file1.containsKey(key)) {
                diffmap.put("+ " + key, value);
            }
        }

        return diffmap.entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().charAt(2))) // 3ий символ ключа
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

    }
    public static void toPrint(LinkedHashMap<String, Object> map) {
        for (var entry: map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

