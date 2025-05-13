package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

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
        private String file1;

        @Parameters(
                index = "1",
                description = "path to second file",
                paramLabel = "filepath2"
        )
        private String file2;

        @Override
        public Integer call() { //логика приложения
            System.out.println("Hello world!");
            return 0; // код завершения
        }

        public static void main(String[] args) {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
        }
    }