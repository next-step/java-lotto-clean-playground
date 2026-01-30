package lotto.domain.service;

import java.util.ArrayList;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.generator.RandomLottoGenerator;
import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.model.Lotto;
import lotto.domain.model.Lottos;

public class LottoMachine {

    private final LottoNumberGenerator generator;

    public LottoMachine(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public Lottos issueWithManual(int totalCount, List<Lotto> manualLottos) {
        int autoCount = calculateAutoCount(totalCount, manualLottos);

        Lottos autoLottos = issue(autoCount);

        List<Lotto> combinedLottos = new ArrayList<>(manualLottos);
        combinedLottos.addAll(autoLottos.getValues());

        return new Lottos(combinedLottos);
    }

    private int calculateAutoCount(int totalCount, List<Lotto> manualLottos) {
        int manualSize = manualLottos.size();
        if (manualSize > totalCount) {
            throw new IllegalArgumentException("수동 구매 수가 총 구매 가능 수를 초과할 수 없습니다.");
        }
        return totalCount - manualSize;
    }

    public Lottos issue(int count) {
        return new Lottos(IntStream.range(0, count)
            .mapToObj(i -> Lotto.from(generator.generateLottoNumbers()))
            .toList());
    }
}
