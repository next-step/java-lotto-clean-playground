package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumber {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<Integer> numbers;

    public LottoNumber(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateIntegerRange(numbers);
        this.numbers = numbers;
    }

    private void validateSize(List<Integer> numbers){
        if(numbers.size() != LOTTO_NUMBERS_SIZE){
            throw new IllegalArgumentException("로또는 6개의 숫자로 구성되어야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers){
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if(uniqueNumbers.size() != LOTTO_NUMBERS_SIZE){
            throw new IllegalArgumentException("로또의 숫자들은 중복되지 않아야 합니다.");
        }
    }

    private void validateIntegerRange(List<Integer> numbers){
        List<Integer> outOfRangeNumbers = numbers.stream()
                .filter(number -> number < 1 || number > 45)
                .collect(Collectors.toList());

        if (!outOfRangeNumbers.isEmpty()) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 중 하나의 정수여야 합니다. : " + outOfRangeNumbers);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean hasBonusBall(int bonusBall){
        return numbers.contains(bonusBall);
    }
}
