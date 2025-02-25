package model;

import java.util.*;

public class LottoGenerator {

    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_MIN = 1;

    public Lotto generateLottoNumbers() {
        List<Integer> shuffledNumbers = shuffleNumbers(initializeNumbers());
        List<Integer> cutOffNumbers = cutNumbersSize(shuffledNumbers);
        List<Integer> sortedNumbers = sortNumbersByAscending(cutOffNumbers);

        return new Lotto(sortedNumbers);
    }

    private List<Integer> initializeNumbers() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = LOTTO_NUMBER_MIN; i <= LOTTO_NUMBER_MAX; i++) {
            numbers.add(i);
        }

        return numbers;
    }

    private List<Integer> shuffleNumbers(List<Integer> numbers) {
        Collections.shuffle(numbers);

        return numbers;
    }

    private List<Integer> cutNumbersSize(List<Integer> numbers) {
        return numbers.subList(0, LOTTO_NUMBER_SIZE);
    }

    private List<Integer> sortNumbersByAscending(List<Integer> numbers) {
        Collections.sort(numbers);

        return numbers;
    }
}
