package hexlet.code.formatters;

public class FormatterFactory {
    public static Formatter getFormatter(String formatter) {
        switch (formatter) {
            case "stylish":
                return new Stylish();
            default: throw new IllegalArgumentException("Такого форматера нет: " + formatter);
        }
    }


}
