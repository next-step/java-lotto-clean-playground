package domain.lotto;

import exception.DuplicatedLottoNumberException;
import exception.LottoNumberSizeException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class LottoNumbers {

    public static final int SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateLottoNumbersSize(lottoNumbers);
        validateDuplicatedNumbers(lottoNumbers);
        this.lottoNumbers = sortNumbers(lottoNumbers);
    }

    public static LottoNumbers from(String input) {
        List<LottoNumber> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoNumbers(numbers);
    }

    private List<LottoNumber> sortNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public int countMatching(LottoNumbers other) {
        return (int) lottoNumbers.stream()
                .filter(other::contains)
                .count();
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    private void validateDuplicatedNumbers(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> lottoNumbersSet = new HashSet<>(lottoNumbers);
        if (lottoNumbersSet.size() < lottoNumbers.size()) {
            throw new DuplicatedLottoNumberException();
        }
    }

    private void validateLottoNumbersSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE) {
            throw new LottoNumberSizeException();
        }
    }

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
