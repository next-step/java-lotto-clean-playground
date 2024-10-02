package domain;

public class LottosCreator {

    private final LottoMakeStrategy lottoMakeStrategy;

    public LottosCreator(final LottoMakeStrategy lottoMakeStrategy) {
        this.lottoMakeStrategy = lottoMakeStrategy;
    }

    public Lottos createLottos() {
        return lottoMakeStrategy.makeLottos();
    }
}
