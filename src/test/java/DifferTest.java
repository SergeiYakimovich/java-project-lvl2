package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.file.Files.readString;
import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    @ParameterizedTest
    @MethodSource
    final void testGenerate(String file1, String file2, String resFile, String formatType)
            throws Exception {
        String result = Differ.generate(file1, file2, formatType);
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> testGenerate() {
        return Stream.of(
                Arguments.of("src/test/resources/file1_v2.json", "src/test/resources/file2_v2.json",
                        "src/test/resources/result_Json_Stylish.txt", "stylish"),
                Arguments.of("src/test/resources/file1_v2.json", "src/test/resources/file2_v2.json",
                        "src/test/resources/result_Json_Plain.txt", "plain"),
                Arguments.of("src/test/resources/file1_v2.json", "src/test/resources/file2_v2.json",
                        "src/test/resources/result_Json_Json.txt", "json"),
                Arguments.of("src/test/resources/file1_v1.yml", "src/test/resources/file2_v1.yml",
                        "src/test/resources/result_Yml_Stylish.txt", "stylish"),
                Arguments.of("src/test/resources/file1_v2.yml", "src/test/resources/file2_v2.yml",
                        "src/test/resources/result_Yml_Plain.txt", "plain"),
                Arguments.of("src/test/resources/file1_v2.yml", "src/test/resources/file2_v2.yml",
                        "src/test/resources/result_Yml_Json.txt", "json")
        );
    }

    @ParameterizedTest
    @MethodSource
    final void testDefaultGenerate(String file1, String file2, String resFile)
            throws Exception {
        String result = Differ.generate(file1, file2);
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> testDefaultGenerate() {
        return Stream.of(
                Arguments.of("src/test/resources/file1_v1.yaml", "src/test/resources/file2_v1.yaml",
                        "src/test/resources/result_Yaml_Default.txt"),
                Arguments.of("src/test/resources/file1_v2.json", "src/test/resources/file2_v2.json",
                        "src/test/resources/result_Json_Stylish.txt")
        );
    }

    @Test
    public void testGenerateWrongFormatType() throws Exception {
        String file1 = "src/test/resources/file1_v2.json";
        String file2 = "src/test/resources/file2_v2.json";
        String result = Differ.generate(file1, file2, "fjhfdff");
        String resFile = "src/test/resources/result_Json_Stylish.txt";
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGenerateDifferentFileTypes() throws Exception {
        String file1 = "src/test/resources/file1_v2.json";
        String file2 = "src/test/resources/file2_v2.yml";
        String result = Differ.generate(file1, file2, "plain");
        String resFile = "src/test/resources/result_Json_Plain.txt";
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testParserGetMapWrongFileExt() throws Exception {
        String text = "fhkjglgulgjh";
        Map<String, Object> result = Parser.getMap(text, "doc");
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void testFormatterEmptyList() throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        String result = Formatter.format(list, "json");
        assertThat(result).isEqualTo("");
    }

}
