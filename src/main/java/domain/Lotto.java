package domain;

import constant.ErrorMessage;

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

    public List<LottoNumber> getLottoNumber() {
        return List.copyOf(numbers);
    }

    public int countMatchingNumbers(Lotto otherLotto) {
        try {
            return Math.toIntExact(numbers.stream()
                    .filter(otherLotto::contains)
                    .count());
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(ErrorMessage.OVERFLOW.getMessage());
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
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
