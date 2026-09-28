package domain;

import java.util.List;

public class Lotto {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private final List<Integer> lotto;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.lotto = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("[ERROR] 로또 번호가 입력되지 않았습니다.");
        }
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또 번호는 " + LOTTO_SIZE + "개여야 합니다. (입력 개수: " + numbers.size() + "개)");
        }
    }

    private void validateDuplicates(List<Integer> numbers) {
        long uniqueCount = numbers.stream().distinct().count();
        if (uniqueCount != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다. 잘못된 값: " + number);
            }
        }
    }

    public List<Integer> getNumbers() {
        return lotto;
    }


}
