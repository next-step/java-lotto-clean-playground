package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
    private static final Money LOTTO_PRICE = new Money(1000);
    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public Lottos buy(Money money, List<Lotto> manualLottos) {
        validatePrice(money);
        int totalCount = calculateLottoCount(money);
        int autoCount = totalCount - manualLottos.size();

        if (autoCount < 0) {
            throw new IllegalArgumentException("수동 구매 개수가 구매 가능한 개수를 초과했습니다.");
        }
        return generateLottos(autoCount, manualLottos);
    }

    private int calculateLottoCount(Money money) {
        return money.divideBy(LOTTO_PRICE);
    }

    private void validatePrice(Money money) {
        if (money.isLessThan(LOTTO_PRICE)) {
            throw new IllegalArgumentException("구매 금액은 1000원 이상이어야 합니다.");
        }

        if (!money.isDivisibleBy(LOTTO_PRICE)) {
            throw new IllegalArgumentException("구매 금액은 1000원 단위여야 합니다.");
        }
    }

    private Lottos generateLottos(int autoCount, List<Lotto> manualLottos) {
        List<Lotto> lottos = new ArrayList<>(manualLottos);

        for (int i = 0; i < autoCount; i++) {
            lottos.add(lottoGenerator.generateLotto());
        }
        return new Lottos(lottos);
    }

    public void validatePurchasePrice(Money money) {
        validatePrice(money);
    }

    public void validateManualCount(Money money, int manualCount) {
        int totalCount = calculateLottoCount(money);

        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동으로 구매 가능한 개수를 초과했습니다.");
        }
    }
}
