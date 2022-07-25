package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> list, String type) throws Exception {

        // преобразуем список в строку нужного типа
        if (type.equals("plain")) {
            return PlainFormatter.format(list);
        } else if (type.equals("json")) {
            return JsonFormatter.format(list);
        } else {
            return StylishFormatter.format(list);
        }
    }

}
