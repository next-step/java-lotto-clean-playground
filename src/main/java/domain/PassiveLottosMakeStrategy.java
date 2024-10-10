package domain;

import util.LottoNumberConvertor;

import java.util.List;

public class PassiveLottosMakeStrategy implements LottoMakeStrategy {

    private final List<String> lottoNumbers;

    public PassiveLottosMakeStrategy(final List<String> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public Lottos makeLottos() {
        return new Lottos(lottoNumbers.stream()
                .map(lottoNumbers -> new Lotto(LottoNumberConvertor.convert(lottoNumbers))
                )
                .toList());
    }
}
