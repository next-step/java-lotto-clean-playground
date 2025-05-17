package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbersGenerator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public LottoNumbers generate() {
        List<LottoNumber> candidateNumbers = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            candidateNumbers.add(new LottoNumber(i));
        }

        Collections.shuffle(candidateNumbers);
        List<LottoNumber> selectedNumbers = candidateNumbers.subList(0, LOTTO_NUMBER_COUNT);
        Collections.sort(selectedNumbers);
        return new LottoNumbers(new ArrayList<LottoNumber>());
    }
}
