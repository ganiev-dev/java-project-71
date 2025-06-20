package hexlet.code.formatters;

public class Formatter {
    public static Format getFormatter(String formatter) {
        switch (formatter) {
            case "stylish":
                return new Stylish();
            case "plain":
                return new Plain();
            case "json":
                return new Json();
            default: throw new IllegalArgumentException("Такого форматера нет: " + formatter);
        }
    }
}
