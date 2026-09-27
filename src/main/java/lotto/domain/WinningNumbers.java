package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WinningNumbers {
    private static final int WINNING_NUMBER_COUNT = 6;
    private final List<LottoNumber> numbers;

    public WinningNumbers(List<Integer> numbers) {
        validateDuplicate(numbers);
        validateSize(numbers);
        this.numbers = toLottoNumbers(numbers);
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
        // 당첨 번호에 해당 숫자가 포함되어 있는지 확인
    }

}
