package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbersGenerator {
    private static final Random random = new Random();
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;
    Set<Integer> lottoNumbers = new HashSet<>();

    public List<Integer> generate() {
        while (lottoNumbers.size() < LOTTO_NUMBER_COUNT) {
            lottoNumbers.add(generateNumber());
        }
        return lottoNumbers.stream().sorted().collect(Collectors.toList());
    }

    private int generateNumber() {
        return random.nextInt(MAX_LOTTO_NUMBER) + MIN_LOTTO_NUMBER;
    }
}
