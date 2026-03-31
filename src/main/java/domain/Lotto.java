package domain;

import constant.ErrorMessage;
import dto.LottoStatus;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateLottoLength(lottoNumbers);
        validateLottoRange(lottoNumbers);
        validateNoDuplicateNumber(lottoNumbers);

        numbers = lottoNumbers.stream()
                .sorted()
                .toList();
    }

    public LottoStatus numbers() {
        return new LottoStatus(numbers);
    }

    public int countMatchingNumbers(List<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private void validateLottoLength(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_COUNT.getMessage());
        }
    }

    private void validateLottoRange(List<Integer> lottoNumbers) {
        boolean isValidRange = lottoNumbers.stream()
                .allMatch(num -> num >= 1 && num <= 45);
        if (!isValidRange) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_RANGE.getMessage());
        }
    }

    private void validateNoDuplicateNumber(List<Integer> lottoNumbers) {
        long uniqueCount = lottoNumbers.stream().distinct().count();
        if (uniqueCount != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DUPLICATE_NUMBER.getMessage());
        }
    }
}
