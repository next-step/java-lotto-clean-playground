package util;

import java.util.ArrayList;
import java.util.List;

public class LottoNumberSeparator {

    private static final String SPLIT_CHARACTERS = ", ";

    public static List<String> separateWinningLottoNumbers(final String winningLottoNumbers) {
        return new ArrayList<String>(List.of(winningLottoNumbers.split(SPLIT_CHARACTERS)));
    }
}
