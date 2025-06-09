package hexlet.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.LinkedHashMap;

public class CalculatorOfDiff {
    public static Map<String, List<Object>>
        calculateDiff(Map<String, Object> data1, Map<String, Object> data2) throws Exception {
        Set<String> keysSet = Stream.concat(data1.keySet().stream(), data2.keySet().stream())
                .collect(Collectors.toSet());
        var diffmap = new HashMap<String, ArrayList<Object>>();

        for (String k : keysSet) {
            Object value1 = data1.get(k);
            Object value2 = data2.get(k);

            if (!data1.containsKey(k) && data2.containsKey(k)) {
                //ADDED
                diffmap.put(k, new ArrayList<>(Arrays.asList("added", value2)));
            } else if (data1.containsKey(k) && !data2.containsKey(k)) {
                //REMOVED
                diffmap.put(k, new ArrayList<>(Arrays.asList("removed", value1)));
            } else if (Objects.equals(value1, value2)) {
                //UNCHANGED
                diffmap.put(k, new ArrayList<>(Arrays.asList("equal", value1)));
            } else {
                //UPDATED
                diffmap.put(k, new ArrayList<>(Arrays.asList("updated", value1, value2)));
            }
        }

        return diffmap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) //Сортируем по ключу
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

    }
}
