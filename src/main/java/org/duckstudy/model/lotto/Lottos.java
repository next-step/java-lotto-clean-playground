package org.duckstudy.model.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos generateLottos(final int lottoCount) {
        return Stream.generate(Lotto::createRandomLotto)
                .limit(lottoCount)
                .collect(collectingAndThen(toList(), Lottos::new));
    }

    public LottoResult accumulateLottoResult(final Lotto winningLotto, final LottoNumber bonusNumber) {
        return lottos.stream()
                .map(lotto -> LottoResult.createLottoResult(lotto, winningLotto, bonusNumber))
                .reduce(new LottoResult(Map.of()), LottoResult::merge);
    }

    public Lottos merge(final Lottos other) {
        return new Lottos(Stream.of(this.lottos, other.lottos)
                .flatMap(List::stream)
                .collect(toList()));
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
