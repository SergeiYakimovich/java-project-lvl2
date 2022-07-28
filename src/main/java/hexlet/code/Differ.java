package hexlet.code;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;

import static java.nio.file.Files.readString;

public class Differ {

    public static String generate(String file1, String file2, String formatType) throws Exception {
        Map<String, Object> data1 = getData(file1);
        Map<String, Object> data2 = getData(file2);
        List<Map<String, Object>> result = makeDif(data1, data2);
        return Formatter.format(result, formatType);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static List<Map<String, Object>> makeDif(Map<String, Object> data1, Map<String, Object> data2)
            throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keys = new HashSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        for (String key: keys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            if (!data1.containsKey(key)) {
                MakeList.putValue(result, key, "+", value2);
            } else {
                if (!data2.containsKey(key)) {
                    MakeList.putValue(result, key, "-", value1);
                } else {
                    MakeList.putTwoValues(result, key, value1, value2);
                }
            }
        }
        return result.stream()
                .sorted((item1, item2) -> item1.get("key").toString().compareTo(item2.get("key").toString()))
                .collect(Collectors.toList());
    }

    private static Map<String, Object> getData(String file) throws Exception {
        String text = readString(Paths.get(file));
        String fileExt = FilenameUtils.getExtension(file);
        return Parser.getMap(text, fileExt);
    }

}
