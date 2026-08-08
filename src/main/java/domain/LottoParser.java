package domain;

import java.util.List;
import java.util.ArrayList;

public class LottoParser {

    public static List<Integer> parseInput(String input) {
        List<Integer> numbers = new ArrayList<>();
        for (String value : input.split(",")) {
            numbers.add(Integer.parseInt(value.trim()));
        }

        return numbers;
    }
}
