package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int MAX_LOTTO_NUMBER = 45;
    private final List<Integer> lottoNumbers;

    public Lotto() {
        this.lottoNumbers = generateLottoNumbers();
    }

    private List<Integer> generateLottoNumbers() {
        List<Integer> tempLottoNumbers = getTempLottoNumbers();

        Collections.shuffle(tempLottoNumbers);

        return List.copyOf(
                tempLottoNumbers.stream()
                        .limit(LOTTO_NUMBERS_SIZE)
                        .toList()
                );
    }

    private List<Integer> getTempLottoNumbers() {
        List<Integer> tempLottoNumbers = new ArrayList<>();
        for(int i = 1; i <= MAX_LOTTO_NUMBER; i++) {
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
