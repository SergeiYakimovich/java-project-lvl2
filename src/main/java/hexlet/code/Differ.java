package hexlet.code;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;

import static java.nio.file.Files.readString;

public class Differ {

    public static String generate(String file1, String file2, String formatType) throws Exception {
        Map<String, Object> data1 = getData(file1);
        Map<String, Object> data2 = getData(file2);
        List<Map<String, Object>> result = MakeList.makeDif(data1, data2);
        return Formatter.format(result, formatType);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static Map<String, Object> getData(String file) throws Exception {
        String text = readString(Paths.get(file));
        String fileExt = FilenameUtils.getExtension(file);
        return Parser.getMap(text, fileExt);
    }

}
