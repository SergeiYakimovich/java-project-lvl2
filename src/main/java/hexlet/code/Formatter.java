package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> list, String type) throws Exception {
        switch (type) {
            case "plain":
                return PlainFormatter.format(list);
            case "json":
                return JsonFormatter.format(list);
            default:
                return StylishFormatter.format(list);
        }
    }

}
