package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {

    public static String generate(String str1, String str2) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1
                = mapper.readValue(str1, new TypeReference<Map<String,Object>>(){});
        Map<String, Object> map2
                = mapper.readValue(str2, new TypeReference<Map<String,Object>>(){});

        return "123";
    }

}
