package domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator implements LottoGenerator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    @Override
    public Lotto generate() {
        List<Integer> candidates = createCandidates();
        Collections.shuffle(candidates);

        List<LottoNumber> lottoNumbers = candidates.stream()
                .limit(LOTTO_SIZE)
                .map(LottoNumber::new)
                .toList();

        return new Lotto(lottoNumbers);
    }

    private List<Integer> createCandidates() {
        List<Integer> candidates = new ArrayList<>();

        for (int number = MIN_LOTTO_NUMBER; number <= MAX_LOTTO_NUMBER; number++) {
            candidates.add(number);
        }

        return candidates;
    }
}
