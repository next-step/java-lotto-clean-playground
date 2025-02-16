package domain;

import java.util.List;

import exception.LottoNumberInvalidCountException;

public class LottoNumbers {

    public static final int NUMBER_COUNT = 6;
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<Integer> numbers) {
        validateNumberCount(numbers);
        this.lottoNumbers = numbers.stream()
            .map(LottoNumber::new)
            .sorted()
            .toList();
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new LottoNumberInvalidCountException(String.format("로또 번호는 %d개여야 합니다.", NUMBER_COUNT));
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}

