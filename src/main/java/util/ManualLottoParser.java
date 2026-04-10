package util;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ManualLottoParser {
    public static List<Integer> parseManualNumber(String input) {
        String[] splitInput = input.split(",");
        List<Integer> Number = new ArrayList<>();
        for (String number : splitInput) {
            Number.add(parseInt(number.trim()));
        }
        return Number;
    }
}
