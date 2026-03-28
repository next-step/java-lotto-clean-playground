package domain;

import java.util.List;

public record Lotto(List<Integer> lottoNumbers) {
    public Lotto {
        validateLottoNumberCount(lottoNumbers);
        validateLottoNumberRange(lottoNumbers);
        validateNoDuplicateNumber(lottoNumbers);

        lottoNumbers = List.copyOf(lottoNumbers);
    }

    public int countMatchingNumbers(List<Integer> winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private void validateLottoNumberCount(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateNoDuplicateNumber(List<Integer> lottoNumbers) {
        long uniqueCount = lottoNumbers.stream().distinct().count();
        if (uniqueCount != 6) {
            throw new IllegalArgumentException("로또 번호 사이에 중복된 숫자가 존재합니다.");
        }
    }

    private void validateLottoNumberRange(List<Integer> lottoNumbers) {
        long validRangeCount = lottoNumbers.stream()
                .filter(num -> (num > 0 && num <= 45)).count();
        if (validRangeCount != 6) {
            throw new IllegalArgumentException("로또 번호는 1과 45 사이에 정수여야 합니다.");
        }
    }
}
