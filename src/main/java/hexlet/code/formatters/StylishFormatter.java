package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {

    public static String format(List<Map<String, Object>> list) {
        String resultStr = "{\n";
        for (Map<String, Object> item : list) {
            if (item.get("res").equals("-+")) {
                resultStr = resultStr + "  " + "-" + " " + item.get("key") + ": "
                        + modifyValue(item.get("value")) + "\n";
                resultStr = resultStr + "  " + "+" + " " + item.get("key") + ": "
                        + modifyValue(item.get("value2")) + "\n";
            } else {
                resultStr = resultStr + "  " + item.get("res") + " " + item.get("key") + ": "
                        + modifyValue(item.get("value")) + "\n";
            }
        }
        return resultStr + "}";
    }

    public static String modifyValue(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString();
    }

}
