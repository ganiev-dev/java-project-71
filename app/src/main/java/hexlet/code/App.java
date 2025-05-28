package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;
import static hexlet.code.Differ.generate;

@Command(
    name = "gendiff",
    version = "1.0",
    mixinStandardHelpOptions = true, //автосправка
    description = "Compares two configuration files and shows a difference."
    )

public class App implements Callable<Integer> {

    @Option(
        names = {"-f", "--format"},
        description = "output format [default: stylish]",
        paramLabel = "format",
        defaultValue = "stylish")
        private String format;

    @Parameters(
        index = "0",
        description = "path to first file",
        paramLabel = "filepath1"
        )
        private String filepath1;

    @Parameters(
        index = "1",
        description = "path to second file",
        paramLabel = "filepath2"
        )
        private String filepath2;

    @Override // точка входа
    public Integer call() throws Exception {
        System.out.println(generate(filepath1, filepath2, format));
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
