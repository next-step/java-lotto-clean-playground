package org.duckstudy.model.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.duckstudy.model.Price;

public record Lottos(List<Lotto> lottos) {

    public Lottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public static Lottos generateLottosByPrice(Price price) {
        int lottoCount = Lotto.calculateLottoCount(price);

        return Stream.generate(Lotto::createRandomLotto)
                .limit(lottoCount)
                .collect(collectingAndThen(toList(), Lottos::new));
    }

    public Map<Integer, Integer> calculateWinningResult(Lotto winningLotto) {
        return lottos.stream()
                .collect(groupingBy(winningLotto::countMatchingNumber, summingInt(e -> 1)));
    }
}
