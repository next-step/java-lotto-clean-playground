package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import util.LottoNumberGenerator;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_MIN_NUM = 1;

    Random random = new Random();

    public List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>();

        while (numbers.size() < LOTTO_SIZE) {
            int number = LOTTO_MIN_NUM + random.nextInt(LOTTO_MAX_NUMBER);
            if (!numbers.contains(number)) {
                numbers.add(number);
            }
        }
        return numbers;
    }
}
