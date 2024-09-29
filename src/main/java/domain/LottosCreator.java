package domain;

import java.util.List;
import java.util.stream.IntStream;

public class LottosCreator {

    private final LottoMakeStrategy lottoMakeStrategy;

    public LottosCreator(final LottoMakeStrategy lottoMakeStrategy) {
        this.lottoMakeStrategy = lottoMakeStrategy;
    }

    public List<Lotto> createLottos(final int lottoCount) {
        return IntStream.range(0, lottoCount)
                .mapToObj(i ->
                        new Lotto(lottoMakeStrategy.makeLotto())
                ).toList();
    }
}
