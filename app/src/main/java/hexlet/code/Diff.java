package hexlet.code;

public class Diff {
    public static void isDifferent(String path1, String path2) throws Exception {
        var file1 = Parse.getData(path1);
        var file2 = Parse.getData(path2);
        System.out.println(file1);
        System.out.println(file2);
    }
}
