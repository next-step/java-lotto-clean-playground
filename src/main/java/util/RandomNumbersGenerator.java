package util;

import java.util.*;

import static constant.LottoConstants.*;

public class RandomNumbersGenerator {
    public static List<Integer> generateSixLottoNumbers() {
        List<Integer> randomNumbers = generateShuffledNumbers().subList(0, LOTTO_SIZE);
        Collections.sort(randomNumbers);
        return randomNumbers;
    }

    private static List<Integer> generateShuffledNumbers() {
        List<Integer> numbers = generateListForShuffle();
        Collections.shuffle(numbers);
        return numbers;
    }

    private static List<Integer> generateListForShuffle() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = LOTTO_LOWER_BOUND; i <= LOTTO_UPPER_BOUND; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
