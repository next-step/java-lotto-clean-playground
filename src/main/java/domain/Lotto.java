package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers = new ArrayList<>();

    public Lotto(List<Integer> numberValues) {
        validateNumberCount(numberValues);
        validateDuplicateNumbers(numberValues);

        convertToLottoNumbers(numberValues);

        Collections.sort(numbers);
    }

    public List<LottoNumber> getNumbers() {
        return List.copyOf(numbers);
    }

    private void convertToLottoNumbers(List<Integer> numberValues) {
        for (int number : numberValues) {
            LottoNumber lottoNumber = new LottoNumber(number);
            numbers.add(lottoNumber);
        }
    }

    private void validateNumberCount(List<Integer> numberValues) {
        if (numberValues.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 " + NUMBER_COUNT + "개여야 합니다.");
        }
    }

    private void validateDuplicateNumbers(List<Integer> numberValues) {
        if (new HashSet<>(numberValues).size() != numberValues.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }
}
