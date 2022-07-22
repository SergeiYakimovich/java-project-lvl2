package hexlet.code;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
public class Differ {
    public static String generate(Path filepath1, Path filepath2, App.Format type) throws Exception {

        // считаваем данные из файлов и преобразуем в мапы
        Map<String, Object> data1 = Parser.getMap(filepath1);
        Map<String, Object> data2 = Parser.getMap(filepath2);

        // сравниваем и заносим результаты в список
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

        // сортируем
        result = result.stream()
                .sorted((item1, item2) -> item1.get("key").toString().compareTo(item2.get("key").toString()))
                .collect(Collectors.toList());

        // преобразуем в строку нужного формата
        return Formatter.format(result, type);
    }

    public static String generate(Path filepath1, Path filepath2) throws Exception { // метод без указания формата
        return generate(filepath1, filepath2, App.Format.stylish);
    }

}
