package domain;

import java.util.Set;
import java.util.stream.Collectors;

import exception.LottoNumberInvalidCountException;

public class LottoNumbers {

    public static final int NUMBER_COUNT = 6;
    private final Set<LottoNumber> lottoNumbers;

    public LottoNumbers(Set<Integer> numbers) {
        validateNumberCount(numbers);
        this.lottoNumbers = numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toSet());
    }

    private void validateNumberCount(Set<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new LottoNumberInvalidCountException(String.format("로또 번호는 %d개여야 합니다.", NUMBER_COUNT));
        }
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}

