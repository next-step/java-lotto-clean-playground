package org.duckstudy.model.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.duckstudy.model.Price;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public static Lottos generateLottosByPrice(Price price) {
        int lottoCount = price.calculateLottoCount();

        return Stream.generate(Lotto::createRandomLotto)
                .limit(lottoCount)
                .collect(collectingAndThen(toList(), Lottos::new));
    }

    public LottoResult createTotalLottoResult(Lotto winningLotto, LottoNumber bonusNumber) {
        return lottos.stream()
                .map(lotto -> LottoResult.createLottoResult(lotto, winningLotto, bonusNumber))
                .reduce(new LottoResult(Map.of()), LottoResult::merge);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
