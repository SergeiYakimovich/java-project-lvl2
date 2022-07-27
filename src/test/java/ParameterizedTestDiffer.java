package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.nio.file.Files.readString;
import static org.assertj.core.api.Assertions.assertThat;

public class ParameterizedTestDiffer {

    @ParameterizedTest
    @MethodSource
    final void testWithMethodSource(String file1, String file2, String resFile, String formatType)
            throws Exception {
        String result = Differ.generate(file1, file2, formatType);
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> testWithMethodSource() {
        return Stream.of(
                Arguments.of("src/test/resources/file1_v2.json", "src/test/resources/file2_v2.json",
                        "src/test/resources/result_J_S.txt", "stylish"),
                Arguments.of("src/test/resources/file1_v2.json", "src/test/resources/file2_v2.json",
                        "src/test/resources/result_J_P.txt", "plain"),
                Arguments.of("src/test/resources/file1_v2.json", "src/test/resources/file2_v2.json",
                        "src/test/resources/result_J_J.txt", "json"),
                Arguments.of("src/test/resources/file1_v1.yml", "src/test/resources/file2_v1.yml",
                        "src/test/resources/result_Y_S.txt", "stylish"),
                Arguments.of("src/test/resources/file1_v2.yml", "src/test/resources/file2_v2.yml",
                        "src/test/resources/result_Y_P.txt", "plain"),
                Arguments.of("src/test/resources/file1_v2.yml", "src/test/resources/file2_v2.yml",
                        "src/test/resources/result_Y_J.txt", "json")
        );
    }

    @Test
    public void testGenerateYamlFileToDefault() throws Exception {
        String file1 = "src/test/resources/file1_v1.yaml";
        String file2 = "src/test/resources/file2_v1.yaml";
        String result = Differ.generate(file1, file2);
        String resFile = "src/test/resources/result_Ya_D.txt";
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

}
