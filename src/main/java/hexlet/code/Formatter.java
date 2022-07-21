package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Formatter {
     public static String format(List<Map<String, Object>> list, App.Format type) {
        if (type == App.Format.plain) {
            return plain(list);
        } else {
            return stylish(list);
        }
    }
    public static String stylish (List<Map<String, Object>> list) {
        String resultStr = "{\n";
        for (Map<String, Object> item : list) {
            resultStr = resultStr + "  " + item.get("res") + " " + item.get("key") + ": "
                    + item.get("value").toString() + "\n";
        }
        return resultStr + "}";
    }
    public static String plain (List<Map<String, Object>> list) {
        String resultStr = "";
        int i = 0;
        Map<String, Object> item = new HashMap<>();
        while (i < list.size()) {
            item = list.get(i);
            if ((i < list.size() - 1) && (list.get(i+1).get("key").equals(item.get("key")))) {
                resultStr = resultStr + "Property '" + item.get("key") + "' was updated. From "
                        + modifyValue(item.get("value")) + " to " + modifyValue(list.get(i+1).get("value")) + "\n";
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
        return resultStr;
    }
    public static String modifyValue (Object o) {
        if((o instanceof Integer) ||  (o instanceof String) || (o instanceof Boolean)) {
            return o.toString();
        } else {
            return "[complex value]";
        }
    }

}
