package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class TestDiffer {

    @Test
    public void testGenerateJson() throws Exception {
        var strJson1 = "{\n" +
                "  \"host\": \"hexlet.io\",\n" +
                "  \"timeout\": 50,\n" +
                "  \"proxy\": \"123.234.53.22\",\n" +
                "  \"follow\": false\n" +
                "}";
        var strJson2 = "{\n" +
                "  \"timeout\": 20,\n" +
                "  \"verbose\": true,\n" +
                "  \"host\": \"hexlet.io\"\n" +
                "}";
        var result = Differ.generate(strJson1, strJson2, Parser.JSON_TYPE);
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

    @Test
    public void testGenerateYaml() throws Exception {
        var strYaml1 = "\"host\": \"hexlet.io\"\n" +
                "\"timeout\": 50\n" +
                "\"proxy\": \"123.234.53.22\"\n" +
                "\"follow\": false";
        var strYaml2 = "\"timeout\": 20\n" +
                "\"verbose\": true\n" +
                "\"host\": \"hexlet.io\"";
        var result = Differ.generate(strYaml1, strYaml2, Parser.YAML_TYPE);
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
