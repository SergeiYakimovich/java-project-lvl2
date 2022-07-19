package hexlet.code;

import picocli.CommandLine;
import java.io.File;
import java.nio.file.Path;
import java.text.Format;
import java.util.concurrent.Callable;

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
    @CommandLine.Option(names={"-V", "--version"}, description="Print version information and exit.")
    private boolean verbose;

    @Override
    public String call() throws Exception {
        return "";
    }
    public static void main(String[] args) throws Exception {

        App app = new App();
        int exitCode = new CommandLine(app).execute(args);
        //System.exit(exitCode);

        String str1 = readString(app.filepath1);
        System.out.println(str1);
        String str2 = readString(app.filepath2);
        System.out.println(str2);
        System.out.println("OK");
        String result = Differ.generate(str1, str2);
        System.out.println(result);


    }

}
