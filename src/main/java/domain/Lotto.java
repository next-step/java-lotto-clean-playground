package domain;

import constant.ErrorMessage;
import dto.LottoStatus;

import java.util.List;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoLength(lottoNumbers);
        validateNoDuplicateNumber(lottoNumbers);

        numbers = lottoNumbers.stream()
                .sorted()
                .toList();
    }

    public LottoStatus numbers() {
        return new LottoStatus(numbers);
    }

    public int countMatchingNumbers(List<LottoNumber> winningNumbers) {
        try {
            return  Math.toIntExact(numbers.stream()
                    .filter(winningNumbers::contains)
                    .count());
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(ErrorMessage.OVERFLOW.getMessage());
        }
    }

    private void validateLottoLength(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_COUNT.getMessage());
        }
    }

    private void validateNoDuplicateNumber(List<LottoNumber> lottoNumbers) {
        long uniqueCount = lottoNumbers.stream().distinct().count();
        if (uniqueCount != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DUPLICATE_NUMBER.getMessage());
        }
    }
}
