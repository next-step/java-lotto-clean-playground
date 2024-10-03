package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberFormatter {
    private final static String COMMA = ",";
    public final static List<Integer> formedNumbers = new ArrayList<>();

    public static List<Integer> formNumbers(String inputNumbers) {
        formedNumbers.clear();
        formedNumbers.addAll(
                Arrays.stream(inputNumbers.split(COMMA)) // "1,2,3,4,5" → ["1", "2", "3", "4", "5"]
                        .map(Integer::parseInt) // ["1", "2", "3", "4", "5"] → [1, 2, 3, 4, 5]
                        .sorted() // 오름차순으로 정렬
                        .toList() // [1, 2, 3, 4, 5]
        );
        return formedNumbers;
    }

}
