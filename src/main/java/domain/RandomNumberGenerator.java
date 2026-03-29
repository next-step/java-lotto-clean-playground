package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    @Override
    public List<Integer> generate() {
        List<Integer> lottoNumbers = new ArrayList<>();

        for (int i = LOTTO_START_NUMBER; i <= LOTTO_END_NUMBER; i++) {
            lottoNumbers.add(i);
        }

        Collections.shuffle(lottoNumbers);

        return lottoNumbers.subList(0, LOTTO_SIZE)
                .stream()
                .sorted()
                .toList();
    }
}
