package domain.lotto;

import domain.money.Money;
import java.math.BigDecimal;

public record LottoPurchaseInfo(
        Money purchaseAmount,
        int manualCount
) {

    public static final Money LOTTO_PRICE = Money.from("1000");

    public LottoPurchaseInfo {
        validateMinimum(purchaseAmount);
        validateUnit(purchaseAmount);
        validateManualCount(purchaseAmount, manualCount);
    }

    public int getTotalCount() {
        return purchaseAmount.divide(LOTTO_PRICE).amount().intValueExact();
    }

    public int getAutoCount() {
        return getTotalCount() - manualCount;
    }

    private void validateMinimum(final Money purchaseAmount) {
        boolean isBelowMinimum = purchaseAmount.amount().compareTo(LOTTO_PRICE.amount()) < 0;
        if (isBelowMinimum) {
            throw new IllegalArgumentException("최소 %s원 이상 입력해야 합니다.".formatted(LOTTO_PRICE.amount()));
        }
    }

    private void validateUnit(final Money purchaseAmount) {
        boolean isInvalidUnit = purchaseAmount.amount().remainder(LOTTO_PRICE.amount()).compareTo(BigDecimal.ZERO) != 0;
        if (isInvalidUnit) {
            throw new IllegalArgumentException("구입 금액은 %s원 단위로 입력해야 합니다.".formatted(LOTTO_PRICE.amount()));
        }
    }

    private void validateManualCount(Money totalAmount, int manualCount) {
        int maxCount = totalAmount.divide(LOTTO_PRICE).amount().intValueExact();
        if (maxCount < manualCount) {
            throw new IllegalArgumentException("수동 로또 개수가 전체 구매 가능한 개수보다 적어야 합니다.");
        }
    }
}
