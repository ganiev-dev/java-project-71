package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatterFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;
import static hexlet.code.Diffs.getDifferents;
import static hexlet.code.Diffs.toPrint;


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
        String format;

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
        var differentsBetweenFiles = getDifferents(filepath1, filepath2);
        Formatter formatter = FormatterFactory.getFormatter(format);
        var formatedResult = formatter.processDiffMap(differentsBetweenFiles);
        toPrint(formatedResult);
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
