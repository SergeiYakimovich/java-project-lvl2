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
                            resultStr.append("Property '" + item.get("key") + "' was updated. From "
                                    + modifyValue(item.get("value")) + " to " + modifyValue(item.get("value2")) + "\n");
                            break;
                        case "+" :
                            resultStr.append("Property '" + item.get("key") + "' was added with value: "
                                    + modifyValue(item.get("value")) + "\n");
                            break;
                        case "-" :
                            resultStr.append("Property '" + item.get("key") + "' was removed" + "\n");
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
