package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class TestDiffer {

    @Test
    public void testGenerate() throws Exception {
        var str1 = "{\n" +
                "  \"host\": \"hexlet.io\",\n" +
                "  \"timeout\": 50,\n" +
                "  \"proxy\": \"123.234.53.22\",\n" +
                "  \"follow\": false\n" +
                "}";
        var str2 = "{\n" +
                "  \"timeout\": 20,\n" +
                "  \"verbose\": true,\n" +
                "  \"host\": \"hexlet.io\"\n" +
                "}";
        var result = Differ.generate(str1, str2);
        var expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";
        assertThat(result).isEqualTo(expected);
    }


}
