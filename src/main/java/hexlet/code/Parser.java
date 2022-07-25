package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> getMap(String text, int fileType) throws Exception {

        // преобразуем в мапу в зависимости от типа файла
        Map<String, Object> result = new HashMap<>();

        switch (fileType) {
            case Differ.JSON_TYPE:
                ObjectMapper mapper1 = new ObjectMapper();
                result = mapper1.readValue(text, new TypeReference<Map<String, Object>>() { });
                break;
            case Differ.YML_TYPE:
                YAMLMapper mapper2 = new YAMLMapper();
                result = mapper2.readValue(text, new TypeReference<Map<String, Object>>() { });
                break;
            default:
        }

        // заменяем null строкой для последующих сравнений
        /*for (Map.Entry<String, Object> item: result.entrySet()) {
            if (item.getValue() == null) {
                item.setValue("null");
            }
        }*/

        return result;
    }

}
