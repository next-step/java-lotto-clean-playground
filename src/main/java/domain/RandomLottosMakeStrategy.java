package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class RandomLottosMakeStrategy implements LottoMakeStrategy {

    private final int lottoCount;

    public final List<Integer> lottoNumbers = new ArrayList<Integer>(
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                    11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                    21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                    31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                    41, 42, 43, 44, 45)
    );


    public RandomLottosMakeStrategy(final int lottoCount) {
        this.lottoCount = lottoCount;
    }

    @Override
    public Lottos makeLottos() {
        return new Lottos(IntStream.range(0, lottoCount)
                .mapToObj(i -> {
                    Collections.shuffle(lottoNumbers);
                    List<Integer> lotto = new ArrayList<>(
                            lottoNumbers.subList(START_EXTRACT_INDEX, START_EXTRACT_INDEX + LOTTO_NUMBER_COUNT)
                    );
                    Collections.sort(lotto);
                    return new Lotto(lotto);
                }).toList());
    }
}
