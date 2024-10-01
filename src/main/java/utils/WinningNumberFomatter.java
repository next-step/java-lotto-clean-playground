package utils;

import view.InputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumberFomatter {
    private final static String COMMA = ",";

    public static List<Integer> formWinningNumbers(){
        String inputWinningNumbers = InputView.inputLastWinningNumbers();

        return Arrays.stream(inputWinningNumbers.split(COMMA)) // "1,2,3,4,5" → ["1", "2", "3", "4", "5"]
                .map(Integer::parseInt) // ["1", "2", "3", "4", "5"] → [1, 2, 3, 4, 5]
                .sorted()
                .collect(Collectors.toList()); // [1, 2, 3, 4, 5]
    }

}
