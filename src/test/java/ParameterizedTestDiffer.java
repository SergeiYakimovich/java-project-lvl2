package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static java.nio.file.Files.readString;
import static org.assertj.core.api.Assertions.assertThat;
public class ParameterizedTestDiffer {
    @ParameterizedTest
    @CsvSource({
            "src/test/resources/file1_v2.json, src/test/resources/file2_v2.json, " +
                    "src/test/resources/result1.txt, stylish",
            "src/test/resources/file1_v2.json, src/test/resources/file2_v2.json, " +
                    "src/test/resources/result2.txt, plain",
            "src/test/resources/file1_v2.json, src/test/resources/file2_v2.json, " +
                    "src/test/resources/result3.txt, json"
    })
    void testWithCsvSource(String file1, String file2, String resFile, String formatType) throws Exception {
        String result = Differ.generate(file1, file2, formatType);
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

}
