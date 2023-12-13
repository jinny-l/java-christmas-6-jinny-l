package christmas.global.util;

import christmas.global.util.exception.UtilError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

// TODO 리팩토링 필요
public class CSVReader {

    public static List<String> read(String filePath) {
        try(var lines = Files.lines(Paths.get(filePath))) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(UtilError.FILE_IO.getMessage());
        }
    }
}
