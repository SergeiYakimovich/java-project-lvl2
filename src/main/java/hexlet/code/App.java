package hexlet.code;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {

        int exitCode = new CommandLine(new Tar()).execute(args);
        System.exit(exitCode);

        System.out.println("Hello World!");
    }

    @CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
            description = "Compares two configuration files and shows a difference.")
    public static class Tar {
        @CommandLine.Option(names = { "-h", "--help" }, usageHelp = true, description = "Show this help message and exit.")
        private boolean helpRequested = false;
        @CommandLine.Option(names={"-V", "--version"}, description="Print version information and exit.")
        private boolean verbose;

    }

}
