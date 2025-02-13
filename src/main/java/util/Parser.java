package util;

import java.util.Arrays;
import java.util.List;

public class Parser {

    private Parser() {
    }

    public static List<Integer> parseIntegerList(String numberString) {
        return Arrays.stream(numberString.split("\\s*,\\s*"))
                .map(Integer::parseInt)
                .toList();
    }
}
