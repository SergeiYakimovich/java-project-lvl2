package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> getMap(String text, String fileExt) throws Exception {
        switch (fileExt) {
            case "json":
                ObjectMapper mapper1 = new ObjectMapper();
                return mapper1.readValue(text, new TypeReference<Map<String, Object>>() { });
            case "yml":
            case "yaml":
                YAMLMapper mapper2 = new YAMLMapper();
                return mapper2.readValue(text, new TypeReference<Map<String, Object>>() { });
            default:
                System.out.println("Wrong file type : " + fileExt);
                return new HashMap<>();
        }
    }

}
