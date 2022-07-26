package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> getMap(String text, String fileExt) throws Exception {

        // преобразуем в мапу в зависимости от типа файла
        if (fileExt.equals("json"))  {
            ObjectMapper mapper1 = new ObjectMapper();
            return mapper1.readValue(text, new TypeReference<Map<String, Object>>() { });
        } else {
            if (fileExt.equals("yml") || fileExt.equals("yaml")) {
                YAMLMapper mapper2 = new YAMLMapper();
                return mapper2.readValue(text, new TypeReference<Map<String, Object>>() { });
            }
        }
        System.out.println("Неверный тип файла " + fileExt);
        return new HashMap<>();
    }

}
