package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbers {
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_CREATE_SIZE = 6;

    private final List<Integer> numbers;

    public LottoNumbers() {
        this.numbers = sortedNumbers(generateAutoLottoNumbers());
    }

    public LottoNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortedNumbers(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_CREATE_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        if (numbers.stream().distinct().count() != LOTTO_CREATE_SIZE) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
        if (numbers.stream().anyMatch(n -> n < LOTTO_MIN_NUMBER || n > LOTTO_MAX_NUMBER)) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }
    }

    private List<Integer> generateAutoLottoNumbers() {
        List<Integer> shuffledNumbers = IntStream.rangeClosed(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(shuffledNumbers);

        return shuffledNumbers.subList(0,LOTTO_CREATE_SIZE);
    }

    private List<Integer> sortedNumbers(List<Integer> numbers) {
        return List.copyOf(numbers.stream().sorted().toList());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
