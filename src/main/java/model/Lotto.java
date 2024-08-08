package model;

import global.Rank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static model.exception.ExceptionMessage.LOTTO_HAS_SAME_NUMBER_ERROR_MESSAGE;
import static model.exception.ExceptionMessage.LOTTO_SIZE_ERROR_MESSAGE;

public class Lotto {

    private static final int LOTTO_NUMBER_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(final List<LottoNumber> numbers) {
        validateNumbers(numbers);
        numbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
        this.numbers = numbers;
    }

    public static Lotto fromNumbers(final List<Integer> input) {
        final List<LottoNumber> numbers = input.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new Lotto(numbers);
    }

    public static Lotto fromStringsInput(final String[] input) {
        final List<LottoNumber> numbers = Arrays.stream(input)
                .map(LottoNumber::from)
                .collect(Collectors.toList());

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
