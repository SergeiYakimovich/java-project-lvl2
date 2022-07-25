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

    public static final int JSON_TYPE = 1;
    public static final int YML_TYPE = 2;
    public static String generate(String file1, String file2, String formatType) throws Exception {

        // считаваем данные из файлов и преобразуем в мапы
        String text = readString(Paths.get(file1));
        int fileType = getFileType(file1);
        Map<String, Object> data1 = Parser.getMap(text, fileType);

        text = readString(Paths.get(file2));
        fileType = getFileType(file2);
        Map<String, Object> data2 = Parser.getMap(text, fileType);

        // сравниваем мапы и заносим результаты в список (ключ, значение, результат сравнения, значение2 при замене)
        List<Map<String, Object>> result = makeDif(data1, data2);

        // преобразуем в строку нужного формата
        return Formatter.format(result, formatType);
    }


    public static List<Map<String, Object>> makeDif(Map<String, Object> data1, Map<String, Object> data2)
            throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> current = new HashMap<>();

        Set<String> keys = new HashSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        for (String key: keys) {
            if (!data1.containsKey(key)) {
                current = new HashMap<>();
                current.put("key", key);
                current.put("value", data2.get(key));
                current.put("res", "+");
                result.add(current);
            } else {
                if (!data2.containsKey(key)) {
                    current = new HashMap<>();
                    current.put("key", key);
                    current.put("value", data1.get(key));
                    current.put("res", "-");
                    result.add(current);
                    //result.add(Map.of("key", key, "value", data1.get(key), "res", "-"));
                } else {
                    if ((data2.get(key) != null) && (data1.get(key) != null)) {
                        if (data2.get(key).equals(data1.get(key))) {
                            current = new HashMap<>();
                            current.put("key", key);
                            current.put("value", data1.get(key));
                            current.put("res", " ");
                            result.add(current);
                            //result.add(Map.of("key", key, "value", data1.get(key), "res", " "));
                        } else {
                            current = new HashMap<>();
                            current.put("key", key);
                            current.put("value", data1.get(key));
                            current.put("res", "-+");
                            current.put("value2", data2.get(key));
                            result.add(current);
                            //result.add(Map.of("key", key, "value", data1.get(key), "res", "-+", "value2",
                            // data2.get(key)));
                        }
                    } else {
                        if ((data2.get(key) == null) && (data1.get(key) == null)) {
                            current = new HashMap<>();
                            current.put("key", key);
                            current.put("value", data1.get(key));
                            current.put("res", " ");
                            result.add(current);
                            //result.add(Map.of("key", key, "value", data1.get(key), "res", " "));
                        } else {
                            current = new HashMap<>();
                            current.put("key", key);
                            current.put("value", data1.get(key));
                            current.put("res", "-+");
                            current.put("value2", data2.get(key));
                            result.add(current);
                            //result.add(Map.of("key", key, "value", data1.get(key), "res", "-+",
                            // "value2", data2.get(key)));
                        }
                    }
                }
            }
        }

        // сортируем и возвращаем список
        return result.stream()
                .sorted((item1, item2) -> item1.get("key").toString().compareTo(item2.get("key").toString()))
                .collect(Collectors.toList());
    }

    public static int getFileType(String fileName) {

        String fileExt = FilenameUtils.getExtension(fileName);
        if (fileExt.equals("json"))  {
            return JSON_TYPE;
        } else {
            if (fileExt.equals("yml") || fileExt.equals("yaml")) {
                return YML_TYPE;
            }
        }
        return 0;
    }

    // метод без указания типа форматирования
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

}
