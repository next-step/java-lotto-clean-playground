package domain;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LottoNumbers {
    private static final int SIZE = 6;
    private final List<LottoNumber> numbers;

    public LottoNumbers(List<LottoNumber> numbers) {
        validateNumber(numbers);
        this.numbers = numbers;
    }

    private void validateNumber(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호의 갯수는 6이여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        Set<LottoNumber> setNumbers = new HashSet<LottoNumber>(numbers);

        if (setNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복이 없어야 합니다");
        }
    }
}
