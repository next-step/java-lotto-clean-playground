package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumberConvertor {

    private static final String SPLIT_CHARACTERS = ", ";

    public List<Integer> convertLottoNumbers(final String lottoNumbers) {
        return splitLottoNumbers(lottoNumbers).stream()
                .map(Integer::parseInt)
                .toList();
    }

    private List<String> splitLottoNumbers(final String lottoNumbers) {
        return new ArrayList<String>(List.of(lottoNumbers.split(SPLIT_CHARACTERS)));
    }

}
