package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MIN_LOTTO_NUMBER = 1;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoNumbers(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validateLottoNumbers(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        List<Integer> checkedNumbers = new ArrayList<>();
        for (int number : numbers) {
            checkAlreadyContains(checkedNumbers, number);
            checkedNumbers.add(number);
        }
    }

    private void checkAlreadyContains(List<Integer> checkedNumbers, int number) {
        if (checkedNumbers.contains(number)) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        int max = numbers.stream().max(Integer::compareTo).orElse(0);
        int min = numbers.stream().min(Integer::compareTo).orElse(0);

        if (max > MAX_LOTTO_NUMBER || min < MIN_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
