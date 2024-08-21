package model;

import global.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static model.exception.ExceptionMessage.LOTTO_HAS_SAME_NUMBER_ERROR_MESSAGE;
import static model.exception.ExceptionMessage.LOTTO_SIZE_ERROR_MESSAGE;

public class Lotto {

    private static final List<Integer> NUMBERS = Arrays.asList(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
        21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
        31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
        41, 42, 43, 44, 45);
    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(final List<LottoNumber> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    public static Lotto byRandomGenerate() {
        Collections.shuffle(NUMBERS);
        final List<Integer> randomNumbers = new ArrayList<>(NUMBERS.subList(0, LOTTO_NUMBER_SIZE));
        return fromNumbers(randomNumbers);
    }

    public static Lotto fromNumbers(final List<Integer> input) {
        final List<LottoNumber> numbers = input.stream()
                .map(LottoNumber::new)
                .toList();

        return new Lotto(numbers);
    }

    public static Lotto fromStringsInput(final List<String> input) {
        final List<LottoNumber> numbers = input.stream()
                .map(LottoNumber::from)
                .toList();

        return new Lotto(numbers);
    }

    private void validateNumbers(final List<LottoNumber> numbers) {
        validateNumbersSize(numbers);
        validateDuplicateNumbers(numbers);
    }

    private void validateNumbersSize(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateNumbers(final List<LottoNumber> numbers) {
        Set<LottoNumber> notDuplicatedNumbers = new HashSet<>(numbers);
        if (notDuplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(LOTTO_HAS_SAME_NUMBER_ERROR_MESSAGE);
        }
    }

    public Rank getRank(final Lotto winningLotto, final LottoNumber bonusNumber) {
        int correctCnt = numbers.stream()
            .filter(winningLotto::containNumber)
            .toList()
            .size();

        return Rank.findPlace(correctCnt, containNumber(bonusNumber));
    }

    private boolean containNumber(final LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
