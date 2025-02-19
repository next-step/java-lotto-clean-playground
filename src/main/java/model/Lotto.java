package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private final List<Integer> lottoNumbers;
    private static final int LOTTO_NUMBERS_SIZE = 6;

    public Lotto() {
        this.lottoNumbers = generateTempLottoNumbers();
    }

    private List<Integer> generateTempLottoNumbers() {
        List<Integer> tempLottoNumbers = getTempLottoNumbers();

        Collections.shuffle(tempLottoNumbers);

        return tempLottoNumbers.stream()
                .limit(LOTTO_NUMBERS_SIZE)
                .toList();
    }

    private static List<Integer> getTempLottoNumbers() {
        List<Integer> tempLottoNumbers = new ArrayList<>();
        for(int i = 1; i <= 45; i++) {
            tempLottoNumbers.add(i);
        }
        return tempLottoNumbers;
    }

    public List<Integer> getSortedLottoNumbers() {
        return lottoNumbers.stream()
                .sorted()
                .toList();
    }
}
