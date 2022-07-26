package hexlet.code;

import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

class App implements Callable<String> {
    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @CommandLine.Option(names = { "-f", "--format" }, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format = "stylish";

    @Override
    public String call() throws Exception {
        if (filepath1 != null && filepath2 != null) {
            String result = Differ.generate(filepath1, filepath2, format);
            System.out.println(result);
        }
        return "";
    }
    public static void main(String[] args) throws Exception {

        App app = new App();
        int exitCode = new CommandLine(app).execute(args);

    }

}
