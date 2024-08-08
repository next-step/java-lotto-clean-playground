package domain.lotto;

import domain.common.Money;
import java.util.Objects;

public class LottoPurchasePrice {

    private static final String NEGATIVE_NUMBER = "음수 입력을 불가능합니다.";
    private static final int LOTTO_PRICE = 1000;

    private final int count;

    public LottoPurchasePrice(Money money) {
        validateNegativeNumber(money);
        validateMoneyUnit(money);
        this.count = getCount(money);
    }

    private void validateNegativeNumber(Money money) {
        if (money.getAmount() < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER);
        }
    }

    private static void validateMoneyUnit(Money money) {
        if (money.getAmount() % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("1000원 단위의 돈만 받음");
        }
    }

    private int getCount(Money money) {
        return money.getAmount() / LOTTO_PRICE;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LottoPurchasePrice that = (LottoPurchasePrice) object;
        return getCount() == that.getCount();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCount());
    }
}
