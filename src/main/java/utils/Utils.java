package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<Integer> parseCommaSeparatedInts(String lottoString) {
        List<String> strings = splitByComma(lottoString);
        return parseIntList(strings);
    }

    private static List<String> splitByComma(String string) {
        return Arrays.asList(string.split(","));
    }

    private static List<Integer> parseIntList(List<String> strings) {
        List<Integer> result = new ArrayList<>();
        for (String string : strings) {
            result.add(Integer.parseInt(string.trim()));
        }
        return result;
    }
}
