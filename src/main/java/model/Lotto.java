package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int LOTTO_MINIMUM_NUMBER = 1;
    private static final int LOTTO_MAXIMUM_NUMBER = 45;

    private final List<Integer> lottoNumbers;

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

    private List<Integer> getTempLottoNumbers() {
        List<Integer> tempLottoNumbers = new ArrayList<>();
        for (int number = LOTTO_MINIMUM_NUMBER; number <= LOTTO_MAXIMUM_NUMBER; number++) {
            tempLottoNumbers.add(number);
        }
        return tempLottoNumbers;
    }

    public String toStringLottoTickets() {
        List<Integer> sortedLottoNumbers = getSortedLottoNumbers();
        return sortedLottoNumbers.toString();
    }

    private List<Integer> getSortedLottoNumbers() {
        return lottoNumbers.stream()
                .sorted()
                .toList();
    }
}
