package domain;

import java.util.List;

import exception.LottoNumberInvalidCountException;
import exception.LottoNumberOutOfRangeException;

public class Lotto {

    public static final int NUMBER_COUNT = 6;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoNumbers(numbers);
        this.numbers = sortNumbers(numbers);
    }

    private void validateLottoNumbers(List<Integer> numbers) {
        validateLottoNumberCount(numbers);
        validateLottoNumbersWithinRange(numbers);
    }

    private void validateLottoNumberCount(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT)
            throw new LottoNumberInvalidCountException("로또 번호는 %d개여야 합니다.".formatted(NUMBER_COUNT));
    }

    private void validateLottoNumbersWithinRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(n -> n < MIN_NUMBER || n > MAX_NUMBER))
            throw new LottoNumberOutOfRangeException("로또 번호는 %d~%d 사이여야 합니다.".formatted(MIN_NUMBER, MAX_NUMBER));
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        return numbers.stream()
            .sorted()
            .toList();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
