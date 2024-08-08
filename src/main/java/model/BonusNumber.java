package model;

import java.util.List;

import static model.exception.ExceptionMessage.BONUS_BALL_IN_WINNING_LOTTO_ERROR_MESSAGE;

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
            throw new IllegalArgumentException(BONUS_BALL_IN_WINNING_LOTTO_ERROR_MESSAGE);
        }
    }
}
