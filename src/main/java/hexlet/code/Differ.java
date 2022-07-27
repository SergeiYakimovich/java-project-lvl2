package hexlet.code;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;

import static java.nio.file.Files.readString;

public class Differ {

    public static String generate(String file1, String file2, String formatType) throws Exception {
        String text = readString(Paths.get(file1));
        String fileExt = FilenameUtils.getExtension(file1);
        Map<String, Object> data1 = Parser.getMap(text, fileExt);
        text = readString(Paths.get(file2));
        fileExt = FilenameUtils.getExtension(file2);
        Map<String, Object> data2 = Parser.getMap(text, fileExt);

        List<Map<String, Object>> result = makeDif(data1, data2);

        return Formatter.format(result, formatType);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
    private static List<Map<String, Object>> makeDif(Map<String, Object> data1, Map<String, Object> data2)
            throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> current = new HashMap<>();
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
                    if (value2 != null && value1 != null) {
                        putTwoValuesNoNull(result, key, value1, value2);
                    } else {
                        putTwoValuesWithNull(result, key, value1, value2);
                    }
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
