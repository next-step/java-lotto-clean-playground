package domain;

import java.util.stream.IntStream;

public class LottosCreator {

    private final LottoMakeStrategy lottoMakeStrategy;

    public LottosCreator(final LottoMakeStrategy lottoMakeStrategy) {
        this.lottoMakeStrategy = lottoMakeStrategy;
    }

    public Lottos createLottos(final int lottoCount) {
        return new Lottos(IntStream.range(0, lottoCount)
                .mapToObj(i ->
                        new Lotto(lottoMakeStrategy.makeLotto())
                ).toList());
    }
}
