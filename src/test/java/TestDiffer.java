package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class TestDiffer {

    @Test
    public void testGenerateJson() throws Exception {
        var strJson1 = "{\n" +
                "  \"setting1\": \"Some value\",\n" +
                "  \"setting2\": 200,\n" +
                "  \"setting3\": true,\n" +
                "  \"key1\": \"value1\",\n" +
                "  \"numbers1\": [1, 2, 3, 4],\n" +
                "  \"numbers2\": [2, 3, 4, 5],\n" +
                "  \"id\": 45,\n" +
                "  \"default\": null,\n" +
                "  \"checked\": false,\n" +
                "  \"numbers3\": [3, 4, 5],\n" +
                "  \"chars1\": [\"a\", \"b\", \"c\"],\n" +
                "  \"chars2\": [\"d\", \"e\", \"f\"]\n" +
                "}";
        var strJson2 = "{\n" +
                "  \"setting1\": \"Another value\",\n" +
                "  \"setting2\": 300,\n" +
                "  \"setting3\": \"none\",\n" +
                "  \"key2\": \"value2\",\n" +
                "  \"numbers1\": [1, 2, 3, 4],\n" +
                "  \"numbers2\": [22, 33, 44, 55],\n" +
                "  \"id\": null,\n" +
                "  \"default\": [\"value1\", \"value2\"],\n" +
                "  \"checked\": true,\n" +
                "  \"numbers4\": [4, 5, 6],\n" +
                "  \"chars1\": [\"a\", \"b\", \"c\"],\n" +
                "  \"chars2\": false,\n" +
                "  \"obj1\": {\n" +
                "    \"nestedKey\": \"value\",\n" +
                "    \"isNested\": true\n" +
                "  }\n" +
                "}";
        var result = Differ.generate(strJson1, strJson2, Parser.JSON_TYPE);
        var expected = "{\n" +
                "    chars1: [a, b, c]\n" +
                "  - chars2: [d, e, f]\n" +
                "  + chars2: false\n" +
                "  - checked: false\n" +
                "  + checked: true\n" +
                "  - default: null\n" +
                "  + default: [value1, value2]\n" +
                "  - id: 45\n" +
                "  + id: null\n" +
                "  - key1: value1\n" +
                "  + key2: value2\n" +
                "    numbers1: [1, 2, 3, 4]\n" +
                "  - numbers2: [2, 3, 4, 5]\n" +
                "  + numbers2: [22, 33, 44, 55]\n" +
                "  - numbers3: [3, 4, 5]\n" +
                "  + numbers4: [4, 5, 6]\n" +
                "  + obj1: {nestedKey=value, isNested=true}\n" +
                "  - setting1: Some value\n" +
                "  + setting1: Another value\n" +
                "  - setting2: 200\n" +
                "  + setting2: 300\n" +
                "  - setting3: true\n" +
                "  + setting3: none\n" +
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
