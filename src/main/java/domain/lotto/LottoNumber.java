package domain.lotto;

import static domain.lotto.LottoConstant.MAX_LOTTO_NUMBER;
import static domain.lotto.LottoConstant.MIN_LOTTO_NUMBER;

public record LottoNumber(
        int number
) implements Comparable<LottoNumber> {

    public static LottoNumber of(int number) {
        validateLottoRange(number);
        return new LottoNumber(number);
    }

    private static void validateLottoRange(int number) {
        if (number < MIN_LOTTO_NUMBER || MAX_LOTTO_NUMBER < number) {
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이여야 합니다.");
        }
    }

    @Override
    public int compareTo(final LottoNumber other) {
        return Integer.compare(this.number, other.number);
    }
}
