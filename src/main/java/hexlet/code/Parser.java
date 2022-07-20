package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static final int JSON_TYPE = 1;
    public static final int YAML_TYPE = 2;

    public static Map<String, Object> getMap(String str, int type) throws Exception {

        switch (type) {
            case JSON_TYPE:
                ObjectMapper mapper1 = new ObjectMapper();
                return mapper1.readValue(str, new TypeReference<Map<String, Object>>() { });
            case YAML_TYPE:
                YAMLMapper mapper2 = new YAMLMapper();
                return mapper2.readValue(str, new TypeReference<Map<String, Object>>() { });
            default:
        }
        return null;
    }

}
