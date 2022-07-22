package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> list, App.Format type) throws Exception {
        // преобразуем список в строку нужного типа
        if (type == App.Format.plain) {
            return plain(list);
        } else if (type == App.Format.json) {
            return json(list);
        } else {
            return stylish(list);
        }
    }

    public static String json(List<Map<String, Object>> list) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        return mapper.writeValueAsString(list);
    }
    public static String stylish(List<Map<String, Object>> list) {
        String resultStr = "{\n";
        for (Map<String, Object> item : list) {
            resultStr = resultStr + "  " + item.get("res") + " " + item.get("key") + ": "
                    + item.get("value").toString() + "\n";
        }
        return resultStr + "}";
    }
    public static String plain(List<Map<String, Object>> list) {
        String resultStr = "";
        int i = 0;
        Map<String, Object> item = new HashMap<>();
        while (i < list.size()) {
            item = list.get(i);
            if ((i < list.size() - 1) && (list.get(i + 1).get("key").equals(item.get("key")))) {
                resultStr = resultStr + "Property '" + item.get("key") + "' was updated. From "
                        + modifyValue(item.get("value")) + " to " + modifyValue(list.get(i + 1).get("value")) + "\n";
                i += 2;
            } else {
                if (item.get("res").equals("+")) {
                    resultStr = resultStr + "Property '" + item.get("key") + "' was added with value: "
                            + modifyValue(item.get("value")) + "\n";
                } else if (item.get("res").equals("-")) {
                    resultStr = resultStr + "Property '" + item.get("key") + "' was removed" + "\n";
                }
                i++;
            }
        }
        return resultStr.substring(0, resultStr.length() - 1);
    }
    public static String modifyValue(Object o) {
        if (o.equals("null") || (o instanceof Integer) || (o instanceof Boolean)) {
            return o.toString();
        }
        if (o instanceof String) {
            return "'" + o + "'";
        } else {
            return "[complex value]";
        }
    }

}
