package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static java.nio.file.Files.readString;
import static org.assertj.core.api.Assertions.assertThat;
public class TestDiffer {

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

    @Test
    public void testGenerateJsonFileToStylish() throws Exception {
        String file1 = "src/test/resources/file1_v2.json";
        String file2 = "src/test/resources/file2_v2.json";
        String result = Differ.generate(file1, file2, "stylish");
        String resFile = "src/test/resources/result_J_S.txt";
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGenerateJsonFileToPlain() throws Exception {
        String file1 = "src/test/resources/file1_v2.json";
        String file2 = "src/test/resources/file2_v2.json";
        String result = Differ.generate(file1, file2, "plain");
        String resFile = "src/test/resources/result_J_P.txt";
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGenerateJsonFileToJson() throws Exception {
        String file1 = "src/test/resources/file1_v2.json";
        String file2 = "src/test/resources/file2_v2.json";
        String result = Differ.generate(file1, file2, "json");
        String resFile = "src/test/resources/result_J_J.txt";
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }
    @Test
    public void testGenerateYmlFileToStylish() throws Exception {
        String file1 = "src/test/resources/file1_v1.yml";
        String file2 = "src/test/resources/file2_v1.yml";
        String result = Differ.generate(file1, file2, "stylish");
        String resFile = "src/test/resources/result_Y_S.txt";
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGenerateYmlFileToPlain() throws Exception {
        String file1 = "src/test/resources/file1_v2.yml";
        String file2 = "src/test/resources/file2_v2.yml";
        String result = Differ.generate(file1, file2, "plain");
        String resFile = "src/test/resources/result_Y_P.txt";
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGenerateYmlFileToJson() throws Exception {
        String file1 = "src/test/resources/file1_v2.yml";
        String file2 = "src/test/resources/file2_v2.yml";
        String result = Differ.generate(file1, file2, "json");
        String resFile = "src/test/resources/result_Y_J.txt";
        String expected = readString(Paths.get(resFile), StandardCharsets.US_ASCII);
        expected = expected.substring(0, expected.length() - 1);
        assertThat(result).isEqualTo(expected);
    }

}
