package christmas.global.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader {

    public static List<String> read(String filePath) {
        try(var lines = Files.lines(Paths.get(filePath))) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
