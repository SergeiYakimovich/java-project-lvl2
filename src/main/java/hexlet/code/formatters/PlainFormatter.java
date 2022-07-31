package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String format(List<Map<String, Object>> list) {
        StringBuilder resultStr = new StringBuilder();
        list.stream()
                .forEach(item -> {
                    switch ((String) item.get("res")) {
                        case ">" :
                            resultStr.append("Property '");
                            resultStr.append(item.get("key"));
                            resultStr.append("' was updated. From ");
                            resultStr.append(modifyValue(item.get("value")));
                            resultStr.append(" to ");
                            resultStr.append(modifyValue(item.get("value2")));
                            resultStr.append("\n");
                            break;
                        case "+" :
                            resultStr.append("Property '");
                            resultStr.append(item.get("key"));
                            resultStr.append("' was added with value: ");
                            resultStr.append(modifyValue(item.get("value")));
                            resultStr.append("\n");
                            break;
                        case "-" :
                            resultStr.append("Property '");
                            resultStr.append(item.get("key"));
                            resultStr.append("' was removed\n");
                            break;
                        default:
                    }
                });
        return resultStr.toString().substring(0, resultStr.length() - 1);
    }

    private static String modifyValue(Object o) {
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
