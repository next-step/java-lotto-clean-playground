package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoRandom {
    private static final int LOTTO_RANGE = 45;
    private static final int LOTTO_COUNT = 6;
    private static final int LOTTO_NUM = 1;

    private final Random random = new Random();

    private List<Integer> lottoRandomNum() {
        List<Integer> lottoNumbers = new ArrayList<>();
        while (lottoNumbers.size() <= LOTTO_COUNT) {
            int randomNumber = random.nextInt(LOTTO_RANGE) + LOTTO_NUM;
            duplicationRemove(lottoNumbers, randomNumber);
        }
        return lottoNumbers;
    }

    private static void duplicationRemove(List<Integer> lottoNumbers, int randomNumber) {
        if (!lottoNumbers.contains(randomNumber)) {
            lottoNumbers.add(randomNumber);
        }
    }

    public List<Integer> getLottoRandomNum() {
        return lottoRandomNum();
    }
}
