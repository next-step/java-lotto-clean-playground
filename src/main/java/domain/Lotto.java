package domain;

import constant.ErrorMessage;

import java.util.List;

public record Lotto(List<Integer> lottoNumbers) {
    public Lotto {
        lottoNumbers = lottoNumbers.stream()
                .sorted()
                .toList();

        validateLottoLength(lottoNumbers);
        validateLottoRange(lottoNumbers);
        validateNoDuplicateNumber(lottoNumbers);
    }

    public int countMatchingNumbers(List<Integer> winningNumbers) {
        return (int) lottoNumbers.stream()
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
