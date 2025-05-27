package lotto.validate;

import lotto.domain.LottoNumber;

import java.util.HashSet;
import java.util.List;

public class WinningNumberValidate {
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;

    public static void validate(List<LottoNumber> numbers) {
        numberValidate(numbers);
        duplicateNumber(numbers);
    }

    private static void numberValidate(List<LottoNumber> list) {
        for (LottoNumber lottoNumber : list) {
            int inputNumber = lottoNumber.getValue();
            if (inputNumber < LOTTO_START_NUMBER || inputNumber > LOTTO_END_NUMBER) {
                throw new IllegalArgumentException("1~45 사이의 숫자만 입력 가능합니다.");
            }
        }
    }

    private static void duplicateNumber(List<LottoNumber> list) {
        if (new HashSet<>(list).size() != list.size()) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
    }
}
