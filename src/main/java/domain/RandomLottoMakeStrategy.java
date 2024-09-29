package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoMakeStrategy implements LottoMakeStrategy {

    private static final int START_EXTRACT_INDEX = 0;
    private static final int LOTTO_NUMBER_COUNT = 6;

    @Override
    public List<Integer> makeLotto() {
        Collections.shuffle(lottoNumbers);
        List<Integer> lotto = new ArrayList<>(
                lottoNumbers.subList(START_EXTRACT_INDEX, START_EXTRACT_INDEX + LOTTO_NUMBER_COUNT)
        );
        Collections.sort(lotto);
        return lotto;
    }
}
