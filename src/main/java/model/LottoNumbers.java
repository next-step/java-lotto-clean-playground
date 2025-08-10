package model;

import java.util.Collections;
import java.util.List;

public final class LottoNumbers {
    private final List<Integer> numbers;

    public LottoNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> asList() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    private void validate(List<Integer> lottoNumbers) {
        validateNumbersSize(lottoNumbers);
        validateNumbersRange(lottoNumbers);
    }

    private void validateNumbersSize(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != Lotto.LOTTO_SIZE) {
            throw new RuntimeException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateNumbersRange(List<Integer> lottoNumbers){
        if (lottoNumbers.stream().anyMatch(n -> n < Lotto.MIN_LOTTO_NUMBER || n > Lotto.MAX_LOTTO_NUMBER)) {
            throw new RuntimeException("로또 번호는 1이상 45이하여야 합니다.");
        }
    }
}
