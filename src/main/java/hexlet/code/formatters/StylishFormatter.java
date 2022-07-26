package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {

    public static String format(List<Map<String, Object>> list) {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("{\n");
        list.stream()
                .forEach(item -> {
                    if (item.get("res").equals(">")) {
                        resultStr.append("  " + "-" + " " + item.get("key") + ": "
                                + modifyValue(item.get("value")) + "\n");
                        resultStr.append("  " + "+" + " " + item.get("key") + ": "
                                + modifyValue(item.get("value2")) + "\n");
                    } else {
                        resultStr.append("  " + item.get("res") + " " + item.get("key") + ": "
                                + modifyValue(item.get("value")) + "\n");
                    }
                });
        return resultStr.append("}").toString();
    }

    private static String modifyValue(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString();
    }

}
