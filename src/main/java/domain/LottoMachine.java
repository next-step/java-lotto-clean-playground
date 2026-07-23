package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;
    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    public List<Lotto> buy(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int purchaseCount = purchaseAmount / LOTTO_PRICE;
        return createLottos(purchaseCount);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        validatePositiveAmount(purchaseAmount);
        validateDivisibleAmount(purchaseAmount);
    }

    private void validatePositiveAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("구입 금액은 0원보다 커야 합니다.");
        }
    }

    private void validateDivisibleAmount(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }

    private List<Lotto> createLottos(int purchaseCount) {
        return IntStream.range(0, purchaseCount)
                .mapToObj(index -> createLotto())
                .collect(Collectors.toList());
    }

    private Lotto createLotto() {
        return new Lotto(lottoNumberGenerator.generate());
    }
}
