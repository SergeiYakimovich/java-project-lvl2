package hexlet.code;

import java.util.List;
import java.util.Map;

public class MakeList {

    public static void putValue(List<Map<String, Object>> result, String key, String res, Object value) {
        if (value == null) {
            result.add(Map.of("key", key, "res", res));
        } else {
            result.add(Map.of("key", key, "value", value, "res", res));
        }
    }

    public static void putTwoValues(List<Map<String, Object>> result, String key,
                                    Object value1, Object value2) {
        if (value2 != null && value1 != null) {
            putTwoValuesNoNull(result, key, value1, value2);
        } else {
            putTwoValuesWithNull(result, key, value1, value2);
        }
    }

    public static void putTwoValuesNoNull(List<Map<String, Object>> result, String key,
                                           Object value1, Object value2) {
        if (value2.equals(value1)) {
            result.add(Map.of("key", key, "value", value1, "res", " "));
        } else {
            result.add(Map.of("key", key, "value", value1, "res", ">",
                    "value2", value2));
        }
    }

    public static void putTwoValuesWithNull(List<Map<String, Object>> result, String key,
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
