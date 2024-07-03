package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoNumbers implements LottoNumberGenerate {

    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    @Override
    public List<Integer> generateLottoNumber() {
        List<Integer> numbers = new ArrayList<>();
        for (int number = 0; number < MAX_LOTTO_NUMBER; number++) {
            numbers.add(number + 1);
        }
        Collections.shuffle(numbers);
        List<Integer> lottoNumbers = numbers.subList(0, LOTTO_SIZE);
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    @Override
    public int generateBonusNumber() {
        Random random = new Random();
        return random.nextInt(MAX_LOTTO_NUMBER);
    }
}
