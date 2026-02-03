package domain.lotto;

import static domain.lotto.LottoNumbers.SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumberGenerator {

    private static final List<Integer> LOTTO_NUMBER_POOL = createLottoNumberPool();

    public LottoNumbers generate() {
        List<Integer> shuffled = new ArrayList<>(LOTTO_NUMBER_POOL);
        Collections.shuffle(shuffled);

        List<LottoNumber> lottoNumbers = shuffled.stream()
                .limit(SIZE)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new LottoNumbers(lottoNumbers);
    }

    private static List<Integer> createLottoNumberPool() {
        List<Integer> lottoNumberPool = new ArrayList<>();
        for (int i = LottoNumber.MIN_LOTTO_NUMBER; i < LottoNumber.MAX_LOTTO_NUMBER; i++) {
            lottoNumberPool.add(i);
        }
        return List.copyOf(lottoNumberPool);
    }
}
