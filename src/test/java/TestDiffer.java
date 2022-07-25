package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class TestDiffer {

    @Test
    public void testGenerateJsonFileToStylish() throws Exception {
        String file1 = "src/test/resources/file1_v2.json";
        String file2 = "src/test/resources/file2_v2.json";
        String result = Differ.generate(file1, file2, "stylish");
        String expected = "{\n" + "    chars1: [a, b, c]\n" + "  - chars2: [d, e, f]\n" + "  + chars2: false\n"
                + "  - checked: false\n" + "  + checked: true\n" + "  - default: null\n"
                + "  + default: [value1, value2]\n" + "  - id: 45\n" + "  + id: null\n" + "  - key1: value1\n"
                + "  + key2: value2\n" + "    numbers1: [1, 2, 3, 4]\n" + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n" + "  - numbers3: [3, 4, 5]\n" + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n" + "  - setting1: Some value\n"
                + "  + setting1: Another value\n" + "  - setting2: 200\n" + "  + setting2: 300\n"
                + "  - setting3: true\n" + "  + setting3: none\n" + "}";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGenerateJsonFileToPlain() throws Exception {
        String file1 = "src/test/resources/file1_v2.json";
        String file2 = "src/test/resources/file2_v2.json";
        var result = Differ.generate(file1, file2, "plain");
        var expected = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGenerateJsonFileToJson() throws Exception {
        String file1 = "src/test/resources/file1_v2.json";
        String file2 = "src/test/resources/file2_v2.json";
        var result = Differ.generate(file1, file2, "json");
        var expected = "[{\"key\":\"chars1\",\"res\":\" \",\"value\":[\"a\",\"b\",\"c\"]},"
                + "{\"key\":\"chars2\",\"res\":\"-+\",\"value\":[\"d\",\"e\",\"f\"],\"value2\":false},"
                + "{\"key\":\"checked\",\"res\":\"-+\",\"value\":false,\"value2\":true},{\"key\":\"default\","
                + "\"res\":\"-+\",\"value2\":[\"value1\",\"value2\"]},{\"key\":\"id\",\"res\":\"-+\",\"value\""
                + ":45},{\"key\":\"key1\",\"res\":\"-\",\"value\":\"value1\"},{\"key\":\"key2\",\"res\":\"+\","
                + "\"value\":\"value2\"},{\"key\":\"numbers1\",\"res\":\" \",\"value\":[1,2,3,4]},{\"key\":\""
                + "numbers2\",\"res\":\"-+\",\"value\":[2,3,4,5],\"value2\":[22,33,44,55]},{\"key\":\"numbers3\","
                + "\"res\":\"-\",\"value\":[3,4,5]},{\"key\":\"numbers4\",\"res\":\"+\",\"value\":[4,5,6]},"
                + "{\"key\":\"obj1\",\"res\":\"+\",\"value\":{\"isNested\":true,\"nestedKey\":\"value\"}},"
                + "{\"key\":\"setting1\",\"res\":\"-+\",\"value\":\"Some value\",\"value2\":\"Another value\"},"
                + "{\"key\":\"setting2\",\"res\":\"-+\",\"value\":200,\"value2\":300},{\"key\":\"setting3\","
                + "\"res\":\"-+\",\"value\":true,\"value2\":\"none\"}]";
        assertThat(result).isEqualTo(expected);
    }
    @Test
    public void testGenerateYmlFileToStylish() throws Exception {
        String file1 = "src/test/resources/file1_v1.yml";
        String file2 = "src/test/resources/file2_v1.yml";
        var result = Differ.generate(file1, file2, "stylish");
        var expected = "{\n" + "  - follow: false\n" + "    host: hexlet.io\n" + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n" + "  + timeout: 20\n" + "  + verbose: true\n" + "}";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGenerateYamlFileToDefault() throws Exception {
        String file1 = "src/test/resources/file1_v1.yaml";
        String file2 = "src/test/resources/file2_v1.yaml";
        var result = Differ.generate(file1, file2);
        var expected = "{\n" + "  - follow: false\n" + "    host: hexlet.io\n" + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n" + "  + timeout: 20\n" + "  + verbose: true\n" + "}";
        assertThat(result).isEqualTo(expected);
    }

}
