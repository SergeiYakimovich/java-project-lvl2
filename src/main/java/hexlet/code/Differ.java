package hexlet.code;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String str1, String str2, int type) throws Exception {

        Map<String, Object> data1 = Parser.getMap(str1, type);
        Map<String, Object> data2 = Parser.getMap(str2, type);

        List<Map<String, Object>> result = new ArrayList<>();

        Set<String> keys = new HashSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key: keys) {
            if (!data1.containsKey(key)) {
                result.add(Map.of("key", key, "value", data2.get(key), "res", "+"));
            } else {
                if (!data2.containsKey(key)) {
                    result.add(Map.of("key", key, "value", data1.get(key), "res", "-"));
                } else {
                    if (data2.get(key).equals(data1.get(key))) {
                        result.add(Map.of("key", key, "value", data1.get(key), "res", " "));
                    } else {
                        result.add(Map.of("key", key, "value", data1.get(key), "res", "-"));
                        result.add(Map.of("key", key, "value", data2.get(key), "res", "+"));
                    }
                }
            }
        }
        result = result.stream()
                .sorted((item1, item2) -> item1.get("key").toString().compareTo(item2.get("key").toString()))
                .collect(Collectors.toList());

        String resultStr = "{\n";
        for (Map<String, Object> item : result) {
            resultStr = resultStr + "  " + item.get("res") + " " + item.get("key") + ": " + item.get("value") + "\n";
        }

        return resultStr + "}";
    }

}
