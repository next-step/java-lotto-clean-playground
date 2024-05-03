package utils;

import java.util.Arrays;
import java.util.List;

public class NumbersParser {

    public static List<Integer> parseIntegerList(String input) {
        String[] splittedInput = input.split(",");
        return Arrays.stream(splittedInput)
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

}
