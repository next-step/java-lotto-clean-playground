package model;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class LottoNumber {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final Pattern REGEX = Pattern.compile("^[0-9]+$");

    private final int number;

    public LottoNumber(final int number) {
        validateNumber1to45(number);
        this.number = number;
    }

    public static LottoNumber from(final String input) {
        validateStringInput(input);
        return new LottoNumber(Integer.parseInt(input));
    }

    public static LottoNumber forBonusNumber(final String input, final List<Integer> winningLotto) {
        validateStringInput(input);
        int number = Integer.parseInt(input);
        validateContainInput(number, winningLotto);
        return new LottoNumber(number);
    }

    private void validateNumber1to45(final int number) {
        if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호 및 보너스 볼은 1과 45사이의 숫자이어야 합니다.");
        }
    }

    private static void validateStringInput(final String input) {
        if (!REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException("숫자입력만 허용합니다.");
        }
    }

    private static void validateContainInput(int input, List<Integer> lotto) {
        if (lotto.contains(input)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호에 포함되어 있으면 안됩니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
