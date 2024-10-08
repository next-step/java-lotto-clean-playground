package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberFormatter {
    private final static String COMMA = ",";
    private final static List<Integer> formedNumbers = new ArrayList<>();

    public static List<Integer> formNumbers(String inputNumbers) {
        checkNull(inputNumbers);

        try {
            formedNumbers.clear();
            List<Integer> List = Arrays.stream(inputNumbers.split(COMMA)) // "1,2,3,4,5" → ["1", "2", "3", "4", "5"]
                    .map(Integer::parseInt) // ["1", "2", "3", "4", "5"] → [1, 2, 3, 4, 5]
                    .sorted() // 오름차순으로 정렬
                    .toList();// [1, 2, 3, 4, 5]
            formedNumbers.addAll(List);

        } catch (NumberFormatException e){
            throw new IllegalArgumentException("입력 값에 숫자가 아닌 값이 포함되어 있습니다.");
        }

        return formedNumbers;
    }

    private static void checkNull(String inputNumbers){
        if (inputNumbers == null || inputNumbers.isBlank()) {
            throw new IllegalArgumentException("입력된 숫자가 없습니다. 숫자를 입력해 주세요.");
        }
    }
}
