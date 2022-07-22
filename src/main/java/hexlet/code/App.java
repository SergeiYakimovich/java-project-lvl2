package hexlet.code;

import picocli.CommandLine;
import java.nio.file.Path;
import java.text.Format;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<String> {
    @CommandLine.Parameters(index = "0", description = "path to first file")
    private Path filepath1;
    @CommandLine.Parameters(index = "1", description = "path to second file")
    private Path filepath2;

    @CommandLine.Option(names = { "-f", "--format" }, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private Format format = Format.stylish;
    @CommandLine.Option(names = { "-h", "--help" }, usageHelp = true,
            description = "Show this help message and exit.")
    private boolean helpRequested = false;
    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    private boolean versionHelpRequested;

    enum Format {
        stylish,
        plain,
        json,
    }
    @Override
    public String call() throws Exception {
        return "";
    }
    public static void main(String[] args) throws Exception {

        App app = new App();
        int exitCode = new CommandLine(app).execute(args);

        if (app.filepath1 == null || app.filepath2 == null) {
            return;
        }

        String result = Differ.generate(app.filepath1.toString(), app.filepath2.toString(), app.format.toString());
//        System.out.println("result :");
//        System.out.println(result);

    }

}
