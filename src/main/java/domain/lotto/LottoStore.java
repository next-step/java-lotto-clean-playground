package domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
    private static final Money LOTTO_PRICE = new Money(1000);

    private LottoStore() {
    }

    public static int calculatePurchasableCount(Money money) {
        int totalCount = money.divideBy(LOTTO_PRICE);
        validatePurchasableCount(totalCount);
        return totalCount;
    }

    private static void validatePurchasableCount(int totalCount) {
        if (totalCount < 1) {
            throw new IllegalArgumentException("금액은 1000원 이상이어야 합니다.");
        }
    }

    public static void validateManualCount(int totalCount, int manualCount) {
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동으로 구매 가능한 개수를 초과했습니다.");
        }
        if (manualCount < 0) {
            throw new IllegalArgumentException("수동 구매 개수는 0 이상이어야 합니다.");
        }
    }

    public static Lottos buy(int totalCount, List<Lotto> manualLottos) {
        int autoCount = totalCount - manualLottos.size();
        if (autoCount < 0) {
            throw new IllegalArgumentException("수동 구매 개수가 구매 가능한 개수를 초과했습니다.");
        }
        return generateLottos(autoCount, manualLottos);
    }

    private static Lottos generateLottos(int autoCount, List<Lotto> manualLottos) {
        List<Lotto> lottos = new ArrayList<>(manualLottos);

        for (int i = 0; i < autoCount; i++) {
            lottos.add(LottoGenerator.generateLotto());
        }
        return new Lottos(lottos);
    }
}
