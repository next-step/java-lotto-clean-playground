package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final LottoNumberGenerator generator;

    public LottoMachine(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public Lottos purchase(int lottoCount) {
        validateManualCount(lottoCount, 0);
        List<Lotto> lottos = new ArrayList<>();
        for (int count = 0; count < lottoCount; count++) {
            lottos.add(new Lotto(generator.generate()));
        }

        return new Lottos(lottos);
    }

    public Lottos purchase(int totalCount, List<Lotto> manualLottos) {
        validateManualCount(totalCount, manualLottos.size());
        List<Lotto> lottos = new ArrayList<>(manualLottos);
        lottos.addAll(purchase(totalCount - manualLottos.size()).getLottos());
        return new Lottos(lottos);
    }

    public static void validateManualCount(int totalCount, int manualCount) {
        if (manualCount < 0 || manualCount > totalCount) {
            throw new IllegalArgumentException("[ERROR] 수동 구매 수량은 0부터 전체 구매 수량 사이여야 합니다.");
        }
    }
}
