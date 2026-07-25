package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;

    private final LottoNumberGenerator generator;

    public LottoMachine(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public Lottos buy(Money money, List<Lotto> manualLottos) {
        validateMoney(money);
        int totalCount = money.divide(LOTTO_PRICE);
        validateManualCount(manualLottos.size(), totalCount);
        List<Lotto> lottos = new ArrayList<>(manualLottos);
        addAutoLottos(lottos, totalCount - manualLottos.size());
        return new Lottos(lottos);
    }

    private void validateMoney(Money money) {
        if (!money.isDivisibleBy(LOTTO_PRICE)) {
            throw new IllegalArgumentException("구입금액은 " + LOTTO_PRICE + "원 단위여야 합니다.");
        }
    }

    private void validateManualCount(int manualCount, int totalCount) {
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 수는 전체 구매 수를 초과할 수 없습니다.");
        }
    }

    private void addAutoLottos(List<Lotto> lottos, int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(generator.generate()));
        }
    }
}
