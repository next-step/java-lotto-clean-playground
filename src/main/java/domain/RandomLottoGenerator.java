package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLottoGenerator implements LottoGenerator {
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_MIN_NUM = 1;

    Random random = new Random();

    public List<LottoNumber> generateNumbers() {
        List<LottoNumber> numbers = new ArrayList<>();

        while (numbers.size() < LOTTO_SIZE) {
            LottoNumber lottoNumber = new LottoNumber(generateRandomNumber());
            if (!numbers.contains(lottoNumber)) {
                numbers.add(lottoNumber);
            }
        }

        return numbers;
    }

    private int generateRandomNumber() {
        return LOTTO_MIN_NUM + random.nextInt(LOTTO_MAX_NUMBER);
    }
}
