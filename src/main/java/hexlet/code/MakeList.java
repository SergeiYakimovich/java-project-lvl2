package hexlet.code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MakeList {

    public static List<Map<String, Object>> makeDif(Map<String, Object> data1, Map<String, Object> data2)
            throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keys = new HashSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        for (String key: keys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            if (!data1.containsKey(key)) {
                putValue(result, key, "+", value2);
            } else {
                if (!data2.containsKey(key)) {
                    putValue(result, key, "-", value1);
                } else {
                    putTwoValues(result, key, value1, value2);
                }
            }
        }
        return result.stream()
                .sorted((item1, item2) -> item1.get("key").toString().compareTo(item2.get("key").toString()))
                .collect(Collectors.toList());
    }

    private static void putValue(List<Map<String, Object>> result, String key, String res, Object value) {
        if (value == null) {
            result.add(Map.of("key", key, "res", res));
        } else {
            result.add(Map.of("key", key, "value", value, "res", res));
        }
    }

    private static void putTwoValues(List<Map<String, Object>> result, String key,
                                    Object value1, Object value2) {
        if (value2 != null && value1 != null) {
            putTwoValuesNoNull(result, key, value1, value2);
        } else {
            putTwoValuesWithNull(result, key, value1, value2);
        }
    }

    private static void putTwoValuesNoNull(List<Map<String, Object>> result, String key,
                                           Object value1, Object value2) {
        if (value2.equals(value1)) {
            result.add(Map.of("key", key, "value", value1, "res", " "));
        } else {
            result.add(Map.of("key", key, "value", value1, "res", ">",
                    "value2", value2));
        }
    }

    private static void putTwoValuesWithNull(List<Map<String, Object>> result, String key,
                                             Object value1, Object value2) {
        if (value2 == null && value1 == null) {
            result.add(Map.of("key", key, "res", " "));
        } else {
            if (value1 == null) {
                result.add(Map.of("key", key, "res", ">", "value2", value2));
            }
            if (value2 == null) {
                result.add(Map.of("key", key, "value", value1, "res", ">"));
            }
        }
    }

}
