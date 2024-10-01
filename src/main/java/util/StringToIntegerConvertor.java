package util;

import java.util.ArrayList;
import java.util.List;

public class StringToIntegerConvertor {

    public static List<Integer> convertStringToInteger(final List<String> winningLottoNumbers) {
        return winningLottoNumbers.stream()
                .map(Integer::parseInt)
                .toList();
    }
}
