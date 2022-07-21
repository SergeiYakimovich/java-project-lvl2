package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static final int JSON_TYPE = 1;
    public static final int YAML_TYPE = 2;
    public static final int STYLISH_TYPE = 3;

    public static Map<String, Object> getMap(String str, int type) throws Exception {

        Map<String, Object> result = new HashMap<>();
        switch (type) {
            case JSON_TYPE:
                ObjectMapper mapper1 = new ObjectMapper();
                result = mapper1.readValue(str, new TypeReference<Map<String, Object>>() { });
                break;
            case YAML_TYPE:
                YAMLMapper mapper2 = new YAMLMapper();
                result = mapper2.readValue(str, new TypeReference<Map<String, Object>>() { });
                break;
            default:
        }
        for (Map.Entry<String, Object> item: result.entrySet()) {
            if (item.getValue() == null) {
                item.setValue("null");
            }
        }
        return result;
    }

}
