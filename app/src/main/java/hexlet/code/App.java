package hexlet.code;

import picocli.CommandLine;
import java.util.concurrent.Callable;

    @CommandLine.Command(
            name = "gendiff",
            version = "1.0",
            mixinStandardHelpOptions = true, //автосправка
            description = "Compares two configuration files and shows a difference."
    )
    
    public class App implements Callable<Integer> {

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