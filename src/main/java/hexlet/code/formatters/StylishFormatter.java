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
                        resultStr.append("  - ");
                        resultStr.append(item.get("key"));
                        resultStr.append(": ");
                        resultStr.append(modifyValue(item.get("value")));
                        resultStr.append("\n  + ");
                        resultStr.append(item.get("key"));
                        resultStr.append(": ");
                        resultStr.append(modifyValue(item.get("value2")));
                        resultStr.append("\n");
                    } else {
                        resultStr.append("  ");
                        resultStr.append(item.get("res"));
                        resultStr.append(" ");
                        resultStr.append(item.get("key"));
                        resultStr.append(": ");
                        resultStr.append(modifyValue(item.get("value")));
                        resultStr.append("\n");
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
