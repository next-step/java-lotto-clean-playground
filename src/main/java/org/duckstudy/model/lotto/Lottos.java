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

    public LottoResult calculateTotalLottoResult(Lotto winningLotto, LottoNumber bonusNumber) {
        return lottos.stream()
                .map(lotto -> calculateLottoResult(lotto, winningLotto, bonusNumber, new LottoResult(Map.of())))
                .reduce(new LottoResult(Map.of()), LottoResult::merge);
    }

    private LottoResult calculateLottoResult(Lotto lotto, Lotto winningLotto, LottoNumber bonusNumber,
                                             LottoResult result
    ) {
        int matchingCount = lotto.countMatchingNumber(winningLotto);
        boolean matchBonus = lotto.containsNumber(bonusNumber);
        return result.updateResult(matchingCount, matchBonus);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
