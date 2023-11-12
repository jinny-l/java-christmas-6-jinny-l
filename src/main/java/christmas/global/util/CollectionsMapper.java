package christmas.global.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionsMapper {

    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    private CollectionsMapper() {
    }

    public static Map<String, Integer> splitListToMap(String delimiter, List<String> input) {
        validateNotEmptyList(input);

        return input.stream()
                .map(element -> element.split(delimiter))
                .collect(Collectors.toUnmodifiableMap(
                        element -> element[KEY_INDEX],
                        element -> Integer.parseInt(element[VALUE_INDEX])
                ));
    }

    public static List<String> splitStringToList(String delimiter, String input) {
        return Stream.of(input.split(delimiter))
                .filter(CollectionsMapper::validateNotBlank)
                .toList();
    }

    private static void validateNotEmptyList(List<String> input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException();
        }
        return true;
    }
}
