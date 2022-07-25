package hexlet.code.formatters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String format(List<Map<String, Object>> list) {
        String resultStr = "";
        Map<String, Object> item = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            item = list.get(i);
            if (item.get("res").equals("-+")) {
                resultStr = resultStr + "Property '" + item.get("key") + "' was updated. From "
                        + modifyValue(item.get("value")) + " to " + modifyValue(item.get("value2")) + "\n";
            } else {
                if (item.get("res").equals("+")) {
                    resultStr = resultStr + "Property '" + item.get("key") + "' was added with value: "
                            + modifyValue(item.get("value")) + "\n";
                } else if (item.get("res").equals("-")) {
                    resultStr = resultStr + "Property '" + item.get("key") + "' was removed" + "\n";
                }
            }
        }
        return resultStr.substring(0, resultStr.length() - 1);
    }
    public static String modifyValue(Object o) {
        if (o == null) {
            return "null";
        }
        if ((o instanceof Integer) || (o instanceof Boolean)) {
            return o.toString();
        }
        if (o instanceof String) {
            return "'" + o + "'";
        } else {
            return "[complex value]";
        }
    }
}
