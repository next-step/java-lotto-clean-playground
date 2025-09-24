package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LottoNumbers {
    private final List<LottoNumber> numbers;

    public LottoNumbers(List<LottoNumber> numbers) {
        checkSize(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void checkSize(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호가 6개가 아닙니다");
        }
    }

    public LottoNumbers sortNumbers() {
        List<LottoNumber> sortNumber = new ArrayList<>(numbers);
        Collections.sort(sortNumber, Comparator.comparingInt(LottoNumber::getNumber));
        return new LottoNumbers(sortNumber);
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
