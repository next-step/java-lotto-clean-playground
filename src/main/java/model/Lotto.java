package model;

import common.Rank;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final Pattern REGEX = Pattern.compile("^[0-9]+$");

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validateNumbers(numbers);
        Collections.sort(numbers);
        this.numbers = numbers;
    }

    public static Lotto from(final String[] input) {
        if (!validateNumberInput(input)) {
            throw new IllegalArgumentException("숫자입력만 허용합니다.");
        }

        final List<Integer> numbers = Arrays.stream(input)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return new Lotto(numbers);
    }

    private void validateNumbers(final List<Integer> numbers) {
        validateNumbersSize(numbers);
        validateDuplicateNumbers(numbers);
        validateNumber1to45(numbers);
    }

    private void validateNumbersSize(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또는 6개의 숫자로 구성되어야 합니다.");
        }
    }

    private void validateDuplicateNumbers(final List<Integer> numbers) {
        Set<Integer> notDuplicatedNumbers = new HashSet<>(numbers);
        if (notDuplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 내 동일한 숫자가 있으면 안됩니다.");
        }
    }

    private void validateNumber1to45(final List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
                throw new IllegalArgumentException("로또 번호는 1과 45사이의 숫자이어야 합니다.");
            }
        }
    }

    private static boolean validateNumberInput(final String[] input) {
        return Arrays.stream(input)
                .allMatch(s -> REGEX.matcher(s).matches());
    }

    public Rank getRank(final Lotto winningLotto) {
        int correctCnt = numbers.stream()
                .filter(winningLotto::containNumber)
                .toList()
                .size();

        return Rank.findPlace(correctCnt);
    }

    private boolean containNumber(final int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
