package Model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualLotto extends Lotto {

    public ManualLotto(String rawNumbers) {
        List<Integer> parsedNumbers = parseNumbers(rawNumbers);

        validate(parsedNumbers);

        this.numbers = parsedNumbers;
    }

    private List<Integer> parseNumbers(String rawNumbers) {
        try {
            return Arrays.stream(rawNumbers.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 숫자만 입력 가능합니다.");
        }
    }

    private void validate(List<Integer> numbers) {
            validateSize(numbers);
            validateDuplicates(numbers);
            validateNumberRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("로또 번호는 1과 45 사이의 숫자여야 합니다.");
            }
        }
    }
}
