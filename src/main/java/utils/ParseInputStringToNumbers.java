package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParseInputStringToNumbers {
    public static List<Integer> parse(String line) {
        return Arrays.stream(line.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
