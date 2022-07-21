package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Files.readString;

public class Parser {
//    public static final int JSON_TYPE = 1;
//    public static final int YAML_TYPE = 2;
    public static Map<String, Object> getMap(Path filepath) throws Exception {

        String str = readString(filepath);
        System.out.println("file " + filepath.toString());
        System.out.println(str);

        Map<String, Object> result = new HashMap<>();
        if (filepath.toString().endsWith(".json"))  {
            ObjectMapper mapper1 = new ObjectMapper();
            result = mapper1.readValue(str, new TypeReference<Map<String, Object>>() { });
        } else {
            if (filepath.toString().endsWith(".yml") || filepath.toString().endsWith(".yaml")) {
                YAMLMapper mapper2 = new YAMLMapper();
                result = mapper2.readValue(str, new TypeReference<Map<String, Object>>() { });
            }
        }

        for (Map.Entry<String, Object> item: result.entrySet()) {
            if (item.getValue() == null) {
                item.setValue("null");
            }
        }
        return result;
    }

}
