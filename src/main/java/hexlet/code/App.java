package hexlet.code;

import picocli.CommandLine;
import java.io.File;
import java.nio.file.Path;
import java.text.Format;
import java.util.concurrent.Callable;
import hexlet.code.Differ;

import static java.nio.file.Files.readString;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<String> {
    @CommandLine.Parameters(index = "0", description = "path to first file")
    private Path filepath1;
    @CommandLine.Parameters(index = "1", description = "path to second file")
    private Path filepath2;

    @CommandLine.Option(names = { "-f", "--format" }, description = "output format [default: stylish]")
    private Format format /*= Format.LONG*/;
    @CommandLine.Option(names = { "-h", "--help" }, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested = false;
    @CommandLine.Option(names = {"-V", "--version"}, description = "Print version information and exit.")
    private boolean verbose;

    @Override
    public String call() throws Exception {
        return "";
    }
    public static void main(String[] args) throws Exception {

        App app = new App();
        int exitCode = new CommandLine(app).execute(args);
        //System.exit(exitCode);

        if (app.filepath1 == null || app.filepath2 == null) {
            return;
        }

        String str1 = readString(app.filepath1);
        System.out.println("file " + app.filepath1.toString());
        System.out.println(str1);
        String str2 = readString(app.filepath2);
        System.out.println("file " + app.filepath2.toString());
        System.out.println(str2);

        int type = 0;
        if (app.filepath1.toString().endsWith(".json") && app.filepath2.toString().endsWith(".json"))  {
            type = Parser.JSON_TYPE;
        } else {
            if (app.filepath1.toString().endsWith(".yml") && app.filepath2.toString().endsWith(".yml")) {
                type = Parser.YAML_TYPE;
            }
        }

        String result = Differ.generate(str1, str2, type);
        System.out.println("result :");
        System.out.println(result);

    }

}
