package lotto.model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class WinNumbers {
    private final Set<LottoNumber> winNumbers;
    private final LottoNumber bonusNumber;

    private WinNumbers(Set<LottoNumber> winNumbers, final LottoNumber bonusNumber) {
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinNumbers of(final String winNumbers, final String bonusNumber) {
        final Set<LottoNumber> numbers = convertWinNumbers(winNumbers);
        final LottoNumber bonus = convertBonusNumber(bonusNumber);
        validate(numbers, bonus);

        return new WinNumbers(numbers, bonus);
    }

    private static Set<LottoNumber> convertWinNumbers(final String winNumbers) {
        try {
            return Arrays.stream(winNumbers.split(", "))
                .map(Integer::parseInt)
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아니거나 형식이 잘못됨");
        }
    }

    private static LottoNumber convertBonusNumber(final String bonusNumber) {
        try {
            return LottoNumber.from(Integer.parseInt(bonusNumber));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아님");
        }
    }

    private static void validate(Set<LottoNumber> numbers, LottoNumber bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 숫자는 지난 주 당첨 번호와 달라야 함");
        }
    }

    public Set<LottoNumber> getWinNumbers() {
        return winNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
