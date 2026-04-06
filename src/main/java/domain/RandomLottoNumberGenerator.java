package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {


    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<Integer> lottoPool;

    public RandomLottoNumberGenerator() {
        this.lottoPool = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoPool.add(i);
        }
    }

    @Override
    public List<Integer> generate() {
        Collections.shuffle(lottoPool);
        List<Integer> selectedNumbers = new ArrayList<>(lottoPool.subList(0, LOTTO_NUMBER_COUNT));
        Collections.sort(selectedNumbers);
        return selectedNumbers;
    }
}
