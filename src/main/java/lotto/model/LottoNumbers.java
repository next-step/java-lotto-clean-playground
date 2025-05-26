package lotto.model;

import java.util.Collections;
import java.util.List;

public class LottoNumbers {

    private static final int LOTTO_SIZE = 6;
    private final List<Integer> numbers;

    public LottoNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    // 수정 불가능한 숫자 리스트를 반환
    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
