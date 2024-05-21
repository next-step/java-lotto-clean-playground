package exception;

import java.util.ArrayList;

public record ManualNumber(int money, int count) {
    private static final int LOTTO_PRICE = 1000;

    public ManualNumber {
        validateNegativeNumber(count);
        checkRemainingMoney(money, count);
    }

    private void validateNegativeNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 입력될 수 없습니다.");
        }
    }
    public void validateLottoRange(ArrayList<Integer> lotto) {
        if (lotto.stream().allMatch(number -> number >= 1 && number <= 45)) {
            throw new IllegalArgumentException("1 ~ 45사이의 값만 입력할 수 있습니다.");
        }
    }

    public void checkRemainingMoney(int money, int manualCount) {
        if (money < manualCount * LOTTO_PRICE) {
            throw new IllegalArgumentException("구입 금액을 넘은 갯수 입니다.");
        }
    }
}
