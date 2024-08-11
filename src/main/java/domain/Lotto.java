package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Errors;

public class Lotto {

    private final List<LottoNumber> numbers;

    public final static int SIZE = 6;
    public final static int PRICE = 1000;

    public Lotto(List<LottoNumber> numbers) {
        validateNumbers(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validateNumbers(List<LottoNumber> numbers) {
        validateLottoNumbersSize(numbers);
        validateDuplicateNumbers(numbers);
    }

    private void validateLottoNumbersSize(List<LottoNumber> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException(Errors.WRONG_LOTTO_SIZE);
        }
    }

    private void validateDuplicateNumbers(List<LottoNumber> numbers) {
        if (hasDuplicates(numbers)) {
            throw new IllegalArgumentException(Errors.NUMBERS_HAS_DUPLICATE_NUMBER);
        }
    }

    public static Lotto from(List<Integer> values) {
        final List<LottoNumber> lottoNumbers = values.stream()
            .map(LottoNumber::new)
            .toList();
        return new Lotto(lottoNumbers);
    }

    private boolean hasDuplicates(List<LottoNumber> numbers) {
        Set<LottoNumber> numberSet = new HashSet<>(numbers);
        return numberSet.size() != numbers.size();
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
            .sorted()
            .map(LottoNumber::getValue)
            .toList();
    }

    public int getMatchingNumberCount(Lotto comparingLotto) {
        return numbers.stream()
            .filter(comparingLotto::isContains)
            .toList()
            .size();
    }

    public boolean isContains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public boolean isContains(int value) {
        return isContains(new LottoNumber(value));
    }
}
