package model;

import java.util.List;

public class BonusNumber extends LottoNumber {

    public BonusNumber(final int number) {
        super(number);
    }

    public static BonusNumber of(final String input, final List<Integer> winningLotto) {
        validateStringInput(input);
        int number = Integer.parseInt(input);
        validateContainInput(number, winningLotto);
        return new BonusNumber(number);
    }

    private static void validateContainInput(final int input, final List<Integer> lotto) {
        if (lotto.contains(input)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호에 포함되어 있으면 안됩니다.");
        }
    }
}
