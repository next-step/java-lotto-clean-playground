package org.duckstudy.model.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.duckstudy.model.Price;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public static Lottos generateLottosByPrice(Price price) {
        int lottoCount = Lotto.calculateLottoCount(price);

        return Stream.generate(Lotto::createRandomLotto)
                .limit(lottoCount)
                .collect(collectingAndThen(toList(), Lottos::new));
    }

    public LottoResult calculateWinningResult(Lotto winningLotto) {
        return new LottoResult(lottos.stream()
                .collect(groupingBy(winningLotto::countMatchingNumber, summingInt(e -> 1))));
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
